package main.java.de.tohemi.justparty.controller;

import main.java.de.tohemi.justparty.datamodel.Event;
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
    public void insertEvent(Event e, String statement){

        // Create a new application context. this processes the Spring config
        ApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
        // Retrieve the data source from the application context
        DataSource ds = (DataSource) ctx.getBean("dataSource");
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            // retrieve a list of three random cities
            PreparedStatement ps = c.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                //do something
            }
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            try { c.close(); } catch (SQLException exp) { }
        } finally {
            // properly release our connection
            DataSourceUtils.releaseConnection(c, ds);
        }
    }
}
