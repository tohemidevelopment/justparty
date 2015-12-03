package de.tohemi.justparty.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

/**
 * Created by Micha Piertzik on 01.12.2015.
 */
public abstract class JPController {

    protected String getMailFromLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }


    protected void setAlerts(ModelMap model, String alert_success) {

        if (alert_success != null){
            System.out.println(alert_success);
            model.addAttribute("alert_success", alert_success);
        }
    }

}
