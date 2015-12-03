package de.tohemi.justparty.controller;

import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * Created by Tom on 07.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/login")
public class LoginController extends JPController {
    @RequestMapping(method = RequestMethod.GET)
    public String printCreateEvent(Principal principal, @RequestParam(value = "error", required = false) String error,@RequestParam(value = "alert_success", required = false) String alert_success, ModelMap model) {
        if (principal != null){
            model.addAttribute("info_title", "info.login.loggedin.title");
            model.addAttribute("info_header", "info.login.loggedin.header");
            model.addAttribute("info_box", "info.login.loggedin.box");
            return LogicalViewNames.getNameInfoPage();
        }
        setAlerts(model, alert_success);
        if (error != null) {
            model.addAttribute("login_error","Ung&uuml;ltige Logindaten!");
        }
        return LogicalViewNames.getNameLogin();
    }
}