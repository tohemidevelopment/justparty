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
        setglobalStrings(model);
        NavBarController.configureNavBar(model);

        model.addAttribute(WelcomePageStrings.getATT_CAROUSEL_IMG1(), WelcomePageStrings.getCAROUSEL_IMG1());
        model.addAttribute(WelcomePageStrings.getAttCarouselImg2(), WelcomePageStrings.getCarouselImg2());
        model.addAttribute(WelcomePageStrings.getAttCarouselImg3(), WelcomePageStrings.getCarouselImg3());
        model.addAttribute(WelcomePageStrings.getAttCarouselImg4(), WelcomePageStrings.getCarouselImg4());
      return LogicalViewNames.getNameWelcomePage();
    }


}
