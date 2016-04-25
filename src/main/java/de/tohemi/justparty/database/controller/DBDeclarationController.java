package de.tohemi.justparty.database.controller;

import com.sun.javafx.css.Declaration;
import de.tohemi.justparty.datamodel.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    public boolean addDeclaration(Event e, Declaration d) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
                PreparedStatement ps = c.prepareStatement("INSERT INTO declaration(name, usertobringwith, bringwithbyall, event_id) VALUES (?, ?, ?, ?);");
                ps.setString(1,d.getProperty());
                ps.setString(2, d.getParsedValue().toString());
                //TODO: Get True: FORALL False: only one from declaration
                ps.setBoolean(3, false);
                ps.setInt(4, e.getId());
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
                PreparedStatement ps = c.prepareStatement("DELETE * FROM declaration WHERE name=? AND usertobringwith=? AND bringwithbyall=? AND event_id=?;");
                ps.setString(1,d.getProperty());
                ps.setString(2, d.getParsedValue().toString());
                //TODO: Get True: FORALL False: only one from declaration
                ps.setBoolean(3, false);
                ps.setInt(4, e.getId());
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

    public boolean udpateDeclaration(Event e, Declaration d) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE declaration SET name=?, usertobringwith=?, bringwithbyall=? WHERE id=?;");
            ps.setString(1,d.getProperty());
            ps.setString(2, d.getParsedValue().toString());
            //TODO: Get True: FORALL False: only one from declaration
            ps.setBoolean(3, false);
            ps.setInt(4,e.getId());
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

    public ArrayList<Declaration> getDeclarations(Event e){
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        ArrayList<Declaration> declaration = new ArrayList<Declaration>();
        try {
            PreparedStatement ps = c.prepareStatement("Select * From declaration WHERE event_id=?;");
            ps.setInt(1, e.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //TODO: CHange RS:GETSTRING("Usertobringwith") to PARSEVALUEOF
                //declaration.add(new Declaration(rs.getString("name"), rs.getString("usertobringwith"), rs.getBoolean("bringwithbyall")));
            }
            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(ds, c);
        }
        return declaration;
    }
}