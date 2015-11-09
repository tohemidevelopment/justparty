package main.java.de.tohemi.justparty.view_interface;

/**
 * Created by Micha Piertzik on 09.11.2015.
 */
public class WelcomePageStrings {
    private final static String TITLE = GeneralStrings.getSlogan1();
    private final static String HEADER = "Wilkommen bei "+ GeneralStrings.getWebappTitle();

    public static String getTITLE() {
        return TITLE;
    }

    public static String getHEADER() {
        return HEADER;
    }
}
