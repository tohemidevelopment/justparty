package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;
import de.tohemi.justparty.database.tables.GuestlistDBTabelle;
import de.tohemi.justparty.datamodel.UserEventRelation;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public boolean updateGuestEventStatus(Event e, User u, int status) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("Update guestlist SET status=? WHERE event=? AND guest=?;");
            ps.setInt(1,status);
            ps.setInt(2, e.getId());
            ps.setString(3, u.getEmail());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean deleteGuestFromGuestlist(Event e, User u, int status) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("DELETE FROM guestlist WHERE guest=? AND event=? AND status=?");
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

    public List<UserEventRelation> getInvitedUsers(int id) {
        Event ev = null;
        try {
            ev = DBEventController.getInstance().getEventById(id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (ZipCodeInvalidException e) {
            e.printStackTrace();
        }
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        ArrayList<UserEventRelation> gl = new ArrayList<UserEventRelation>();
        try {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT " + GuestlistDBTabelle.COLUMN_GUEST + ", " + GuestlistDBTabelle.COLUMN_STATUS + " FROM guestlist WHERE " + GuestlistDBTabelle.COLUMN_EVENT + " = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                gl.add(new UserEventRelation(ev, UserFactory.create(resultSet.getString("guest")), GuestlistDBTabelle.getAcceptedObjectForStatus(resultSet.getInt("status"))));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return gl;
    }
}
