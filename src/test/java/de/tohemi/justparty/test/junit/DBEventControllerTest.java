package de.tohemi.justparty.test.junit;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.database.controller.DBLocationController;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.*;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import java.sql.Date;

import static org.junit.Assert.*;

/**
 * Created by xce35l2 on 20.04.2016.
 */
public class DBEventControllerTest {

    public DBEventController conE;
    public DBUserController conU;
    public DBLocationController conL;
    public DBController con;
    public Event event;
    public User user;
    public EMail email;
    public Location location;

    @Before
    public void setUp() throws Exception, ZipCodeInvalidException {
        conE = DBEventController.getInstance();
        conU = DBUserController.getInstance();
        conL = DBLocationController.getInstance();
        con = DBController.getInstance();
        email = new EMail("junit@testemail.tv");
        user = new User(email);
        user.setFirstName("JUnit");
        user.setLastName("Test");
        user.setBirthday(new Date(1995, 07, 10));
        user.setAddress(new Address("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"));
        location = new Location("Testlocation", new Address("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"), false);
        event = new ConcreteEvent("TestEventForJUnit", user);
        conU.addUser(user, "ROLE_USER", "1234");
        conE.addEvent(event);
        event.setId(con.getEventID(user));
        conL.addLocation(location);
    }

    @After
    public void tearDown() throws Exception {
        conE.deleteEvent(event, user);
        event.setId(event.getId()-1);
        conE.deleteEvent(event, user);
        conU.removeUser(user);
        conL.deleteLocation(location);
    }

    @Test
    public void getInstance() throws Exception {
        Assert.notNull(DBEventController.getInstance());
    }

    @Test
    public void addEvent() throws Exception {
        Assert.isTrue(conE.addEvent(event));
    }

    @Test
    public void deleteEvent() throws Exception {
        Assert.isTrue(conE.deleteEvent(event, user));
    }

    @Test
    public void getEventById() throws Exception, ZipCodeInvalidException {
        Assert.isInstanceOf(ConcreteEvent.class, conE.getEventById(con.getEventID(user)));
    }

    @Test
    public void updateEventData() throws Exception {
        Assert.isTrue(conE.updateEventData(event, user, location));
    }

    @Test
    public void userIsHostOfRequestedEvent() throws Exception {
        Assert.isTrue(conE.userIsHostOfRequestedEvent(user, event));
    }

    @Test
    public void getHostedUERs() throws Exception {
        Assert.notEmpty(conE.getHostedUERs(user));
    }

    @Test
    public void getInvitedUsers() throws Exception {
        Assert.notNull(conE.getInvitedUsers(event));
    }

    @Test
    public void getInvitedUERs() throws Exception {
        Assert.notNull(conE.getInvitedUERs(user));
    }

    @Test
    public void getEventID() throws Exception {
        Assert.notNull(conE.getEventID(event));
    }

}