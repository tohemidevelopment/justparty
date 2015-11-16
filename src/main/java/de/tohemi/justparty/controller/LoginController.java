package main.java.de.tohemi.justparty.controller;

import main.java.de.tohemi.justparty.view_interface.GeneralStrings;
import main.java.de.tohemi.justparty.view_interface.LogicalViewNames;
import main.java.de.tohemi.justparty.view_interface.LoginStrings;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Tom on 07.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/login")
public class LoginController  {
    @RequestMapping(method = RequestMethod.GET)
    public String printCreateEvent(@RequestParam(value = "error", required = false) String error, ModelMap model) {
        if (error != null) {
            model.addAttribute(LoginStrings.getATT_LOGIN_ERROR(),LoginStrings.getLOGIN_ERROR());
        }
        return LogicalViewNames.getNameLogin();
    }
}