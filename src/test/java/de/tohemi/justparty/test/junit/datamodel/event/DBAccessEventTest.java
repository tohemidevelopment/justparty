package de.tohemi.justparty.test.junit.datamodel.event;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.database.controller.DBLocationController;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.address.ConcreteAddress;
import de.tohemi.justparty.datamodel.event.DBAccessEvent;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Created by Tom on 12.05.2016.
 */
public class DBAccessEventTest {

    public DBEventController conE;
    public DBLocationController conL;
    public DBController con;
    public Event event;
    public User user;
    public EMail email;
    public Location location;
    public DBAccessEvent DBAE;

    @Before
    public void setUp() throws Exception {
        conE = DBEventController.getInstance();
        conL = DBLocationController.getInstance();
        con = DBController.getInstance();
        email = new EMail("junit@testemail.tv");
        user = UserFactory.create(email);
        user.setFirstName("JUnit");
        user.setLastName("Test");
        user.setBirthday(new Date(1995, 07, 10));
        user.setAddress(new ConcreteAddress("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"));
        location = new Location("Testlocation", new ConcreteAddress("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"), false);
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
        conL.addLocation(location);
        conE.addEvent(event);
        event.setId(con.getEventID(user));
        conE.updateEventData(event, user, location);
        DBAE = new DBAccessEvent(event.getId());
    }

    @After
    public void tearDown() throws Exception {
        conE.deleteEvent(event, user);
        conL.deleteLocation(location);
        conL.deleteLocation(new Location("TestLocation23", new ConcreteAddress("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"), false));
    }

    @Test
    public void getName() throws Exception {
        Assert.hasText(DBAE.getName(), event.getName());
    }

    @Test
    public void setName() throws Exception {
        DBAE.setName("TestEventForJava1");
        Assert.isTrue(!DBAE.getName().equals(event.getName()));
    }

    @Test
    public void getGuests() throws Exception {

    }

    @Test
    public void setGuests() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        Assert.isTrue(DBAE.getId() == event.getId());
    }

    @Test
    public void setId() throws Exception {

    }

    @Test
    public void getDescription() throws Exception {
        Assert.hasText(DBAE.getDescription(), event.getDescription());
    }

    @Test
    public void setDescription() throws Exception {
        DBAE.setDescription("TestEventForJava1");
        Assert.isTrue(!Objects.equals(DBAE.getDescription(), event.getDescription()));
    }

    @Test
    public void getBegin() throws Exception {
        Assert.isTrue(event.getBegin().getTime() == DBAE.getBegin().getTime());
    }

    @Test
    public void setBegin() throws Exception {
        DBAE.setBegin(new Timestamp(1466542000));
        Assert.isTrue(DBAE.getBegin().getTime() == 1466542000);
    }

    @Test
    public void getEnd() throws Exception {
        Assert.isTrue(event.getEnd().getTime() == DBAE.getEnd().getTime());
    }

    @Test
    public void setEnd() throws Exception {
        DBAE.setEnd(new Timestamp(1462542000));
        Assert.isTrue(DBAE.getEnd().getTime() == 1462542000);
    }

    @Test
    public void getLocation() throws Exception {
        Assert.isInstanceOf(Location.class, DBAE.getLocation());
    }

    @Test
    public void setLocation() throws Exception {
        conL.addLocation(new Location("TestLocation23", new ConcreteAddress("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"), false));
        DBAE.setLocation(new Location("TestLocation23", new ConcreteAddress("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"), false));
        Assert.isTrue(DBAE.getLocation().getName().equals("TestLocation23"));
    }

    @Test
    public void getEventOwner() throws Exception {
        Assert.hasText(event.getEventOwner().getEmail(), DBAE.getEventOwner().getEmail());
    }

    @Test
    public void setEventOwner() throws Exception {
        DBAE.setEventOwner(UserFactory.create("test€tester23.de"));
        Assert.hasText(DBAE.getEventOwner().getEmail(), "test€tester23.de");
    }

    @Test
    public void getFacebookLink() throws Exception {
        Assert.hasText("www.facebook.com", DBAE.getFacebookLink().getHost());
    }

    @Test
    public void setFacebookLink() throws Exception {
        DBAE.setFacebookLink(new URL("http://www.facebook.com/123"));
        Assert.hasText("www.facebook.com/123", DBAE.getFacebookLink().getHost());
    }

    @Test
    public void getSpotifyLink() throws Exception {
        Assert.hasText("www.facebook.com", DBAE.getSpotifyPlaylistLink().getHost());
    }

    @Test
    public void setSpotifyLink() throws Exception {
        DBAE.setFacebookLink(new URL("http://www.facebook.com/123"));
        Assert.hasText("www.facebook.com/123", DBAE.getSpotifyPlaylistLink().getHost());
    }

    @Test
    public void getGooglePlusLink() throws Exception {
        Assert.hasText("www.facebook.com", DBAE.getGooglePlusLink().getHost());
    }

    @Test
    public void setGooglePlusLink() throws Exception {
        DBAE.setFacebookLink(new URL("http://www.facebook.com/123"));
        Assert.hasText("www.facebook.com/123", DBAE.getGooglePlusLink().getHost());
    }

    @Test
    public void getWishlistLink() throws Exception {
        Assert.hasText("www.facebook.com", DBAE.getWishlistLink().getHost());
    }

    @Test
    public void setWishlistLink() throws Exception {
        DBAE.setFacebookLink(new URL("http://www.facebook.com/123"));
        Assert.hasText("www.facebook.com/123", DBAE.getWishlistLink().getHost());
    }
}