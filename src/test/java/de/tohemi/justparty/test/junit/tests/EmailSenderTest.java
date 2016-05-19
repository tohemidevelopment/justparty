package de.tohemi.justparty.test.junit.tests;

import de.tohemi.justparty.businesslogic.EmailSender;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

import static org.junit.Assert.*;

/**
 * Created by xce35l2 on 09.05.2016.
 */
public class EmailSenderTest {

    public static EmailSender es;
    public static User user;

    @BeforeClass
    public static void setUp() throws Exception {
        es = new EmailSender();
        user = UserFactory.create("junit@tester.de");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void EmailSenderTest() throws Exception{
        Assert.notNull(new EmailSender());
    }

    @Test
    public void sendEmailVerification() throws Exception {
        Assert.isTrue(es.sendEmailVerification(user, "1234567890"));
    }

    @Test
    public void sendCreateConfirmation() throws Exception {
        Assert.isTrue(es.sendCreateConfirmation(user, "TestEvent"));
    }

    @Test
    public void sendInvitationToUser() throws Exception {
        Assert.isTrue(es.sendInvitationToUser(user, user, EventFactory.createEvent()));
    }

    @Test
    public void sendInvitationToNonUser() throws Exception {
        Assert.isTrue(es.sendInvitationToNonUser(user, user, EventFactory.createEvent()));
    }

}