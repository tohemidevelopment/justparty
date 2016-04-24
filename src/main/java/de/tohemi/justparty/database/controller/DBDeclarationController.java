package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.datamodel.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            PreparedStatement ps = c.prepareStatement("CREATE TABLE 'justParty_db.declaration_" + e.getId() + "' ( id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(255) NOT NULL, usertobringwith VARCHAR(255) NOT NULL, bringwithbyall BOOL DEFAULT 0 NOT NULL );");
            ps.setString(1, e.getName());
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
            PreparedStatement ps = c.prepareStatement("DROP TABLE 'justParty_db.declaration_" + e.getId() + "';");
            ps.setString(1, e.getName());
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