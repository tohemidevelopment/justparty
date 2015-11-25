package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.user.UserHandler;
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
public class RegisterController {
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String printRegisterPage(ModelMap model, Principal principal) {
        if (principal != null){
            model.addAttribute("info_title", "info.login.loggedin.title");
            model.addAttribute("info_header", "info.login.loggedin.header");
            model.addAttribute("info_box", "info.login.loggedin.box");
            return LogicalViewNames.getNameInfoPage();
        }
        return LogicalViewNames.getNameRegister();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String performRegister(ModelMap model, @RequestParam(value = "email") String email,
                                  @RequestParam(value = "password") String password, @RequestParam(value = "password_repeat") String matchingPassword,
                                  @RequestParam(value = "terms", required = false) String termsAsString) {

        boolean acceptedTerms = termsAsString != null ? true : false;
        de.tohemi.justparty.businesslogic.Error error = new UserHandler().createUser(email, password, matchingPassword, acceptedTerms);
        if (error == null) {
            model.addAttribute("alert_success", "alert.success.registration");
            return "redirect:" + LogicalViewNames.getNameLogin();
        }
        if (error.getType() == null) {
            return LogicalViewNames.getNameErrorPage();
        }
        model.addAttribute(error.getType().toString(), error.getMsg());

        //Diese Attribute werden benötigt, wenn Bestätigungsmail geschickt wird
        //    model.addAttribute("info_header", "info.header.register");
        //    model.addAttribute("info_box", "info.box.register");
        //    return LogicalViewNames.getNameInfoPage();
        return LogicalViewNames.getNameRegister();
    }
}