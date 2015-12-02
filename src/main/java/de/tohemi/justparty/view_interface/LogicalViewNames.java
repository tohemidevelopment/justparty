package de.tohemi.justparty.view_interface;

/**
 * Created by xce35l7 on 07.11.2015.
 */
public class LogicalViewNames {
    public static final String REDIRECT = "redirect:";
    //Pages
    private final static String welcome = "welcomepage";
    private final static String login = "login";
    private final static String register = "register";
    private final static String eventManager = "user/eventManager";
    private final static String nameCreateEvent = "user/createEvent";
    private final static String nameErrorPage = "error";
    private final static String nameInfoPage = "infopage";
    private final static String nameEditEvent = "user/editevent";

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
}