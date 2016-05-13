package de.tohemi.justparty.viewinterface;

/**
 * Created by xce35l7 on 07.11.2015.
 */
public class LogicalViewNames {
    private static final String WELCOME_PAGE = "welcomepage";
    private static final String LOGIN = "login";
    private static final String REGISTER = "register";
    private static final String EVENTMANAGER = "user/eventManager";
    private static final String NAME_CREATE_EVENT = "user/createEvent";
    private static final String NAME_ERROR_PAGE = "error";
    private static final String NAME_INFO_PAGE = "infopage";
    private static final String NAME_EDIT_EVENT = "user/editevent";
    private static final String NAME_EVENT_DATA = "user/eventdata";
    private static final String NAME_ALERT_ANSWER_INVITAION = "fragments/alertanswerinvite";
    private static final String NAME_SHOW_GUESTLIST ="user/showguests";
    private static final String NAME_VERIFY_EMAIL = "user/verifyEmail";
    private static final String NAME_INVITE_USER ="user/inviteUsers";

    public static String getNameWelcomePage() {
        return WELCOME_PAGE;
    }

    public static String getNameLogin() {
        return LOGIN;
    }

    public static String getNameRegister() { return REGISTER; }

    public static String getNameEventManager() { return EVENTMANAGER; }

    public static String getNameCreateEvent() {
        return NAME_CREATE_EVENT;
    }

    public static String getNameErrorPage() {
        return NAME_ERROR_PAGE;
    }

    public static String getNameInfoPage() {
        return NAME_INFO_PAGE;
    }

    public static String getNameEditEvent() {
        return NAME_EDIT_EVENT;
    }

    public static String getNameAlertAnswerInvitaion() {
        return NAME_ALERT_ANSWER_INVITAION;
    }

    public static String getNameShowGuestlist() {
        return NAME_SHOW_GUESTLIST;
    }

    public static String getNameInviteUsers() {
        return NAME_INVITE_USER;
    }

    public static String getNameEventData() { return NAME_EVENT_DATA; }

    public static String getNameVerifyEmail() {
        return NAME_VERIFY_EMAIL;
    }

}