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
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException exp) {
            System.out.println(exp.toString());
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
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return exe;
    }
}