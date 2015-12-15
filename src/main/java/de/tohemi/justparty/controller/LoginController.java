package de.tohemi.justparty.controller;

import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * Created by Tom on 07.11.2015.
 */
@org.springframework.stereotype.Controller
public class LoginController extends JPController {

    @RequestMapping(method = RequestMethod.GET, value = LOGIN)
    public String printCreateEvent(Principal principal, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "alert_success", required = false) String alert_success, ModelMap model) {
        if (addInfoIfAlreadyLoggedIn(model, principal)) {
            return LogicalViewNames.getNameInfoPage();
        }
        String alert_danger = (error != null) ? "alert.danger.login_err" : null;
        setAlerts(model, alert_danger, null, alert_success, null);
        return LogicalViewNames.getNameLogin();
    }
}