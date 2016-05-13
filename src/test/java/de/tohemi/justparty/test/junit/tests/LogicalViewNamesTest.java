package de.tohemi.justparty.test.junit.tests;

import de.tohemi.justparty.viewinterface.LogicalViewNames;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created by xce35l2 on 09.05.2016.
 */
public class LogicalViewNamesTest {

    private final static String welcome = "welcomepage";
    private final static String login = "login";
    private final static String register = "register";
    private final static String eventManager = "user/eventManager";
    private final static String nameCreateEvent = "user/createEvent";
    private final static String nameErrorPage = "error";
    private final static String nameInfoPage = "infopage";
    private final static String nameEditEvent = "user/editevent";
    private final static String nameEventData = "user/eventdata";
    private final static String nameAlertAnswerInvitaion = "fragments/alertanswerinvite";
    private final static String nameShowGuestlist ="user/showguests";
    private final static String nameVerifyEmail = "user/verifyEmail";
    private final static String nameInviteUser ="user/inviteUsers";


    @Test
    public void getNameWelcomePage() throws Exception {
        Assert.hasText(LogicalViewNames.getNameWelcomePage(), welcome);
    }

    @Test
    public void getNameLogin() throws Exception {
        Assert.hasText(LogicalViewNames.getNameLogin(),login);
    }

    @Test
    public void getNameRegister() throws Exception {
        Assert.hasText(LogicalViewNames.getNameRegister(), register);
    }

    @Test
    public void getNameEventManager() throws Exception {
        Assert.hasText(LogicalViewNames.getNameEventManager(),eventManager);
    }

    @Test
    public void getNameCreateEvent() throws Exception {
        Assert.hasText(LogicalViewNames.getNameCreateEvent(),nameCreateEvent);
    }

    @Test
    public void getNameErrorPage() throws Exception {
        Assert.hasText(LogicalViewNames.getNameErrorPage(),nameErrorPage);
    }

    @Test
    public void getNameInfoPage() throws Exception {
        Assert.hasText(LogicalViewNames.getNameInfoPage(),nameInfoPage);
    }

    @Test
    public void getNameEditEvent() throws Exception {
        Assert.hasText(LogicalViewNames.getNameEditEvent(), nameEditEvent);
    }

    @Test
    public void getNameAlertAnswerInvitaion() throws Exception {
        Assert.hasText(LogicalViewNames.getNameAlertAnswerInvitaion(), nameAlertAnswerInvitaion);
    }

    @Test
    public void getNameShowGuestlist() throws Exception {
        Assert.hasText(LogicalViewNames.getNameShowGuestlist(), nameShowGuestlist);
    }

    @Test
    public void getNameInviteUsers() throws Exception {
        Assert.hasText(LogicalViewNames.getNameInviteUsers(),nameInviteUser);
    }

    @Test
    public void getNameEventData() throws Exception {
        Assert.hasText(LogicalViewNames.getNameEventData(), nameEventData);
    }

    @Test
    public void getNameVerifyEmail() throws Exception {
        Assert.hasText(LogicalViewNames.getNameVerifyEmail(), nameVerifyEmail);
    }
}