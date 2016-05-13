package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.database.tables.LocationDBTabelle;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.address.ConcreteAddress;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Heiko on 26.12.2015.
 */
public class DBLocationController extends DBControl {
    private static DBLocationController instance;

    public static synchronized DBLocationController getInstance() {
        if (instance == null) {
            return new DBLocationController();
        }
        return instance;
    }

    public Location getLocationByID(int id) throws ZipCodeInvalidException {
        final Location location = new Location("NAME", null, false);
        final ConcreteAddress address = new ConcreteAddress("street", "housenumber", new ZipCode(12345), "City", "Country");
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psAddress = c.prepareStatement("SELECT * FROM location WHERE address_id=?");
            psAddress.setInt(1, id);
            ResultSet rs = psAddress.executeQuery();

            while (rs.next()) {
                location.setName(rs.getString(LocationDBTabelle.COLUMN_NAME));
                address.setStreet(rs.getString(LocationDBTabelle.COLUMN_STREET));
                address.setHouseNumber(rs.getString(LocationDBTabelle.COLUMN_HOUSENR));
                address.setZipCode(new ZipCode(rs.getInt(LocationDBTabelle.COLUMN_ZIPCODE)));
                address.setCity(rs.getString(LocationDBTabelle.COLUMN_CITY));
                address.setCountry(rs.getString(LocationDBTabelle.COLUMN_COUNTRY));
                location.setPublicLocation(rs.getBoolean(LocationDBTabelle.COLUMN_PUBLIC));
            }
            location.setAddress(address);
            psAddress.close();

        } catch (SQLException ex) {
            LOGGER.logException(ex);
        } finally {
            releaseConnection(ds, c);
        }
        return location;
    }

    public boolean addLocation(Location l) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO location(street, house_nr, city, zipcode, country, name, public) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(4, l.getAddress().getZipCode().getZipInt());
            ps.setString(1, l.getAddress().getStreet());
            ps.setInt(2, Integer.parseInt(l.getAddress().getHouseNumber()));
            ps.setBoolean(7, l.isPublicLocation());
            ps.setString(3, l.getAddress().getCity());
            ps.setString(5, l.getAddress().getCountry());
            ps.setString(6, l.getName());
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

    public boolean deleteLocation(Location l) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("DELETE FROM location WHERE address_id=?");
            ps.setInt(1, getLocationID(l));
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

    public boolean updateLocation(Location l) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE location SET street=? AND house_nr=? AND city=? AND zipcode=? AND country=? AND name=? AND public=? WHERE address_id=?");
            ps.setInt(4, l.getAddress().getZipCode().getZipInt());
            ps.setInt(2, Integer.parseInt(l.getAddress().getHouseNumber()));
            ps.setString(3, l.getAddress().getCity());
            ps.setString(1, l.getAddress().getStreet());
            ps.setBoolean(7, l.isPublicLocation());
            ps.setString(5, l.getAddress().getCountry());
            ps.setString(6, l.getName());
            ps.setInt(8, getLocationID(l));
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

    public int getLocationID(Location l) {

        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        int exe = 0;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT address_id FROM location WHERE street=? AND house_nr=? AND city=? AND zipcode=? AND country=? AND name=? AND public=?");
            ps.setString(1, l.getAddress().getStreet());
            ps.setInt(2, Integer.parseInt(l.getAddress().getHouseNumber()));
            ps.setString(3, l.getAddress().getCity());
            ps.setInt(4, l.getAddress().getZipCode().getZipInt());
            ps.setString(5, l.getAddress().getCountry());
            ps.setString(6, l.getName());
            ps.setBoolean(7, l.isPublicLocation());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                exe = rs.getInt(LocationDBTabelle.COLUMN_ADDRESS_ID);
            }
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return exe;
    }

    public String getStreet(int id) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        String street = "";
        try {
            PreparedStatement ps = c.prepareStatement("SELECT street FROM location WHERE address_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                street = rs.getString(LocationDBTabelle.COLUMN_STREET);
            }
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return street;
        }

    public void setStreet(String street, int id) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE location SET street=? WHERE address_id=?");
            ps.setInt(2, id);
            ps.setString(1, street);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public String getHouseNumber(int id) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        String housenumber = "";
        try {
            PreparedStatement ps = c.prepareStatement("SELECT house_nr FROM location WHERE address_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                housenumber = rs.getString(LocationDBTabelle.COLUMN_HOUSENR);
            }
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return housenumber;
    }

    public void setHouseNumber(String houseNumber, int id) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE location SET house_nr=? WHERE address_id=?");
            ps.setInt(2, id);
            ps.setString(1, houseNumber);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public ZipCode getZipCode(int id) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        ZipCode zipcode = null;
        try {
            PreparedStatement ps = c.prepareStatement("SELECT zipcode FROM location WHERE address_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                zipcode = new ZipCode(Integer.parseInt(rs.getString(LocationDBTabelle.COLUMN_ZIPCODE)));
            }
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } catch (ZipCodeInvalidException e) {
            LOGGER.logException(e, "");
        } finally {
            releaseConnection(ds, c);
        }
        return zipcode;
    }

    public void setZipCode(ZipCode zipCode, int id) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE location SET zipcode=? WHERE address_id=?");
            ps.setInt(2, id);
            ps.setString(1, zipCode.toString());
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public String getCity(int id) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        String city = "";
        try {
            PreparedStatement ps = c.prepareStatement("SELECT city FROM location WHERE address_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                city = rs.getString(LocationDBTabelle.COLUMN_CITY);
            }
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return city;
    }

    public void setCity(String city, int id) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE location SET city=? WHERE address_id=?");
            ps.setInt(2, id);
            ps.setString(1, city);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }

    public String getCountry(int id) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        String country = "";
        try {
            PreparedStatement ps = c.prepareStatement("SELECT country FROM location WHERE address_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                country = rs.getString(LocationDBTabelle.COLUMN_COUNTRY);
            }
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return country;
    }

    public void setCountry(String country, int id) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE location SET country=? WHERE address_id=?");
            ps.setInt(2, id);
            ps.setString(1, country);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
    }
}
