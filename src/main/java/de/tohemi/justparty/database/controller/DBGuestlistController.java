package de.tohemi.justparty.database.controller;

import com.sun.javafx.css.Declaration;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by xce35l2 on 25.04.2016.
 */
public class DBGuestlistController {
    private static DBGuestlistController instance;

    private DBGuestlistController(){}

    public synchronized static DBGuestlistController getInstance() {
        if (instance == null) {
            return new DBGuestlistController();
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

    public boolean addGuestToEvent(Event e, User u, int status) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO guestlist(guest, event, status) VALUES (?, ?, ?);");
            ps.setString(1,u.getEmail());
            ps.setInt(2, e.getId());
            ps.setInt(3, status);
            ps.execute();
            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }
}
