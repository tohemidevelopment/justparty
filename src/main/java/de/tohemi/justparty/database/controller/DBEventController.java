package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.database.tables.EventsDBTabelle;
import de.tohemi.justparty.database.tables.GuestlistDBTabelle;
import de.tohemi.justparty.datamodel.*;
import de.tohemi.justparty.datamodel.event.ConcreteEvent;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 24.04.2016.
 */
public class DBEventController {

    private static DBEventController instance;

    public synchronized static DBEventController getInstance() {
        if (instance == null) {
            return new DBEventController();
        }
        return instance;
    }
    private DataSource getDataSource() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-database.xml");
        return (DataSource) ctx.getBean("dataSource");
    }
    private void releaseConnection(DataSource ds, Connection c) {
        try {
            c.close();
        } catch (SQLException exp) {
        }
        DataSourceUtils.releaseConnection(c, ds);
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
            ex.printStackTrace();
            return false;
        } finally {
            releaseConnection(ds, c);
            e.setId(getEventID(e));
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

            if(psEvent.execute()) {
                psGuests.executeUpdate();
            }
            psEvent.close();
            psGuests.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            releaseConnection(ds, c);
            }
            return true;
    }

    public Event getEventById(int id) throws MalformedURLException, InvalidEmailException, ZipCodeInvalidException {

        final Event event = EventFactory.createEvent(id);
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT * FROM events WHERE event_id=?");

            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();

            while(rs.next())
            {
                event.setSpotifyPlaylistLink(rs.getURL("Spotify_link"));
                event.setGooglePlusLink((rs.getURL("googleplus_link")));
                event.setFacebookLink(rs.getURL("facebook_link"));
                event.setBegin(rs.getDate("begin"));
                event.setEnd(rs.getDate("end"));
                event.setDescription(rs.getString("description"));
                event.setEventOwner(new User(new EMail(rs.getString("email"))));
                event.setName(rs.getString("name"));
                event.setLocation(DBLocationController.getInstance().getLocationByID(rs.getInt("address_id")));
            }
            psEvent.close();
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
        } finally {
            // ignore failure closing connection
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
            while(rs.next())
                email = rs.getString("email");
            rs.close();
            pS1.close();

            PreparedStatement pS = c.prepareStatement("UPDATE events SET name=?, description=?, begin=?, end=?, address_id=?, facebook_link=?, wishlist_link=?, googleplus_link=?, Spotify_link=? WHERE event_id=?;");
            pS.setString(1, event.getName());
            pS.setString(2, event.getDescription());
            pS.setDate(3, (Date) event.getBegin());
            pS.setDate(4, (Date) event.getEnd());
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
        } catch (SQLException e) {
            e.printStackTrace();
            tries = false;
        } finally {
            releaseConnection(ds, c);
        }
        return tries;
    }

    public boolean userIsHostOfRequestedEvent(User user, Event event){
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        boolean tries = false;
        try {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM events WHERE email=? AND event_id=?;");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setInt(2, event.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                tries = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return tries;
    }

    public List<UserEventRelation> getHostedUERs(User user) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        ArrayList<UserEventRelation> userEventRelations = new ArrayList<UserEventRelation>();
        try {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT event_id, name, begin, email FROM events WHERE email = ?;");
            String email = user.getEmail();
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Event event = EventFactory.createEvent(resultSet.getInt("event_id"));
                event.setName(resultSet.getString(EventsDBTabelle.COLUMN_NAME));
                event.setEventOwner(user);
                Date date = resultSet.getDate("begin");
                if (date != null) {
                    event.setBegin(date);
                }
                userEventRelations.add(new UserEventRelation(event, user));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }

        return userEventRelations;
    }

    public ArrayList<UserEventRelation> getInvitedUERs(User user) {

        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        ArrayList<UserEventRelation> userEventRelations = new ArrayList<UserEventRelation>();
        try {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT event_id, name, begin, email, " + EventsDBTabelle.COLUMN_STATUS + " FROM events, " + GuestlistDBTabelle.TABLE + " WHERE event_id = event AND " + GuestlistDBTabelle.COLUMN_GUEST + " = ?;");
            String email = user.getEmail();
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Event event = EventFactory.createEvent(resultSet.getInt("event_id"));
                event.setName(resultSet.getString(EventsDBTabelle.COLUMN_NAME));
                event.setEventOwner(new User(resultSet.getString("email")));
                Date date = resultSet.getDate("begin");
                if (date != null) {
                    event.setBegin(date);
                }
                int status = resultSet.getInt(GuestlistDBTabelle.COLUMN_STATUS);
                Accepted accepted = GuestlistDBTabelle.getAcceptedObjectForStatus(status);
                UserEventRelation uer = new UserEventRelation(event, user, accepted);
                userEventRelations.add(uer);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return userEventRelations;
    }

    /**
     * @Deprecated
     * @param event
     * @param user
     * @param answer
     * @return boolnothing
     * use DBGuestlistContorller.getInstance().updateGuestlist(Event, User, State); instead
     */
    @Deprecated
    public boolean updateGuest(Event event, User user, Accepted answer) {
        int status = GuestlistDBTabelle.getIntStatusForAcceptedObject(answer);
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement pS = c.prepareStatement("UPDATE " + GuestlistDBTabelle.TABLE + " SET " + GuestlistDBTabelle.COLUMN_STATUS + "=? WHERE " +
                    GuestlistDBTabelle.COLUMN_EVENT + "=? AND " + GuestlistDBTabelle.COLUMN_GUEST + "=?;");
            pS.setInt(1, status);
            pS.setInt(2, event.getId());
            pS.setString(3, user.getEmail());
            pS.executeUpdate();
            pS.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            releaseConnection(ds, c);
        }
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
            while(rs.next())
                exe = rs.getInt("event_id");
            rs.close();
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            while(rs.next())
                name = rs.getString("name");
            rs.close();
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            ex.printStackTrace();
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
            while(rs.next())
                name = rs.getString("name");
            rs.close();
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }

    }

    public Date getBegin(int id) {

        Date begin = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT begin FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while(rs.next()){
                begin = new Date(rs.getDate("begin").getTime());
            }
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return begin;
    }

    public void setBegin(int id, Date begin) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET begin=? WHERE event_id=?;");
            psEvent.setDate(1, begin);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }

    }

    public Date getEnd(int id) {

        Date end = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT end FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while(rs.next()){
                end = new Date(rs.getDate("end").getTime());
            }
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return end;
    }

    public void setEnd(int id, Date end) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET end=? WHERE event_id=?;");
            psEvent.setDate(1, end);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
    }

    public Location getLocation(int id) throws ZipCodeInvalidException {

        Location location = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT address_id FROM events WHERE event_id=?;");
            PreparedStatement psLocation = c.prepareStatement("SELECT * FROM location WHERE address_id=?");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while(rs.next()){
                psLocation.setInt(1, rs.getInt("address_id"));
            }
            rs.close();
            psEvent.close();
            rs = psLocation.executeQuery();
            while(rs.next()) {
                location = new Location(rs.getString("name"), new Address(rs.getString("street"), rs.getString("house_nr"), new ZipCode(rs.getInt("zipcode")), rs.getString("city"), rs.getString("country")), rs.getBoolean("public"));
            }
            rs.close();
            psLocation.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            ex.printStackTrace();
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
            while(rs.next()){
                eo = new User(rs.getString("email"));
            }
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return eo;
    }

    public void setEventOwner(int id, Date end) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET end=? WHERE event_id=?;");
            psEvent.setDate(1, end);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
    }

    public Date getFacebookLink(int id) {

        Date end = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT end FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while(rs.next()){
                end = rs.getDate("end");
            }
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return end;
    }

    public void setFacebookLink(int id, Date end) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET end=? WHERE event_id=?;");
            psEvent.setDate(1, end);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }

    }

    public Date getSpotifyLink(int id) {

        Date end = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT end FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while(rs.next()){
                end = rs.getDate("end");
            }
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return end;
    }

    public void setSpotifyLink(int id, Date end) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET end=? WHERE event_id=?;");
            psEvent.setDate(1, end);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
    }

    public Date getGooglePlusLink(int id) {

        Date end = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT end FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while(rs.next()){
                end = rs.getDate("end");
            }
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return end;
    }

    public void setGooglePlusLink(int id, Date end) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET end=? WHERE event_id=?;");
            psEvent.setDate(1, end);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
    }

    public Date getWishlistLink(int id) {

        Date end = null;
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT end FROM events WHERE event_id=?;");
            psEvent.setInt(1, id);
            ResultSet rs = psEvent.executeQuery();
            while(rs.next()){
                end = rs.getDate("end");
            }
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return end;
    }

    public void setWishlistLink(int id, Date end) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psEvent = c.prepareStatement("UPDATE events SET end=? WHERE event_id=?;");
            psEvent.setDate(1, end);
            psEvent.setInt(2, id);
            psEvent.execute();
            psEvent.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
    }
}
