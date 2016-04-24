package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.datamodel.Address;
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
}
