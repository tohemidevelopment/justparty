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
public class WelcomeController extends Controller{
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcomePage(ModelMap model)
    {
        setglobalVariables(model);
        NavBarController.configureNavBar(model);


        model.addAttribute(GeneralStrings.getAttTitle(), WelcomePageStrings.getTITLE());
        model.addAttribute(GeneralStrings.getAttHeader(), WelcomePageStrings.getHEADER());
        model.addAttribute("description","Hier entsteht 'justParty' f&uuml;r Sie!");
        model.addAttribute(WelcomePageStrings.getATT_CAROUSEL_IMG1(), WelcomePageStrings.getCAROUSEL_IMG1());
        model.addAttribute(GeneralStrings.getATT_REGITSTER_BTN(), GeneralStrings.getREGISTER_BTN());
        model.addAttribute(GeneralStrings.getATT_LOGIN_BTN(), GeneralStrings.getLOGIN_BTN());
        return LogicalViewNames.getNameWelcomePage();
    }


}
