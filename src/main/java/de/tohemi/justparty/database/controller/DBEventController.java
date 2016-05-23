package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.database.tables.EventsDBTabelle;
import de.tohemi.justparty.database.tables.GuestlistDBTabelle;
import de.tohemi.justparty.database.tables.LocationDBTabelle;
import de.tohemi.justparty.datamodel.Accepted;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.UserEventRelation;
import de.tohemi.justparty.datamodel.address.ConcreteAddress;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 24.04.2016.
 */
public class DBEventController extends DBControl {
    private static DBEventController instance;

    public static synchronized DBEventController getInstance() {
        if (instance == null) {
            return new DBEventController();
        }
        return instance;
    }

    public boolean addEvent(Event e) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO events (name, email) VALUE (?, ?)");
            ps.setString(1, e.getName());
            ps.setString(2, e.getEventOwner().getEmail());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean deleteEvent(Event e, User u) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("DELETE FROM events WHERE event_id=? AND email=?");
            PreparedStatement psGuests = c.prepareStatement("DELETE FROM " + GuestlistDBTabelle.TABLE + " WHERE event=?");
            psEvent.setInt(1, e.getId());
            psEvent.setString(2, u.getEmail());
            psGuests.setInt(1, e.getId());

            if (psEvent.execute()) {
                psGuests.executeUpdate();
            }
            psEvent.close();
            psGuests.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public Event getEventById(int id) {

        final Event event = EventFactory.createEvent(id);
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT * FROM events WHERE event_id=?");

            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();

            while (rs.next()) {
                event.setSpotifyPlaylistLink(rs.getURL(EventsDBTabelle.COLUMN_SPOTIFY));
                event.setGooglePlusLink(rs.getURL(EventsDBTabelle.COLUMN_GOOGLE));
                event.setFacebookLink(rs.getURL(EventsDBTabelle.COLUMN_FACEBOOK));
                event.setBegin(rs.getTimestamp(EventsDBTabelle.COLUMN_BEGIN));
                event.setEnd(rs.getTimestamp(EventsDBTabelle.COLUMN_END));
                event.setDescription(rs.getString(EventsDBTabelle.COLUMN_DESCRIPTION));

                    event.setEventOwner(UserFactory.create(new EMail(rs.getString(EventsDBTabelle.COLUMN_EMAIL))));

                event.setName(rs.getString(EventsDBTabelle.COLUMN_NAME));
                event.setLocation(getLocation(rs.getInt(EventsDBTabelle.COLUMN_ADDRESS_ID)));
            }
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } catch (InvalidEmailException e) {
            LOGGER.logException(e, "");
        } finally {
            releaseConnection(ds, c);
        }
        return event;
    }

    public boolean updateEventData(Event event, User user, Location l) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        boolean tries = false;
        String email = "";
        try {
            PreparedStatement pS1 = c.prepareStatement("SELECT email FROM events WHERE event_id=?;");
            pS1.setInt(1, event.getId());

            ResultSet rs = pS1.executeQuery();
            while (rs.next())
                email = rs.getString(EventsDBTabelle.COLUMN_EMAIL);
            rs.close();
            pS1.close();

            PreparedStatement pS = c.prepareStatement("UPDATE events SET name=?, description=?, begin=?, end=?, address_id=?, facebook_link=?, wishlist_link=?, googleplus_link=?, Spotify_link=? WHERE event_id=?;");
            pS.setString(1, event.getName());
            pS.setString(2, event.getDescription());
            pS.setTimestamp(3, event.getBegin());
            pS.setTimestamp(4, event.getEnd());
            pS.setInt(5, DBLocationController.getInstance().getLocationID(l));
            pS.setURL(6, event.getFacebookLink());
            pS.setURL(7, event.getGooglePlusLink());
            pS.setURL(8, event.getWishlistLink());
            pS.setURL(9, event.getSpotifyPlaylistLink());
            pS.setInt(10, event.getId());

            if (email.equals(user.getEmail())) {
                pS.executeUpdate();
                tries = true;
            }
            pS.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return tries;
    }

    public boolean userIsHostOfRequestedEvent(User user, Event event) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        boolean tries = false;
        try {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM events WHERE email=? AND event_id=?;");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setInt(2, event.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tries = true;
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return tries;
    }

    public List<UserEventRelation> getHostedUERs(User user) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        List<UserEventRelation> userEventRelations = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT event_id, name, begin, email FROM events WHERE email = ?;");
            String email = user.getEmail();
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Event event = EventFactory.createEvent(resultSet.getInt(EventsDBTabelle.COLUMN_ID));
                event.setName(resultSet.getString(EventsDBTabelle.COLUMN_NAME));
                event.setEventOwner(user);
                Timestamp timeStamp = resultSet.getTimestamp(EventsDBTabelle.COLUMN_BEGIN);
                if (timeStamp != null) {
                    event.setBegin(timeStamp);
                }
                userEventRelations.add(new UserEventRelation(event, user));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return userEventRelations;
    }

    public List<UserEventRelation> getInvitedUERs(User user) {

        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        List<UserEventRelation> userEventRelations = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT event_id, name, begin, email, " + GuestlistDBTabelle.COLUMN_STATUS + " FROM events, " + GuestlistDBTabelle.TABLE + " WHERE event_id = event AND " + GuestlistDBTabelle.COLUMN_GUEST + " = ?;");
            String email = user.getEmail();
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Event event = EventFactory.createEvent(resultSet.getInt(EventsDBTabelle.COLUMN_ID));
                event.setName(resultSet.getString(EventsDBTabelle.COLUMN_NAME));
                event.setEventOwner(UserFactory.create(resultSet.getString(EventsDBTabelle.COLUMN_EMAIL)));
                Timestamp timeStamp = resultSet.getTimestamp(EventsDBTabelle.COLUMN_BEGIN);
                if (timeStamp != null) {
                    event.setBegin(timeStamp);
                }
                int status = resultSet.getInt(GuestlistDBTabelle.COLUMN_STATUS);
                Accepted accepted = GuestlistDBTabelle.getAcceptedObjectForStatus(status);
                UserEventRelation uer = new UserEventRelation(event, user, accepted);
                userEventRelations.add(uer);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return userEventRelations;
    }

    public int getEventID(Event e) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        int exe = 0;
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT event_id FROM events WHERE name=? AND email=?;");
            psEvent.setString(1, e.getName());
            psEvent.setString(2, e.getEventOwner().getEmail());
            ResultSet rs = psEvent.executeQuery();
            while (rs.next())
                exe = rs.getInt(EventsDBTabelle.COLUMN_ID);
            rs.close();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return exe;
    }

    public String getName(int id) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        String name = "";
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT name FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while (rs.next())
                name = rs.getString(EventsDBTabelle.COLUMN_NAME);
            rs.close();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return name;
    }

    public void setName(int id, String name) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET name=? WHERE event_id=?;");
            psEvent.setString(1, name);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public String getDescription(int id) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        String name = "";
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT description FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while (rs.next())
                name = rs.getString(EventsDBTabelle.COLUMN_DESCRIPTION);
            rs.close();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return name;
    }

    public void setDescription(int id, String description) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET description=? WHERE event_id=?;");
            psEvent.setString(1, description);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public Timestamp getBegin(int id) {

        Timestamp begin = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT begin FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while (rs.next()) {
                begin = new Timestamp(rs.getTimestamp(EventsDBTabelle.COLUMN_BEGIN).getTime());
            }
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return begin;
    }

    public void setBegin(int id, Timestamp begin) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET begin=? WHERE event_id=?;");
            psEvent.setTimestamp(1, begin);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public Timestamp getEnd(int id) {

        Timestamp end = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT end FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while (rs.next()) {
                end = new Timestamp(rs.getTimestamp(EventsDBTabelle.COLUMN_END).getTime());
            }
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return end;
    }

    public void setEnd(int id, Timestamp end) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET end=? WHERE event_id=?;");
            psEvent.setTimestamp(1, end);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public Location getLocation(int id) {

        Location location = null;
        int address_id = id;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            //PreparedStatement psEvent = c.prepareStatement("SELECT address_id FROM events WHERE event_id=?;");
            PreparedStatement psLocation = c.prepareStatement("SELECT * FROM location WHERE address_id=?");
            //psEvent.setInt(1, id);
            //ResultSet rs = psEvent.executeQuery();
            //while(rs.next()) {
            //    address_id = rs.getInt(EventsDBTabelle.COLUMN_ADDRESS_ID);
            //}
            //rs.close();
            //psEvent.close();

            psLocation.setInt(1, address_id);
            ResultSet rs = psLocation.executeQuery();
            while (rs.next()) {
                location = new Location(rs.getString(LocationDBTabelle.COLUMN_NAME), new ConcreteAddress(rs.getString(LocationDBTabelle.COLUMN_STREET), rs.getString(LocationDBTabelle.COLUMN_HOUSENR), new ZipCode(rs.getInt(LocationDBTabelle.COLUMN_ZIPCODE)), rs.getString(LocationDBTabelle.COLUMN_CITY), rs.getString(LocationDBTabelle.COLUMN_COUNTRY)), rs.getBoolean(LocationDBTabelle.COLUMN_PUBLIC));
            }
            rs.close();
            psLocation.close();
        } catch (SQLException | ZipCodeInvalidException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return location;
    }

    public void setLocation(int id, Location location) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET address_id=? WHERE event_id=?;");
            psEvent.setInt(1, DBLocationController.getInstance().getLocationID(location));
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public User getEventOwner(int id) {

        User eo = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT email FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while (rs.next()) {
                eo = UserFactory.create(rs.getString(EventsDBTabelle.COLUMN_EMAIL));
            }
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return eo;
    }

    public void setEventOwner(int id, User user) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET email=? WHERE event_id=?;");
            psEvent.setString(1, user.getEmail());
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public URL getFacebookLink(int id) {

        URL facebook = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT facebook_link FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while (rs.next()) {
                facebook = rs.getURL(EventsDBTabelle.COLUMN_FACEBOOK);
            }
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return facebook;
    }

    public void setFacebookLink(int id, URL facebook) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET facebook_link=? WHERE event_id=?;");
            psEvent.setURL(1, facebook);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public URL getSpotifyLink(int id) {

        URL spotify = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT Spotify_link FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while (rs.next()) {
                spotify = rs.getURL(EventsDBTabelle.COLUMN_SPOTIFY);
            }
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return spotify;
    }

    public void setSpotifyLink(int id, URL spotify) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET Spotify_link=? WHERE event_id=?;");
            psEvent.setURL(1, spotify);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public URL getGooglePlusLink(int id) {

        URL google = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT googleplus_link FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while (rs.next()) {
                google = rs.getURL(EventsDBTabelle.COLUMN_GOOGLE);
            }
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return google;
    }

    public void setGooglePlusLink(int id, URL google) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET googleplus_link=? WHERE event_id=?;");
            psEvent.setURL(1, google);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public URL getWishlistLink(int id) {

        URL wish = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT wishlist_link FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while (rs.next()) {
                wish = rs.getURL(EventsDBTabelle.COLUMN_WISHLIST);
            }
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return wish;
    }

    public void setWishlistLink(int id, URL wishlist) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET wishlist_link=? WHERE event_id=?;");
            psEvent.setURL(1, wishlist);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }
}
