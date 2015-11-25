package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.businesslogic.user.UserRoles;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public boolean add(Event e) {

        // Create a new application context. this processes the Spring config
        ApplicationContext ctx = new ClassPathXmlApplicationContext("WEB-INF/spring-database.xml");
        // Retrieve the data source from the application context
        DataSource ds = (DataSource) ctx.getBean("dataSource");
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO events (name, email) VALUE ('" + e.getName() + "', '" + e.getEventOwner().getEmail() + "')");
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

    public boolean emailAvailable(String email) {
        //TODO: Implement
        // Create a new application context. this processes the Spring config
        ApplicationContext ctx = new ClassPathXmlApplicationContext("WEB-INF/spring-database.xml");
        // Retrieve the data source from the application context
        DataSource ds = (DataSource) ctx.getBean("dataSource");
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM users WHERE email='" + email + "'");
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

    public boolean createUserInDB(User user, UserRoles userRole, String hash) {
        //TODO: Implement
        // Create a new application context. this processes the Spring config
        ApplicationContext ctx = new ClassPathXmlApplicationContext("WEB-INF/spring-database.xml");
        // Retrieve the data source from the application context
        DataSource ds = (DataSource) ctx.getBean("dataSource");
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO users (email,password,role) VALUE ('" + user.getEmail() + "', '" + hash + "','" + userRole.toString() + "')");
            ps.executeUpdate();
            ps.close();
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

    private void releaseConnection(DataSource ds, Connection c) {
        try {
            c.close();
        } catch (SQLException exp) {}
        DataSourceUtils.releaseConnection(c, ds);
    }
}