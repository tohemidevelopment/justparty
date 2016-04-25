package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.database.tables.EventsDBTabelle;
import de.tohemi.justparty.database.tables.GuestlistDBTabelle;
import de.tohemi.justparty.datamodel.Accepted;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserEventRelation;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.wrapper.EMail;
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
        if(DBDeclarationController.getInstance().createDeclarationTableForEvent(e))
            return true;
        return false;
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
        if(DBDeclarationController.getInstance().deleteDeclarationTableForEvent(e))
            return true;
        return false;
    }

    public Event getEventById(int id) throws MalformedURLException, InvalidEmailException, ZipCodeInvalidException {

        final Event event = new Event(id);
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
                event.setLocation(DBLocationController.getInstance().getLocationByID(rs.getInt("location")));
            }
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
        } finally {
            // ignore failure closing connection
            releaseConnection(ds, c);
        }
        return event;
    }

    public boolean updateEventData(Event event, User user) {
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

            PreparedStatement pS = c.prepareStatement("UPDATE events SET name=?, description=?, begin=?, end=?, address_id=?, facebook_link=?, wishlist_link=?, googleplus_link=?, Spotify_link=? WHERE event_id=?;");
            pS.setString(1, event.getName());
            pS.setString(2, event.getDescription());
            pS.setDate(3, (Date) event.getBegin());
            pS.setDate(4, (Date) event.getEnd());
            //TODO: Create a Method to get the LocationID from Location and save the location in the location database
            pS.setInt(5, event.getLocation().getID());
            pS.setURL(6, event.getFacebookLink());
            pS.setURL(7, event.getGooglePlusLink());
            pS.setURL(8, event.getWishlistLink());
            pS.setURL(9, event.getSpotifyPlaylistLink());
            pS.setInt(10, event.getId());

            if (email.equals(user.getEmail())) {
                pS.executeUpdate();
                tries = true;
            }
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

                Event event = new Event(resultSet.getString(EventsDBTabelle.COLUMN_NAME), user);
                event.setId(resultSet.getInt("event_id"));
                Date date = resultSet.getDate("begin");
                if (date != null) {
                    event.setBegin(date);
                }
                userEventRelations.add(new UserEventRelation(event, user));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }

        return userEventRelations;
    }

    public List<UserEventRelation> getInvitedUsers(Event event) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        ArrayList<UserEventRelation> gl = new ArrayList<UserEventRelation>();
        try {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT " + GuestlistDBTabelle.COLUMN_GUEST + ", " + GuestlistDBTabelle.COLUMN_STATUS + " FROM guestlist WHERE " + GuestlistDBTabelle.COLUMN_EVENT + " = ?;");
            preparedStatement.setInt(1, event.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                gl.add(new UserEventRelation(event, new User(resultSet.getString("guest")), GuestlistDBTabelle.getAcceptedObjectForStatus(resultSet.getInt("status"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }

        return gl;
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

                Event event = new Event(resultSet.getString(EventsDBTabelle.COLUMN_NAME), new User(resultSet.getString("email")));
                event.setId(resultSet.getInt("event_id"));
                Date date = resultSet.getDate("begin");
                if (date != null) {
                    event.setBegin(date);
                }
                int status = resultSet.getInt(GuestlistDBTabelle.COLUMN_STATUS);
                Accepted accepted = GuestlistDBTabelle.getAcceptedObjectForStatus(status);
                UserEventRelation uer = new UserEventRelation(event, user, accepted);
                userEventRelations.add(uer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return userEventRelations;
    }

    public boolean updateGuest(Event event, User user, Accepted answer) {
        int status = GuestlistDBTabelle.getIntStatusForAcceptedObject(answer);
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement pS = c.prepareStatement("UPDATE " + GuestlistDBTabelle.TABLE + " SET " + GuestlistDBTabelle.COLUMN_STATUS + "=? WHERE " +
                    GuestlistDBTabelle.COLUMN_EVENT + "=? AND " + GuestlistDBTabelle.COLUMN_GUEST + "=?;");
            pS.setInt(1, status);
            pS.setInt(2, event.getId());
            pS.setString(3, user.getEmail());
            pS.executeUpdate();
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
            PreparedStatement psEvent = c.prepareStatement("SELECT event_id FROM events WHERE name=?;");
            psEvent.setString(1, e.getName());
            ResultSet rs = psEvent.executeQuery();
            while(rs.next())
                exe = rs.getInt("event_id");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return exe;
    }
}
