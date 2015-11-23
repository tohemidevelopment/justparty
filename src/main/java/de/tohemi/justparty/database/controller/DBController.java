package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public User getUser(String username) {
        try {
            return new User(new EMail("hi@f.fe"));
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(Event e) {

        // Create a new application context. this processes the Spring config
        ApplicationContext ctx = new ClassPathXmlApplicationContext("WEB-INF/spring-database.xml");
        // Retrieve the data source from the application context
        DataSource ds = (DataSource) ctx.getBean("dataSource");
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            // retrieve a list of three random cities
            PreparedStatement ps = c.prepareStatement("INSERT INTO events ('name', 'user_id') VALUE ('" + e.getName() + "', '" + e.getEventOwner().getEmail() + "')");
            ps.executeQuery();
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
            return false;
        }
        return true;
    }
}