package de.tohemi.justparty.test.junit;

import de.tohemi.justparty.database.controller.DBDeclarationController;
import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.Declaration;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created by xce35l2 on 25.04.2016.
 */
public class DBDeclarationControllerTest {

    public DBDeclarationController conD;
    public Declaration d;
    public Event e;
    public User u;

    @Before
    public void setUp() throws Exception {
        u = new User("junit@tester.de");
        DBUserController.getInstance().addUser(u, "ROLE_USER", "1234");
        e = new Event("TestEvent", u);
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