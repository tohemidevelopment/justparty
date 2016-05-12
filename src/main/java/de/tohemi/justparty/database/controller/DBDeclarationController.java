package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.database.tables.DeclarationDBTabelle;
import de.tohemi.justparty.datamodel.Declaration;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.user.UserFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tom on 24.04.2016.
 */
public class DBDeclarationController extends DBControl {
    private static DBDeclarationController instance;

    private DBDeclarationController() {
    }

    public static synchronized DBDeclarationController getInstance() {
        if (instance == null) {
            return new DBDeclarationController();
        }
        return instance;
    }

    public boolean addDeclaration(Declaration d) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO declaration(name, usertobringwith, bringwithbyall, event_id) VALUES (?, ?, ?, ?);");
            ps.setString(1, d.getName());
            ps.setString(2, d.getUser().getEmail());
            ps.setBoolean(3, d.getBringWithByAll());
            ps.setInt(4, d.getEventId());
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

    public boolean deleteDeclaration(Declaration d) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("DELETE FROM declaration WHERE name=? AND usertobringwith=? AND bringwithbyall=? AND event_id=?;");
            ps.setString(1, d.getName());
            ps.setString(2, d.getUser().getEmail());
            ps.setBoolean(3, d.getBringWithByAll());
            ps.setInt(4, d.getEventId());
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

    public boolean updateDeclaration(Declaration d) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE declaration SET name=?, usertobringwith=?, bringwithbyall=? WHERE id=?;");
            ps.setString(1, d.getName());
            ps.setString(2, d.getUser().getEmail());
            ps.setBoolean(3, d.getBringWithByAll());
            ps.setInt(4, d.getEventId());
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

    public ArrayList getDeclarations(Event e) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        ArrayList<Declaration> declaration = new ArrayList<Declaration>();
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM declaration WHERE event_id=?;");
            ps.setInt(1, e.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                declaration.add(new Declaration(rs.getString(DeclarationDBTabelle.COLUMN_NAME), UserFactory.create(rs.getString(DeclarationDBTabelle.COLUMN_USER_TO_BRING_WITH)), rs.getBoolean(DeclarationDBTabelle.COLUMN_BRING_WITH_BY_ALL), rs.getInt(DeclarationDBTabelle.COLUMN_EVENT_ID)));
            }
            ps.close();

        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return declaration;
    }
}