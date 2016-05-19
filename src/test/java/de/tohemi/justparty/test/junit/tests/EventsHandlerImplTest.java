package de.tohemi.justparty.test.junit.tests;

import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.address.ConcreteAddress;
import de.tohemi.justparty.datamodel.event.ConcreteEvent;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.junit.*;
import org.springframework.util.Assert;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.*;

/**
 * Created by xce35l2 on 09.05.2016.
 */
public class EventsHandlerImplTest {
    public static EventsHandlerImpl ehi;
    public static Event event;

    @BeforeClass
    public static void setUp() throws Exception {
        ehi = new EventsHandlerImpl();

        event = EventFactory.createEvent();
        event.setName("TestEventForJUnit");
        event.setDescription("Description");
        event.setEventOwner(UserFactory.create("junit@testemail.tv"));
        event.setBegin(new Timestamp(1462542000));
        event.setEnd(new Timestamp(1466542000));
        event.setSpotifyPlaylistLink(new URL("http://www.facebook.com"));
        event.setFacebookLink(new URL("http://www.facebook.com"));
        event.setWishlistLink(new URL("http://www.facebook.com"));
        event.setGooglePlusLink(new URL("http://www.facebook.com"));
        DBEventController.getInstance().addEvent(event);
        event.setId(DBEventController.getInstance().getEventID(event));
    }

    @AfterClass
    public static void tearDown() throws Exception {
        DBEventController.getInstance().deleteEvent(event, UserFactory.create("junit@testemail.tv"));
    }

    @Test
    public void createEvent() throws Exception {

    }

    @Test
    public void deleteEvent() throws Exception {

    }

    @Test
    public void getCurrentEvents() throws Exception {

    }

    @Test
    public void userIsHostOfRequestedEvent() throws Exception {

    }

    @Test
    public void answerInvitation() throws Exception {

    }

    @Test
    public void getGuestlist() throws Exception {

    }

    @Test
    public void getEvent() throws Exception {
        Assert.isInstanceOf(Event.class, ehi.getEvent(event.getId(), "junit@testemail.tv"));
    }

    @Test
    public void updateEvent() throws Exception {
        Assert.isTrue(ehi.updateEvent(event));
    }

}