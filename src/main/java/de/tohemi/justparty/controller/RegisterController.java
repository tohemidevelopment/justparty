package de.tohemi.justparty.controller;

import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tom on 07.11.2015.
 */
@org.springframework.stereotype.Controller
public class RegisterController {
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String printRegisterPage(ModelMap model)
    {
        return LogicalViewNames.getNameRegister();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/perform_registry")
    public String performRegister(ModelMap model)
    {
        return LogicalViewNames.getNameErrorPage();
    }
}