package main.java.de.tohemi.justparty.view_interface;

/**
 * Created by xce35l7 on 07.11.2015.
 */
public class LogicalViewNames {
    private static String welcome = "welcomepage";
    private static String login = "login";
    private static String register = "register";
    private static String eventManager = "user/eventManager";
    private static String nameCreateEvent = "user/createEvent";

    public static String getNameWelcomePage() {
        return welcome;
    }

    public static String getNameLogin() {
        return login;
    }

    public static String getNameRegister() {
        return register;
    }

    public static String getNameEventManager() { return eventManager; }

    public static String getNameCreateEvent() {
        return nameCreateEvent;
    }


}
