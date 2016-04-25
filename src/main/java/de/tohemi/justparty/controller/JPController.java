package de.tohemi.justparty.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

import java.security.Principal;

/**
 * Created by Micha Piertzik on 01.12.2015.
 */
public abstract class JPController {
    public static final String REDIRECT = "redirect:";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String EDITEVENT = "/edit";
    public static final String ERROR = "/error";
    public static final String MANAGE_EVENT = "/manageEvent";
    public static final String ACCEPTED = "/accepted";
    public static final String CREATE_EVENT = "/createEvent";
    public static final String DELETE = "/delete";
    public static final String GUESTS = "guests";
    public static final String VERIFY_EMAIL = "/verifyEmail";
    public static final String INVITE_USER = "/inviteUser";
    public static final String EVENTDATA = "/eventdata";

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

    protected boolean addInfoIfAlreadyLoggedIn(ModelMap model, Principal principal) {
        if (principal != null){
            model.addAttribute("info_title", "info.login.loggedin.title");
            model.addAttribute("info_header", "info.login.loggedin.header");
            model.addAttribute("info_box", "info.login.loggedin.box");
            return true;
        }
        return false;
    }
}
