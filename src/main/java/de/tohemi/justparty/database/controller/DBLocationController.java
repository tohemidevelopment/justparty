package de.tohemi.justparty.database.controller;

import com.sun.javafx.css.Declaration;
import de.tohemi.justparty.datamodel.Address;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Heiko on 26.12.2015.
 */
public class DBLocationController {
    private static DBLocationController instance;

    private DBLocationController(){}

    public synchronized static DBLocationController getInstance() {
        if (instance == null) {
            return new DBLocationController();
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

    public Location getLocationByID(int id) throws ZipCodeInvalidException {
        final Location location = new Location("NAME", null, false);
        final Address address = new Address("street", "housenumber", new ZipCode(12345), "City", "Country");
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psAddress = c.prepareStatement("SELECT * FROM location WHERE address_id=?");
            psAddress.setInt(1, id);
            ResultSet rs = psAddress.executeQuery();

            while(rs.next())
            {
                location.setName(rs.getString("name"));
                address.setStreet(rs.getString("street"));
                address.setHouseNumber(rs.getString("house_nr"));
                address.setZipCode(new ZipCode(rs.getInt("zipcode")));
                address.setCity(rs.getString("city"));
                address.setCountry(rs.getString("country"));
                location.setPublicLocation(rs.getBoolean("public"));
            }
            location.setAddress(address);

        } catch (SQLException ex) {
            // something has failed and we print a stack trace to analyse the error
            ex.printStackTrace();
        } finally {
            // ignore failure closing connection
            releaseConnection(ds, c);
        }
        return location;
    }

    public boolean addLocation(Location l) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO location(street, house_nr, city, zipcode, country, name, public) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1,l.getAddress().getStreet());
            ps.setInt(2, Integer.parseInt(l.getAddress().getHouseNumber()));
            ps.setString(3, l.getAddress().getCity());
            ps.setInt(4, l.getAddress().getZipCode().getZipInt());
            ps.setString(5, l.getAddress().getCountry());
            ps.setString(6, l.getName());
            ps.setBoolean(7, l.isPublicLocation());
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

    public boolean deleteLocation(Location l) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("DELETE * FROM location WHERE address_id=?");
            ps.setInt(1, getLocationID(l));
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

    public boolean updateLocation(Location l) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE location SET street=? AND house_nr=? AND city=? AND zipcode=? AND country=? AND name=? AND public=? WHERE address_id=?");
            ps.setString(1,l.getAddress().getStreet());
            ps.setInt(2, Integer.parseInt(l.getAddress().getHouseNumber()));
            ps.setString(3, l.getAddress().getCity());
            ps.setInt(4, l.getAddress().getZipCode().getZipInt());
            ps.setString(5, l.getAddress().getCountry());
            ps.setString(6, l.getName());
            ps.setBoolean(7, l.isPublicLocation());
            ps.setInt(8, getLocationID(l));
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

    private int getLocationID(Location l) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        int exe = 0;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT address_id FROM location Where street=? AND house_nr=? AND city=? AND zipcode=? AND country=? AND name=? AND public=?");
            ps.setString(1,l.getAddress().getStreet());
            ps.setInt(2, Integer.parseInt(l.getAddress().getHouseNumber()));
            ps.setString(3, l.getAddress().getCity());
            ps.setInt(4, l.getAddress().getZipCode().getZipInt());
            ps.setString(5, l.getAddress().getCountry());
            ps.setString(6, l.getName());
            ps.setBoolean(7, l.isPublicLocation());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                exe = rs.getInt("address_id");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return exe;
        } finally {
            releaseConnection(ds, c);
        }
        return exe;
    }
}
