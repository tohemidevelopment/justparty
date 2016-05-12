package de.tohemi.justparty.controller;

import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Micha on 06.11.2015.
 */
@org.springframework.stereotype.Controller
public class WelcomeController extends JPController{
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String printWelcomePage()
    {
      return LogicalViewNames.getNameWelcomePage();
    }
}
