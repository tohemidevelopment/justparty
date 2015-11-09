package main.java.de.tohemi.justparty.controller;

import main.java.de.tohemi.justparty.view_interface.GeneralStrings;
import main.java.de.tohemi.justparty.view_interface.LogicalViewNames;
import main.java.de.tohemi.justparty.view_interface.WelcomePageStrings;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Micha on 06.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/")
public class WelcomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcomePage(ModelMap model)
    {
        model.addAttribute(GeneralStrings.getAttHeader(),WelcomePageStrings.getHEADER());
        model.addAttribute(GeneralStrings.getAttTitle(),WelcomePageStrings.getTITLE());
        NavBarController.configureNavBar(model);
        model.addAttribute("description","Hier entsteht 'justParty' f&uuml;r Sie!");
        return LogicalViewNames.getNameWelcomePage();
    }
}
