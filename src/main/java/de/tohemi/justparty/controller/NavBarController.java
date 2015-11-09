package main.java.de.tohemi.justparty.controller;

import main.java.de.tohemi.justparty.view_interface.NavBarStrings;
import org.springframework.ui.ModelMap;

/**
 * Created by Micha Piertzik on 09.11.2015.
 */
public class NavBarController {
    public static void configureNavBar(ModelMap model) {
        model.addAttribute(NavBarStrings.getATT_SEARCH(), NavBarStrings.getSEARCH_PLACEHOLDER());
    }
}
