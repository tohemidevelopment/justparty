package de.tohemi.justparty.controller;

import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Tom on 07.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/login")
public class LoginController  {
    @RequestMapping(method = RequestMethod.GET)
    public String printCreateEvent(@RequestParam(value = "error", required = false) String error,@RequestParam(value = "alert_success", required = false) String alert_success, ModelMap model) {
        if (error != null) {
            model.addAttribute("login_error","Ung&uuml;ltige Logindaten!");
        }
        if (alert_success != null){
            System.out.println(alert_success);
            model.addAttribute("alert_success", alert_success);
        }
        return LogicalViewNames.getNameLogin();
    }
}