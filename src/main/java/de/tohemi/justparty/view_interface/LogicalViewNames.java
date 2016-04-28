package de.tohemi.justparty.view_interface;

/**
 * Created by xce35l7 on 07.11.2015.
 */
public class LogicalViewNames {
    private final static String welcome = "welcomepage";
    private final static String login = "login";
    private final static String register = "register";
    private final static String eventManager = "user/eventManager";
    private final static String nameCreateEvent = "user/createEvent";
    private final static String nameErrorPage = "error";
    private final static String nameInfoPage = "infopage";
    private final static String nameEditEvent = "user/editevent";
    private final static String nameAlertAnswerInvitaion = "fragments/alertanswerinvite";
    private final static String nameShowGuestlist ="user/showguests";
    private final static String nameVerifyEmail = "user/verifyEmail";
    private final static String nameInviteUser ="user/inviteUsers";

    public static String getNameWelcomePage() {
        return welcome;
    }

    public static String getNameLogin() {
        return login;
    }

    public static String getNameRegister() {
        return register;
    }

    public static String getNameEventManager() {
        return eventManager;
    }

    public static String getNameCreateEvent() {
        return nameCreateEvent;
    }

    public static String getNameErrorPage() {
        return nameErrorPage;
    }

    public static String getNameInfoPage() {
        return nameInfoPage;
    }

    public static String getNameEditEvent() {
        return nameEditEvent;
    }

    public static String getNameAlertAnswerInvitaion() {
        return nameAlertAnswerInvitaion;
    }

    public static String getNameShowGuestlist() {
        return nameShowGuestlist;
    }

    public static String getNameInviteUsers() {
        return nameInviteUser;
    }


    public static String getNameVerifyEmail() {
        return nameVerifyEmail;
    }

}