package de.tohemi.justparty.controller;

import de.tohemi.justparty.view_interface.LogicalViewNames;
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
      return LogicalViewNames.getNameWelcomePage();
    }
}
