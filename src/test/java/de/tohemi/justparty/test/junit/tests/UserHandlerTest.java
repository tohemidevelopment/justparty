package de.tohemi.justparty.test.junit.tests;

import de.tohemi.justparty.businesslogic.Error;
import de.tohemi.justparty.businesslogic.ErrorType;
import de.tohemi.justparty.businesslogic.user.UserHandler;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import static org.junit.Assert.*;

/**
 * Created by xce35l2 on 09.05.2016.
 */
public class UserHandlerTest {
    UserHandler uh;

    @Before
    public void setUp() throws Exception {
        uh = new UserHandler();
    }

    @After
    public void tearDown() throws Exception {
        DBUserController.getInstance().removeUser(new User(new EMail("test@test.de")));
    }

/*
    @Test
    public void createUser() throws Exception {
        Assert.isNull(uh.createUser("test@test.de", "1234", "1234", true));
    }*/

    @Test
    public void createUserWrongPassord() throws Exception {
        Assert.isTrue(uh.createUser("test@test.de", "1234", "12345", true).getType() == ErrorType.PASSWORD);
    }

    @Test
    public void createUserWrongEmail() throws Exception {
        Assert.isTrue(uh.createUser("test@testde", "1234", "12345", true).getType() == ErrorType.EMAIL);
    }

    @Test
    public void verifyEmailFALSE() throws Exception {
        Assert.isTrue(uh.verifyEmail("1234567890").getType() == ErrorType.GENERAL);
    }

    @Test
    public void verifyEmailTRUE() throws Exception {
        DBUserController.getInstance().addVerificationData("test@test.de", "1234567890");
        Assert.isNull(uh.verifyEmail("1234567890"));
    }
}