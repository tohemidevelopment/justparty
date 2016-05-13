package de.tohemi.justparty.test.junit.DBTests;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.database.controller.DBLocationController;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.address.ConcreteAddress;
import de.tohemi.justparty.datamodel.event.ConcreteEvent;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.junit.*;
import org.springframework.util.Assert;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by xce35l2 on 20.04.2016.
 */
public class DBEventControllerTest {

    public static DBEventController conE;
    public static DBUserController conU;
    public static DBLocationController conL;
    public static DBController con;
    public Event event;
    public static User user;
    public static EMail email;
    public static Location location;

    @BeforeClass
    public static void setUp() throws Exception {
        conE = DBEventController.getInstance();
        conU = DBUserController.getInstance();
        conL = DBLocationController.getInstance();
        con = DBController.getInstance();
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

    @Before
    public void setUpEachTest() throws Exception {
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
        conE.addEvent(event);
        event.setId(con.getEventID(user));
    }

    @AfterClass
    public static void tearDown() throws Exception {
        conU.removeUser(user);
        conL.deleteLocation(location);
    }

    @After
    public void tearDownEachMethod() throws Exception {
        conE.deleteEvent(event, user);
        event.setId(event.getId() - 1);
        conE.deleteEvent(event, user);
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
}