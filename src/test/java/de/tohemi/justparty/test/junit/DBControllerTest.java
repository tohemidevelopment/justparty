package de.tohemi.justparty.test.junit;

import de.tohemi.justparty.database.controller.DBController;
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
    public DBController con;
    public Event event;
    public User user;
    public EMail email;


    @Before
    public void setUp() throws Exception {
        con = DBController.getInstance();
        email = new EMail("tom@wolske.tv");
        user = new User(email);
        event = new Event("TestEvent", user);
        con.addUser(user, "ROLE_USER", "1234");
        con.addEvent(event);
        event.setId(con.getEventID(user));
    }

    @After
    public void tearDown() throws Exception {
        con.deleteEvent(event, user);
        con.removeUser(user);
    }

    @Test
    public void getInstance() throws Exception {
        Assert.notNull(DBController.getInstance());
    }

    @Test
    public void addUser() throws Exception {
        con.removeUser(user);
        Assert.isTrue(con.addUser(user, "ROLE_USER", "1234"));
    }

    @Test
    public void userIsRegistered() throws Exception {
        Assert.isTrue(con.userIsRegistered("tom@wolske.tv"));
    }

    @Test
    public void addEvent() throws Exception {
        Assert.isTrue(con.addEvent(event));
    }

    @Test
    public void deleteEvent() throws Exception {

        Assert.isTrue(con.deleteEvent(event, user));
    }

    @Test
    public void getHostedUERs() throws Exception {
        Assert.notEmpty(con.getHostedUERs(user));
    }

    @Test
    public void getInvitedUsers() throws Exception {

        Assert.notNull(con.getInvitedUsers(event));

    }

    @Test
    public void getInvitedUERs() throws Exception {

        Assert.notNull(con.getInvitedUERs(user));

    }

    @Test
    public void userIsHostOfRequestedEvent() throws Exception {

        Assert.isTrue(con.userIsHostOfRequestedEvent(user, event));

    }

    @Test
    public void updateEventData() throws Exception, ZipCodeInvalidException {


        Assert.isTrue(con.updateEventData(event, user));

    }

    @Test
    public void removeUser() throws Exception {

        Assert.isTrue(con.removeUser(user));
    }
}