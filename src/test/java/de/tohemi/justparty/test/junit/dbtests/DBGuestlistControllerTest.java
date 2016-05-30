package de.tohemi.justparty.test.junit.dbtests;

import de.tohemi.justparty.database.controller.*;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.address.ConcreteAddress;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.user.UserFactory;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.junit.*;
import org.springframework.util.Assert;
import java.sql.Date;

/**
 * Created by xce35l2 on 28.04.2016.
 */
public class DBGuestlistControllerTest {

    public static DBEventController conE;
    public static DBUserController conU;
    public static DBLocationController conL;
    public static DBGuestlistController conG;
    public static DBController con;
    public static Event event;
    public static de.tohemi.justparty.datamodel.user.User user;
    public static EMail email;
    public static Location location;

    @BeforeClass
    public static void setUp() throws Exception {
        conE = DBEventController.getInstance();
        conU = DBUserController.getInstance();
        conL = DBLocationController.getInstance();
        conG = DBGuestlistController.getInstance();
        con = DBController.getInstance();
        email = new EMail("junit@testemail.tv");
        user = UserFactory.create(email);
        user.setFirstName("JUnit");
        user.setLastName("Test");
        user.setBirthday(new Date(1995, 07, 10));
        user.setAddress(new ConcreteAddress("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"));
        location = new Location("Testlocation", new ConcreteAddress("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"), false);
        event = EventFactory.createEvent();
        event.setEventOwner(user);
        event.setName("TestEvent");
        conU.addUser(user, "ROLE_USER", "1234");
        conE.addEvent(event);
        event.setId(con.getEventID(user));
        conL.addLocation(location);
    }


    @AfterClass
    public static void tearDown() throws Exception {
        conG.deleteGuestFromGuestlist(event, user, 0);
        conE.deleteEvent(event, user);
        conU.removeUser(user);
        conL.deleteLocation(location);
    }

    @After
    public void tearDownEachTest() throws Exception {
        conG.deleteGuestFromGuestlist(event, user, 0);
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