package de.tohemi.justparty.database.controller;

import com.sun.javafx.css.Declaration;
import de.tohemi.justparty.datamodel.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tom on 24.04.2016.
 */
public class DBDeclarationController {
    private static DBDeclarationController instance;

    private DBDeclarationController(){}

    public synchronized static DBDeclarationController getInstance() {
        if (instance == null) {
            return new DBDeclarationController();
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

    public boolean createDeclarationTableForEvent(Event e) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("CREATE TABLE declaration_" + e.getId() + " ( id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(255) NOT NULL, usertobringwith VARCHAR(255) NOT NULL, bringwithbyall BOOL DEFAULT 0 NOT NULL );");
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

    public boolean deleteDeclarationTableForEvent(Event e) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("DROP TABLE declaration_" + e.getId() + ";");
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

    public boolean addDeclaration(Event e, Declaration d) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
                PreparedStatement ps = c.prepareStatement("INSERT INTO declaration_" + e.getId() + "(name, usertobringwith, bringwithbyall) VALUES (?, ?, ?);");
                ps.setString(1,d.getProperty());
                ps.setString(2, d.getParsedValue().toString());
                //TODO: Get True: FORALL False: only one from declaration
                ps.setBoolean(3, false);
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

    public boolean deleteDeclaration(Event e, Declaration d) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
                PreparedStatement ps = c.prepareStatement("DELETE * FROM declaration_" + e.getId() + " WHERE name=? AND usertobringwith=? AND bringwithbyall=?;");
                ps.setString(1,d.getProperty());
                ps.setString(2, d.getParsedValue().toString());
                //TODO: Get True: FORALL False: only one from declaration
                ps.setBoolean(3, false);
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