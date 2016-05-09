package de.tohemi.justparty.test.junit.dbtests;

import de.tohemi.justparty.database.controller.*;
import de.tohemi.justparty.datamodel.Address;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
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
public class DBGuestlistControllerTest {

    public DBEventController conE;
    public DBUserController conU;
    public DBLocationController conL;
    public DBGuestlistController conG;
    public DBController con;
    public Event event;
    public de.tohemi.justparty.datamodel.User user;
    public EMail email;
    public Location location;

    @Before
    public void setUp() throws Exception, ZipCodeInvalidException {
        conE = DBEventController.getInstance();
        conU = DBUserController.getInstance();
        conL = DBLocationController.getInstance();
        conG = DBGuestlistController.getInstance();
        con = DBController.getInstance();
        email = new EMail("junit@testemail.tv");
        user = new de.tohemi.justparty.datamodel.User(email);
        user.setFirstName("JUnit");
        user.setLastName("Test");
        user.setBirthday(new Date(1995, 07, 10));
        user.setAddress(new Address("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"));
        location = new Location("Testlocation", new Address("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"), false);
        event = EventFactory.createEvent();
        event.setEventOwner(user);
        event.setName("TestEvent");
        conU.addUser(user, "ROLE_USER", "1234");
        conE.addEvent(event);
        event.setId(con.getEventID(user));
        conL.addLocation(location);
    }

    @After
    public void tearDown() throws Exception {
        conG.deleteGuestFromGuestlist(event, user, 0);
        conE.deleteEvent(event, user);
        conU.removeUser(user);
        conL.deleteLocation(location);
    }

    @Test
    public void getInstance() throws Exception {
        Assert.notNull(DBGuestlistController.getInstance());
    }

    @Test
    public void addGuestToEvent() throws Exception {
        Assert.isTrue(conG.addGuestToEvent(event, user, 0));
    }

    @Test
    public void updateGuestEventStatus() throws Exception {
        conG.addGuestToEvent(event, user, 2);
        Assert.isTrue(conG.updateGuestEventStatus(event, user, 0));
    }

    @Test
    public void deleteGuestFromGuestlist() throws Exception {
        conG.addGuestToEvent(event, user, 0);
        Assert.isTrue(conG.deleteGuestFromGuestlist(event, user, 0));
    }

    @Test
    public void getGuestlist() throws Exception {
        conG.addGuestToEvent(event, user, 0);
        Assert.notEmpty(conG.getInvitedUsers(event.getId()));
    }
}