package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.businesslogic.UserNotFoundException;
import de.tohemi.justparty.database.tables.EventsDBTabelle;
import de.tohemi.justparty.database.tables.GuestlistDBTabelle;
import de.tohemi.justparty.datamodel.*;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Heiko on 04.11.2015.
 */
public class DBController {
    private static DBController instance;

    private DBController(){}

    public synchronized static DBController getInstance() {
        if (instance == null) {
            return new DBController();
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


    /*public User getUser(String email) {
        // Create a new application context. this processes the Spring config
        ApplicationContext ctx = new ClassPathXmlApplicationContext("WEB-INF/spring-database.xml");
        // Retrieve the data source from the application context
        DataSource ds = (DataSource) ctx.getBean("dataSource");
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            // retrieve a list of three random cities
            PreparedStatement ps = c.prepareStatement("SELECT * FROM users WHERE Email='"+email+"'");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return new User(rs.getString("Email"), rs.getString("Email"), String firstName, Address address, Calendar birthday);
            }
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            try {
                c.close();
            } catch (SQLException exp) {}
            DataSourceUtils.releaseConnection(c, ds);

        }
        return null;
    }
*/
    public boolean addEvent(Event e) {

        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO events (name, email) VALUE (?, ?)");
            ps.setString(1, e.getName());
            ps.setString(2, e.getEventOwner().getEmail());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            return false;
        } finally {
            // ignore failure closing connection
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

            if(psEvent.execute()) {
                psGuests.executeUpdate();
            }
            psEvent.close();
            psGuests.close();

        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            return false;
        } finally {
            // ignore failure closing connection
            releaseConnection(ds, c);
        }
        return true;
    }

    /**
     * @param email
     * @return Returns true, if the given Email-Address is already used by a user.
     * Returns false, if the Email-Address is in our DB but not used by a user.
     * Throws UserNotFoundException, if the Email-Address is not in our DB
     */
    public boolean userIsRegistered(String email) throws UserNotFoundException {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT role FROM users WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new UserNotFoundException();
            } else {
                rs.beforeFirst();
            }
            if (rs.next()) {
                if (rs.getString("role").equals(UserRoles.NONUSER))
                    return false;
            }
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean addUser(User user, String userRole, String hash) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);

        try {
            PreparedStatement psUser = c.prepareStatement("INSERT INTO users (email, password, role) VALUE (?, ?, ?)");
            psUser.setString(1, user.getEmail());
            psUser.setString(2, hash);
            psUser.setString(3, UserRoles.USER);
            psUser.executeUpdate();
            psUser.close();
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean removeUser(User user) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);

        try {
            PreparedStatement psUser = c.prepareStatement("DELETE FROM users WHERE email=?");
            PreparedStatement psGuestlist = c.prepareStatement("DELETE FROM guestlist WHERE guest=?");
            psUser.setString(1, user.getEmail());
            psGuestlist.setString(1, user.getEmail());
            psUser.executeUpdate();
            psGuestlist.executeUpdate();
            psUser.close();
            psGuestlist.close();
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean changeToUser(User user, String hash) {
        DataSource ds = getDataSource();

        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psUser = c.prepareStatement("UPDATE users SET Password=?, role=? WHERE Email=?");
            psUser.setString(1, hash);
            psUser.setString(2, UserRoles.USER);
            psUser.setString(3, user.getEmail());
            psUser.executeUpdate();
            psUser.close();
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
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
                    Calendar begin = Calendar.getInstance();
                    begin.setTime(date);
                    event.setBegin(begin);
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
                    Calendar begin = Calendar.getInstance();
                    begin.setTime(date);
                    event.setBegin(begin);
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

            PreparedStatement pS = c.prepareStatement("UPDATE events SET name=?, description=?, begin='2016-07-10 12:00', end='2016-07-10 12:00', address_id='2', facebook_link=?, wishlist_link=?, googleplus_link=?, Spotify_link=? WHERE event_id=?;");
            pS.setString(1, event.getName());
            pS.setString(2, event.getDescription());
            //pS.setDate(3, (Date) event.getBegin().getTime());
            //pS.setDate(4, (Date) event.getEnd().getTime());
            //pS.setString(5, event.getLocation().getAddress().toString());
            pS.setURL(3, event.getFacebookLink());
            pS.setURL(4, event.getGooglePlusLink());
            pS.setURL(5, event.getWishlistLink());
            pS.setURL(6, event.getSpotifyPlaylistLink());
            pS.setInt(7, event.getId());


            if (email.equals(user.getEmail())) {
                pS.executeUpdate();
                tries = true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            tries = false;
        } finally {
            releaseConnection(ds, c);
            return tries;
        }
    }

    public int getEventID(User u) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        int exe = 0;
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT event_id FROM events WHERE email=?");

            psEvent.setString(1, u.getEmail());
            ResultSet rs = psEvent.executeQuery();
            while(rs.next())
                exe = rs.getInt("event_id");


        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
        } finally {
            // ignore failure closing connection
            releaseConnection(ds, c);
        }
        return exe;
    }


    public Event getEventById(int id) throws MalformedURLException, InvalidEmailException, ZipCodeInvalidException {

        Event event = null;
        event.setId(id);
        //TODO: Refactor so we can use the data from database
        event.setBegin(Calendar.getInstance());
        event.setEnd(Calendar.getInstance());
        event.setLocation(new Location("Testlocation bis zum Refactorn", new Address("Test", "12", new ZipCode(12345), "Bei Micha", "in Deutschland"), true));
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
                //event.setBegin(Calendar.getInstance());
                //event.setEnd(Calendar.getInstance());
                event.setDescription(rs.getString("description"));
                event.setEventOwner(new User(new EMail(rs.getString("email"))));
                event.setName(rs.getString("name"));
                //event.setLocation();
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
}