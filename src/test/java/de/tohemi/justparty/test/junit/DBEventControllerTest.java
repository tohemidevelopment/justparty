package de.tohemi.justparty.test.junit;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.database.controller.DBLocationController;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.*;
import de.tohemi.justparty.datamodel.event.ConcreteEvent;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

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
        event = EventFactory.createEvent();
        event.setName("TestEventForJUnit");
        event.setDescription("Description");
        event.setEventOwner(user);
        event.setBegin(new Timestamp(1462542000));
        event.setEnd(new Timestamp(1466542000));
        event.setSpotifyPlaylistLink(new URL("http://www.facebook.com"));
        event.setFacebookLink(new URL("http://www.facebook.com"));
        event.setWishlistLink(new URL("http://www.facebook.com"));
        event.setGooglePlusLink(new URL("http://www.facebook.com"));
        conU.addUser(user, "ROLE_USER", "1234");
        conE.addEvent(event);
        event.setId(con.getEventID(user));
        conE.setDescription(event.getId(), "Description");
        conE.setEnd(event.getId(), event.getEnd());
        conE.setBegin(event.getId(), event.getBegin());
        conE.setDescription(event.getId(), event.getDescription());
        conE.setSpotifyLink(event.getId(), new URL("http://www.facebook.com"));
        conE.setFacebookLink(event.getId(), new URL("http://www.facebook.com"));
        conE.setWishlistLink(event.getId(), new URL("http://www.facebook.com"));
        conE.setGooglePlusLink(event.getId(), new URL("http://www.facebook.com"));
        conL.addLocation(location);
        conE.setLocation(event.getId(), location);
    }

    @After
    public void tearDown() throws Exception, ZipCodeInvalidException {
        conE.deleteEvent(event, user);
        event.setId(event.getId()-1);
        conE.deleteEvent(event, user);
        conU.removeUser(user);
        conL.deleteLocation(location);
        conL.deleteLocation(new Location("TestLocation23", new Address("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"), false));
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
    public void getInvitedUERs() throws Exception {
        Assert.notNull(conE.getInvitedUERs(user));
    }

    @Test
    public void getEventID() throws Exception {
        Assert.notNull(conE.getEventID(event));
    }

    @Test
    public void getName() throws Exception {
        Assert.hasText(conE.getName(event.getId()), event.getName());
    }

    @Test
    public void setName() throws Exception {
        conE.setName(event.getId(), "TestEventForJava1");
        Assert.isTrue(conE.getName(event.getId()) != event.getName());
    }

    @Test
    public void getDescription() throws Exception {
        Assert.hasText(conE.getDescription(event.getId()), event.getDescription());
    }

    @Test
    public void setDescription() throws Exception {
        conE.setDescription(event.getId(), "TestEventForJava1");
        Assert.isTrue(conE.getDescription(event.getId()) != event.getDescription());
    }

    @Test
    public void getBegin() throws Exception {
        Assert.isTrue(event.getBegin().getTime() == conE.getBegin(event.getId()).getTime());
    }

    @Test
    public void setBegin() throws Exception {
        conE.setBegin(event.getId(), new Timestamp(1466542000));
        Assert.isTrue(conE.getBegin(event.getId()).getTime() == 1466542000);
        }

    @Test
    public void getEnd() throws Exception {
        System.out.println(event.getEnd().getTime());
        System.out.println(conE.getEnd(event.getId()).getTime());
        Assert.isTrue(event.getEnd().getTime() == conE.getEnd(event.getId()).getTime());
    }

    @Test
    public void setEnd() throws Exception {
        conE.setEnd(event.getId(), new Timestamp(1462542000));
        Assert.isTrue(conE.getEnd(event.getId()).getTime() == 1462542000);
    }

    @Test
    public void getLocation() throws Exception {
        Assert.isInstanceOf(Location.class, conE.getLocation(event.getId()));
    }

    @Test
    public void setLocation() throws Exception, ZipCodeInvalidException {
        conL.addLocation(new Location("TestLocation23", new Address("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"), false));
        conE.setLocation(event.getId(), new Location("TestLocation23", new Address("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"), false));
        Assert.isTrue(conE.getLocation(event.getId()).getName().equals("TestLocation23"));
    }

    @Test
    public void getEventOwner() throws Exception {
        Assert.hasText(event.getEventOwner().getEmail(), conE.getEventOwner(event.getId()).getEmail());
    }

    @Test
    public void setEventOwner() throws Exception {
        conE.setEventOwner(event.getId(), new User("test€tester23.de"));
        Assert.hasText(conE.getEventOwner(event.getId()).getEmail(), "test€tester23.de");
    }

    @Test
    public void getFacebookLink() throws Exception {
        Assert.hasText("www.facebook.com", conE.getFacebookLink(event.getId()).getHost());
    }

    @Test
    public void setFacebookLink() throws Exception {
        conE.setFacebookLink(event.getId(), new URL("http://www.facebook.com/123"));
        Assert.hasText("www.facebook.com/123", conE.getFacebookLink(event.getId()).getHost());
    }

    @Test
    public void getSpotifyLink() throws Exception {
        Assert.hasText("www.facebook.com", conE.getSpotifyLink(event.getId()).getHost());
    }

    @Test
    public void setSpotifyLink() throws Exception {
        conE.setFacebookLink(event.getId(), new URL("http://www.facebook.com/123"));
        Assert.hasText("www.facebook.com/123", conE.getSpotifyLink(event.getId()).getHost());

    }

    @Test
    public void getGooglePlusLink() throws Exception {
        Assert.hasText("www.facebook.com", conE.getGooglePlusLink(event.getId()).getHost());
    }

    @Test
    public void setGooglePlusLink() throws Exception {
        conE.setFacebookLink(event.getId(), new URL("http://www.facebook.com/123"));
        Assert.hasText("www.facebook.com/123", conE.getGooglePlusLink(event.getId()).getHost());

    }

    @Test
    public void getWishlistLink() throws Exception {
        Assert.hasText("www.facebook.com", conE.getWishlistLink(event.getId()).getHost());
    }

    @Test
    public void setWishlistLink() throws Exception {
        conE.setFacebookLink(event.getId(), new URL("http://www.facebook.com/123"));
        Assert.hasText("www.facebook.com/123", conE.getWishlistLink(event.getId()).getHost());

    }
}