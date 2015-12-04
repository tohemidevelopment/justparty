package de.tohemi.justparty.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

/**
 * Created by Micha Piertzik on 01.12.2015.
 */
public abstract class JPController {
    public static final String REDIRECT = "redirect:";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";

    protected String getMailFromLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    protected void setAlerts(ModelMap model,String danger, String warning, String success, String info) {

        if (danger != null){
            model.addAttribute("alert_danger", danger);
        }
        if (warning != null){
            model.addAttribute("alert_warning", warning);
        }
        if (success != null){
            model.addAttribute("alert_success", success);
        }
        if (info != null){
            model.addAttribute("alert_info", info);
        }
    }
}
