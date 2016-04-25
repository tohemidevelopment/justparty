package de.tohemi.justparty.test.junit;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created by xce35l2 on 20.04.2016.
 */
public class DBControllerTest {
    public DBEventController conE;
    public DBUserController conU;
    public DBController con;
    public Event event;
    public User user;
    public EMail email;

    @Before
    public void setUp() throws Exception {
        conE = DBEventController.getInstance();
        conU = DBUserController.getInstance();
        con = DBController.getInstance();
        email = new EMail("tom@wolske.tv");
        user = new User(email);
        event = new Event("TestEvent", user);
        conU.addUser(user, "ROLE_USER", "1234");
        conE.addEvent(event);
        event.setId(con.getEventID(user));
    }

    @After
    public void tearDown() throws Exception {
        conE.deleteEvent(event, user);
        conU.removeUser(user);
    }

    @Test
    public void getInstance() throws Exception {
        Assert.notNull(DBController.getInstance());
    }

    @Test
    public void addUser() throws Exception {
        conU.removeUser(user);
        Assert.isTrue(conU.addUser(user, "ROLE_USER", "1234"));
    }

    @Test
    public void userIsRegistered() throws Exception {
        Assert.isTrue(conU.userIsRegistered("tom@wolske.tv"));
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
    public void userIsHostOfRequestedEvent() throws Exception {

        Assert.isTrue(conE.userIsHostOfRequestedEvent(user, event));

    }

    @Test
    public void updateEventData() throws Exception, ZipCodeInvalidException {


        Assert.isTrue(conE.updateEventData(event, user));

    }

    @Test
    public void removeUser() throws Exception {

        Assert.isTrue(conU.removeUser(user));
    }
}