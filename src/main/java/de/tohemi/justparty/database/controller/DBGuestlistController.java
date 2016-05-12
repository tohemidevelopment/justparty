package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.database.tables.GuestlistDBTabelle;
import de.tohemi.justparty.datamodel.UserEventRelation;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xce35l2 on 25.04.2016.
 */
public class DBGuestlistController extends DBControl {
    private static DBGuestlistController instance;

    private DBGuestlistController() {
    }

    public static synchronized DBGuestlistController getInstance() {
        if (instance == null) {
            return new DBGuestlistController();
        }
        return instance;
    }

    public boolean addGuestToEvent(Event e, User u, int status) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO guestlist(guest, event, status) VALUES (?, ?, ?);");
            ps.setString(1, u.getEmail());
            ps.setInt(2, e.getId());
            ps.setInt(3, status);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
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
            PreparedStatement ps = c.prepareStatement("UPDATE guestlist SET status=? WHERE event=? AND guest=?;");
            ps.setInt(1, status);
            ps.setInt(2, e.getId());
            ps.setString(3, u.getEmail());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
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
            ps.setString(1, u.getEmail());
            ps.setInt(2, e.getId());
            ps.setInt(3, status);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public List<UserEventRelation> getInvitedUsers(int id) {
        Event ev = DBEventController.getInstance().getEventById(id);
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        List<UserEventRelation> gl = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT " + GuestlistDBTabelle.COLUMN_GUEST + ", " + GuestlistDBTabelle.COLUMN_STATUS + " FROM guestlist WHERE " + GuestlistDBTabelle.COLUMN_EVENT + " = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                gl.add(new UserEventRelation(ev, UserFactory.create(resultSet.getString(GuestlistDBTabelle.COLUMN_GUEST)), GuestlistDBTabelle.getAcceptedObjectForStatus(resultSet.getInt(GuestlistDBTabelle.COLUMN_STATUS))));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return gl;
    }
}
