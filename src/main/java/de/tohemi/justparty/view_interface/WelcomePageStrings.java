package main.java.de.tohemi.justparty.view_interface;

/**
 * Created by Micha Piertzik on 09.11.2015.
 */
public class WelcomePageStrings {
    private final static String TITLE = GeneralStrings.getSlogan1();
    private final static String HEADER = "Wilkommen bei "+ GeneralStrings.getWebappTitle();
    private static final String PLACEHOLDER_IMG = "https://placeholdit.imgix.net/~text?txtsize=31&txt=Platzhalterbild&w=2700&h=600";
    private final static String ATT_CAROUSEL_IMG1 = "carouselImg1";
    private final static String CAROUSEL_IMG1 = PLACEHOLDER_IMG;
    private final static String ATT_CAROUSEL_IMG2 = "carouselImg2";
    private final static String CAROUSEL_IMG2 = PLACEHOLDER_IMG;
    private final static String ATT_CAROUSEL_IMG3 = "carouselImg3";
    private final static String CAROUSEL_IMG3 = PLACEHOLDER_IMG;
    private final static String ATT_CAROUSEL_IMG4 = "carouselImg4";
    private final static String CAROUSEL_IMG4 = PLACEHOLDER_IMG;

    public static String getTITLE() {
        return TITLE;
    }

    public static String getHEADER() {
        return HEADER;
    }

    public static String getCAROUSEL_IMG1() {
        return CAROUSEL_IMG1;
    }

    public static String getATT_CAROUSEL_IMG1() {
        return ATT_CAROUSEL_IMG1;
    }
}
