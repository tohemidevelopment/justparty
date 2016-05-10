package de.tohemi.justparty.test.junit.DBTests;

import de.tohemi.justparty.database.controller.DBLocationController;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.database.datainterfaces.DBAddress;
import de.tohemi.justparty.datamodel.Address;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserRoles;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import de.tohemi.justparty.util.IDGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;
import java.sql.Date;
import java.util.Calendar;

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
        email = new EMail(IDGenerator.generateID(10)+"@testemail.de");
        user = new User(email);
        user.setFirstName("JUnit");
        user.setLastName("Test");
        Calendar birthday=Calendar.getInstance();
        birthday.set(1995,Calendar.JULY,10);
        user.setBirthday(new Date(birthday.getTimeInMillis()));
        user.setAddress(new Address("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"));
        location = new Location("Testlocation", new Address("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"), false);
        conU.addUser(user, UserRoles.USER, "1234");
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
        Assert.isTrue(user.getBirthday().toString().equals(conU.getBirthday(user.getEmail()).toString()));
    }

    @Test
    public void userIsRegistered() throws Exception {
        Assert.isTrue(conU.userIsRegistered(user.getEmail()));
    }

    @Test
    public void addUser() throws Exception {
        conU.removeUser(user);
        Assert.isTrue(conU.addUser(user, UserRoles.USER, "12345"));
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
    public void getAddress() throws Exception  {
        Assert.notNull(DBUserController.getInstance().getAddress(email.toString()));
    }

    @Test
    public void setLastName() throws Exception  {
        DBUserController.getInstance().setLastName("TEST", email.toString());
        Assert.hasText(DBUserController.getInstance().getLastName(email.toString()));
    }

    @Test
    public void setFirstName() throws Exception  {
        DBUserController.getInstance().setFirstName("TEST", email.toString());
        Assert.hasText(DBUserController.getInstance().getFirstName(email.toString()));
    }

    @Test
    public void setAddress() throws Exception  {
        DBUserController.getInstance().setAddress(new DBAddress(4), email.toString());
        Assert.isTrue(4 == DBUserController.getInstance().getAddress(email.toString()).getId());
    }

    @Test
    public void setBirthday() throws Exception  {
        Calendar newBirthday=Calendar.getInstance();
        newBirthday.set(1995,Calendar.MAY,5);
        user.setBirthday(new Date(newBirthday.getTimeInMillis()));
        Assert.isTrue(DBUserController.getInstance().setBirthday(new Date(newBirthday.getTimeInMillis()), email.toString()));
    }
}