package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.database.datainterfaces.DBAddress;
import de.tohemi.justparty.datamodel.Address;
import de.tohemi.justparty.util.DateFormater;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Heiko on 26.12.2015.
 */
public class DBUserController {
    private static DBUserController instance;

    private DBUserController(){}

    public synchronized static DBUserController getInstance() {
        if (instance == null) {
            return new DBUserController();
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
        } catch (SQLException exp) {
        }
        DataSourceUtils.releaseConnection(c, ds);
    }

    public String getLastName(String email){
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            // retrieve a list of three random cities
            PreparedStatement ps = c.prepareStatement("SELECT Name FROM users WHERE Email=?");
            ps.setString(1, email);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return rs.getString("name");
            }
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            try {
                c.close();
            } catch (SQLException exp) {}
            DataSourceUtils.releaseConnection(c, ds);

        }
        return null;
    }

    public String getFirstName(String email){
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            // retrieve a list of three random cities
            PreparedStatement ps = c.prepareStatement("SELECT Firstname FROM users WHERE Email=?");
            ps.setString(1, email);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return rs.getString("Firstname");
            }
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            try {
                c.close();
            } catch (SQLException exp) {}
            DataSourceUtils.releaseConnection(c, ds);

        }
        return null;
    }

    public DBAddress getAddress(String email){
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            // retrieve a list of three random cities
            PreparedStatement ps = c.prepareStatement("SELECT AddressID FROM users WHERE Email=?");
            ps.setString(1, email);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return new DBAddress(rs.getInt("AddressID"));
            }
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            try {
                c.close();
            } catch (SQLException exp) {}
            DataSourceUtils.releaseConnection(c, ds);

        }
        return null;
    }

    public Calendar getBirthday(String email){
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            // retrieve a list of three random cities
            PreparedStatement ps = c.prepareStatement("SELECT Name FROM users WHERE Email=?");
            ps.setString(1, email);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String[] date=rs.getString("Birthday").split("\\.");
                Calendar bd =new GregorianCalendar(Integer.valueOf(date[0]),Integer.valueOf(date[1]),Integer.valueOf(date[2]));
                return bd;
            }
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            try {
                c.close();
            } catch (SQLException exp) {}
            DataSourceUtils.releaseConnection(c, ds);

        }
        return null;
    }

    public boolean setLastName(String lastName, String email) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            // retrieve a list of three random cities
            PreparedStatement ps = c.prepareStatement("UPDATE users SET Name=? WHERE Email=?");
            ps.setString(1, lastName);
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean setFirstName(String firstName, String email) {DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            // retrieve a list of three random cities
            PreparedStatement ps = c.prepareStatement("UPDATE users SET Firstname=? WHERE Email=?");
            ps.setString(1, firstName);
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;

    }

    public boolean setAddress(DBAddress address, String email) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            // retrieve a list of three random cities
            PreparedStatement ps = c.prepareStatement("UPDATE users SET AddressID=? WHERE Email=?");
            ps.setInt(1, address.getId());
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean setBirthday(Calendar birthday, String email) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            // retrieve a list of three random cities
            PreparedStatement ps = c.prepareStatement("UPDATE users SET Birthday=? WHERE Email=?");
            ps.setString(1, DateFormater.formatDate(birthday));
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }


    public boolean verificationIDIsValid(String verificationID) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            // retrieve a list of three random cities
            PreparedStatement ps = c.prepareStatement("SELECT * FROM userverification WHERE verificationID=?");
            ps.setString(1, verificationID);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return false;
    }

    public boolean verifyEmail(String verificationID) {
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);

        try {
            String email = getEmailFromVerification(c,verificationID);
            PreparedStatement psEnable=c.prepareStatement("UPDATE users SET enabled=1 WHERE Email=?");
            PreparedStatement psDelete = c.prepareStatement("DELETE FROM userverification WHERE verificationID=?");
            psEnable.setString(1, email);
            psDelete.setString(1, verificationID);
            psEnable.executeUpdate();
            psDelete.executeUpdate();
            psEnable.close();
            psDelete.close();
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    private String getEmailFromVerification(Connection c, String id) throws SQLException {
        PreparedStatement ps=c.prepareStatement("SELECT email FROM userverification WHERE verificationID=?");
        ps.setString(1, id);
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
            return rs.getString("email");
        }
        return "";
    }

    public boolean addVerificationData(String email, String verificationID){
        DataSource ds = getDataSource();
        // Open a database connection using Spring's DataSourceUtils
        Connection c = DataSourceUtils.getConnection(ds);

        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO userverification (verificationID, email)  VALUE (?, ?)");
            ps.setString(1, verificationID);
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
            // ignore failure closing connection
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }
}
