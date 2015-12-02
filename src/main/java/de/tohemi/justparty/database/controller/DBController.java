package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.businesslogic.UserNotFoundException;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserRoles;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heiko on 04.11.2015.
 */
public class DBController {
    private static DBController instance;

    public synchronized static DBController getInstance() {
        if (instance == null)
        {
            return new DBController();
        }
        return instance;
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
            ps.setString(1,e.getName());
            ps.setString(2,e.getEventOwner().getEmail());
            ps.executeUpdate();
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            return false;
        }
        finally {
            // ignore failure closing connection
            releaseConnection(ds, c);
        }
        return true;
    }
    /**
     * @param email
     * @return
     * Returns true, if the given Email-Address is already used by a user.
     * Returns false, if the Email-Address is in our DB but not used by a user.
     * Throws UserNotFoundException, if the Email-Address is not in our DB
     */
    public boolean userIsRegistered(String email)throws UserNotFoundException{
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM user_roles WHERE email=?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(!rs.next())
            {
                throw new UserNotFoundException();
            }else{
                rs.beforeFirst();
            }
            if(rs.next()){
                if(rs.getString("role").equals(UserRoles.NONUSER))
                    return false;
            }
        }catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
        }
        finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    /**
     * Deprecated, use userIsRegistered instead.
     * @param email
     * @return
     */
    @Deprecated
    public boolean emailAvailable(String email) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM users WHERE email=?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return rs.getInt(1)== 0;
            }

        }catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
        }
        finally {
            releaseConnection(ds, c);
        }
        return false;
    }

    public boolean addUser(User user, String userRole, String hash) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);

        try {
            PreparedStatement psUser = c.prepareStatement("INSERT INTO users (email, password) VALUE (?, ?)");
            PreparedStatement psUserRole = c.prepareStatement("INSERT INTO user_roles (role, Email) VALUE (?,?)");
            psUser.setString(1, user.getEmail());
            psUser.setString(2, hash);
            psUserRole.setString(1, userRole);
            psUserRole.setString(2, user.getEmail());
            psUser.executeUpdate();
            psUser.close();
            psUserRole.executeUpdate();
            psUserRole.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            return false;
        }
        finally {
            releaseConnection(ds, c);
        }
        return true;
    }
    public boolean changeToUser(User user, String hash) {
        DataSource ds = getDataSource();

        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psUser = c.prepareStatement("UPDATE users SET Password=? WHERE Email=?");
            PreparedStatement psUserRole = c.prepareStatement("UPDATE user_roles SET role=? WHERE Email=?");
            psUser.setString(1, hash);
            psUser.setString(2, user.getEmail());
            psUserRole.setString(1, UserRoles.USER);
            psUserRole.setString(2, user.getEmail());
            psUser.executeUpdate();
            psUser.close();
            psUserRole.executeUpdate();
            psUserRole.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            return false;
        }
        finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    private DataSource getDataSource() {
        // Create a new application context. this processes the Spring config
        ApplicationContext ctx = new ClassPathXmlApplicationContext("WEB-INF/spring-database.xml");
        // Retrieve the data source from the application context
        return (DataSource) ctx.getBean("dataSource");
    }

    private void releaseConnection(DataSource ds, Connection c) {
        try {
            c.close();
        } catch (SQLException exp) {}
        DataSourceUtils.releaseConnection(c, ds);
    }


    public List<Event> getHostedEventsLightweight(User user) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            //TODO: Implement
            PreparedStatement preparedStatement = c.prepareStatement("SELECT event_id, name, begin, email ....");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            releaseConnection(ds, c);
        }

        return new ArrayList<Event>();
    }
}