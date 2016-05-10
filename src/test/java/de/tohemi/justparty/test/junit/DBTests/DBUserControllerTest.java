package de.tohemi.justparty.test.junit.dbtests;

import de.tohemi.justparty.database.controller.DBLocationController;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.database.datainterfaces.DBAddress;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.address.ConcreteAddress;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import java.sql.Date;

/**
 * Created by xce35l2 on 28.04.2016.
 */
public class DBUserControllerTest {

    public DBUserController conU;
    public DBLocationController conL;
    public User user;
    public EMail email;
    public Location location;

    @Before
    public void setUp() throws Exception, ZipCodeInvalidException {
        conU = DBUserController.getInstance();
        conL = DBLocationController.getInstance();
        email = new EMail("junit@testemail.tv");
        user = UserFactory.create(email);
        user.setFirstName("JUnit");
        user.setLastName("Test");
        user.setBirthday(new Date(1995, 07, 10));
        user.setAddress(new ConcreteAddress("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"));
        location = new Location("Testlocation", new ConcreteAddress("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"), false);
        conU.addUser(user, "ROLE_USER", "1234");
        conL.addLocation(location);
    }

    @After
    public void tearDown() throws Exception {
        conU.removeUser(user);
        conL.deleteLocation(location);
    }

    @Test
    public void getInstance() throws Exception {
        Assert.notNull(DBUserController.getInstance());
    }

    @Test
    public void getLastName() throws Exception {
        Assert.isTrue(user.getLastName().equals(conU.getLastName(email.toString())));
    }

    @Test
    public void getFirstName() throws Exception {
        Assert.isTrue(user.getFirstName().equals(conU.getFirstName(email.toString())));
    }

    @Test
    public void getBirthday() throws Exception {
        Assert.isTrue(user.getBirthday().equals(conU.getBirthday(user.getEmail())));
    }

    @Test
    public void userIsRegistered() throws Exception {
        Assert.isTrue(conU.userIsRegistered(user.getEmail()));
    }

    @Test
    public void addUser() throws Exception {
        conU.removeUser(user);
        Assert.isTrue(conU.addUser(user, "ROLE_USER", "12345"));
    }

    @Test
    public void removeUser() throws Exception {
        Assert.isTrue(conU.removeUser(user));
    }

    @Test
    public void changeToUser() throws Exception {
        Assert.isTrue(conU.changeToUser(user, "12345"));
    }

    @Test
    public void getAddress() throws Exception {
        Assert.notNull(DBUserController.getInstance().getAddress(email.toString()));
    }

    @Test
    public void setLastName() throws Exception {
        DBUserController.getInstance().setLastName("TEST", email.toString());
        Assert.hasText(DBUserController.getInstance().getLastName(email.toString()));
    }

    @Test
    public void setFirstName() throws Exception {
        DBUserController.getInstance().setFirstName("TEST", email.toString());
        Assert.hasText(DBUserController.getInstance().getFirstName(email.toString()));
    }

    @Test
    public void setAddress() throws Exception {
        DBUserController.getInstance().setAddress(new DBAddress(4), email.toString());
        Assert.isTrue(4 == DBUserController.getInstance().getAddress(email.toString()).getID());
    }

    @Test
    public void setBirthday() throws Exception {
        Assert.isTrue(DBUserController.getInstance().setBirthday(new Date(2016, 05, 05), email.toString()));
    }
}