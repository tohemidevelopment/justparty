package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.businesslogic.UserNotFoundException;
import de.tohemi.justparty.database.datainterfaces.DBAddress;
import de.tohemi.justparty.datamodel.UserRoles;
import de.tohemi.justparty.datamodel.address.Address;
import de.tohemi.justparty.datamodel.user.User;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Heiko on 26.12.2015.
 */
public class DBUserController extends DBControl {
    private static DBUserController instance;

    private DBUserController() {
    }

    public static synchronized DBUserController getInstance() {
        if (instance == null) {
            return new DBUserController();
        }
        return instance;
    }

    public String getLastName(String email) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT Name FROM users WHERE Email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("name");
            }
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return null;
    }

    public String getFirstName(String email) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT Firstname FROM users WHERE Email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("Firstname");
            }
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return null;
    }

    public Address getAddress(String email) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT AddressID FROM users WHERE Email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new DBAddress(rs.getInt("AddressID"));
            }
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return null;
    }

    public Date getBirthday(String email) {
        Date bd = new Date(0000, 00, 00);
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM users WHERE Email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bd = rs.getDate("Birthday");
            }

            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return bd;
    }

    public boolean setLastName(String lastName, String email) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE users SET Name=? WHERE Email=?");
            ps.setString(1, lastName);
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean setFirstName(String firstName, String email) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE users SET Firstname=? WHERE Email=?");
            ps.setString(1, firstName);
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean setAddress(DBAddress address, String email) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE users SET AddressID=? WHERE Email=?");
            ps.setInt(1, address.getId());
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean setBirthday(Date birthday, String email) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("UPDATE users SET Birthday=? WHERE Email=?");
            ps.setDate(1, birthday);
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
            c.close();
            DataSourceUtils.releaseConnection(c, ds);
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }


    public boolean verificationIDIsValid(String verificationID) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM userverification WHERE verificationID=?");
            ps.setString(1, verificationID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return false;
    }

    public boolean verifyEmail(String verificationID) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);

        try {
            String email = getEmailFromVerification(verificationID);
            PreparedStatement psEnable = c.prepareStatement("UPDATE users SET enabled=1 WHERE Email=?");
            PreparedStatement psDelete = c.prepareStatement("DELETE FROM userverification WHERE verificationID=?");
            psEnable.setString(1, email);
            psDelete.setString(1, verificationID);
            psEnable.executeUpdate();
            psDelete.executeUpdate();
            psEnable.close();
            psDelete.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    private String getEmailFromVerification(String id) {
        String email = "";
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT email FROM userverification WHERE verificationID=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
        } finally {
            releaseConnection(ds, c);
        }
        return email;
    }

    public boolean addVerificationData(String email, String verificationID) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);

        try {
            PreparedStatement ps = c.prepareStatement("INSERT INTO userverification (verificationID, email)  VALUE (?, ?)");
            ps.setString(1, verificationID);
            ps.setString(2, email);
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

    public boolean userIsRegistered(String email) throws UserNotFoundException {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement ps = c.prepareStatement("SELECT role FROM users WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new UserNotFoundException();
            } else {
                rs.beforeFirst();
            }
            if (rs.next() && rs.getString("role").equals(UserRoles.NONUSER)) {
                return false;
            }
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean addUser(User user, String userRole, String hash) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);

        try {
            PreparedStatement psUser = c.prepareStatement("INSERT INTO users (email, password, role, Birthday, Name, Firstname, AddressID) VALUE (?, ?, ?, ?, ?, ?, ?)");
            psUser.setString(1, user.getEmail());
            psUser.setString(2, hash);
            psUser.setString(3, UserRoles.USER);
            psUser.setDate(4, user.getBirthday());
            psUser.setString(5, user.getLastName());
            psUser.setString(6, user.getFirstName());
            psUser.setInt(7, user.getAddress().getID());
            psUser.executeUpdate();
            psUser.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean removeUser(User user) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);

        try {
            PreparedStatement psUser = c.prepareStatement("DELETE FROM users WHERE email=?");
            PreparedStatement psGuestlist = c.prepareStatement("DELETE FROM guestlist WHERE guest=?");
            psUser.setString(1, user.getEmail());
            psGuestlist.setString(1, user.getEmail());
            psUser.executeUpdate();
            psGuestlist.executeUpdate();
            psUser.close();
            psGuestlist.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }

    public boolean changeToUser(User user, String hash) {
        DataSource ds = getDataSource();
        Connection c = DataSourceUtils.getConnection(ds);
        try {
            PreparedStatement psUser = c.prepareStatement("UPDATE users SET Password=?, role=? WHERE Email=?");
            psUser.setString(1, hash);
            psUser.setString(2, UserRoles.USER);
            psUser.setString(3, user.getEmail());
            psUser.executeUpdate();
            psUser.close();
        } catch (SQLException ex) {
            LOGGER.logException(ex, "");
            return false;
        } finally {
            releaseConnection(ds, c);
        }
        return true;
    }
}
