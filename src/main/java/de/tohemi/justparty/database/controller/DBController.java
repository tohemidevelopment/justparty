package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.util.SystemProperties;
import de.tohemi.justparty.util.logger.Logger;
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
public class DBController extends DBControl {
    private static DBController instance;

    private DBController() {
    }

    public synchronized static DBController getInstance() {
        if (instance == null) {
            return new DBController();
        }
        return instance;
    }

    public int getEventID(User u) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        int exe = 0;
        try {
            PreparedStatement psEvent = c.prepareStatement("SELECT event_id FROM events WHERE email=?");
            psEvent.setString(1, u.getEmail());
            ResultSet rs = psEvent.executeQuery();
            while (rs.next())
                exe = rs.getInt("event_id");
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return exe;
    }
}