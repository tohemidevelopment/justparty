package de.tohemi.justparty.test.junit.DBTests;

import de.tohemi.justparty.database.controller.DBDeclarationController;
import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.*;
import de.tohemi.justparty.datamodel.address.ConcreteAddress;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;
import java.sql.Date;

/**
 * Created by xce35l2 on 25.04.2016.
 */
public class DBDeclarationControllerTest {

    public DBDeclarationController conD;
    public Declaration d;
    public Event e;
    public User u;

    @Before
    public void setUp() throws Exception, ZipCodeInvalidException {
        u = UserFactory.create("junit@tester.de");
        u.setFirstName("JUnit");
        u.setLastName("Test");
        u.setBirthday(new Date(1995, 07, 10));
        u.setAddress(new ConcreteAddress("Teststraße", "12", new ZipCode(12345), "Testort", "Testland"));
        DBUserController.getInstance().addUser(u, "ROLE_USER", "1234");
        e = EventFactory.createEvent();
        e.setName("TestEvent");
        e.setEventOwner(u);
        DBEventController.getInstance().addEvent(e);
        int event_id = DBEventController.getInstance().getEventID(e);
        e.setId(event_id);
        d = new Declaration("Testdeclaration", u, false, event_id);
        conD = DBDeclarationController.getInstance();


    }

    @After
    public void tearDown() throws Exception {
        conD.deleteDeclaration(d);
        DBEventController.getInstance().deleteEvent(e, u);
        DBUserController.getInstance().removeUser(u);
    }

    @Test
    public void getInstance() throws Exception {
        Assert.notNull(DBDeclarationController.getInstance());
    }

    @Test
    public void addDeclaration() throws Exception {
        conD.deleteDeclaration(d);
        Assert.isTrue(conD.addDeclaration(d));
    }

    @Test
    public void deleteDeclaration() throws Exception {
        Assert.isTrue(conD.deleteDeclaration(d));
    }

    @Test
    public void udpateDeclaration() throws Exception {
        Assert.isTrue(conD.updateDeclaration(d));
    }

    @Test
    public void getDeclarations() throws Exception {
        conD.addDeclaration(d);
        Assert.notEmpty(conD.getDeclarations(e));
    }
}