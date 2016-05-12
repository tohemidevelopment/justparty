package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.Error;
import de.tohemi.justparty.businesslogic.user.UserHandler;
import de.tohemi.justparty.util.logger.Logger;
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
public class RegisterController extends JPController {

    @RequestMapping(method = RequestMethod.GET, value = REGISTER)
    public String printRegisterPage(ModelMap model, Principal principal) {
        if (addInfoIfAlreadyLoggedIn(model, principal)) {
            return LogicalViewNames.getNameInfoPage();
        }
        return LogicalViewNames.getNameRegister();
    }


    @RequestMapping(method = RequestMethod.POST, value = REGISTER)
    public String performRegister(ModelMap model, @RequestParam(value = "email") String email,
                                  @RequestParam(value = "password") String password, @RequestParam(value = "password_repeat") String matchingPassword,
                                  @RequestParam(value = "terms", required = false) String termsAsString) {

        boolean acceptedTerms = termsAsString != null ? true : false;
        Error error = new UserHandler().createUser(email, password, matchingPassword, acceptedTerms);
        if (error == null) {
            setAlerts(model, null, null, "alert.success.registration", null);
            return REDIRECT + LogicalViewNames.getNameLogin();
        }
        if (error.getType() == null) {
            return REDIRECT + ERROR;
        }
        model.addAttribute(error.getType().toString(), error.getMsg());

        //Diese Attribute werden benötigt, wenn Bestätigungsmail geschickt wird
        //    model.addAttribute("info_header", "info.header.register");
        //    model.addAttribute("info_box", "info.box.register");
        //    return LogicalViewNames.getNameInfoPage();
        return LogicalViewNames.getNameRegister();
    }

    @RequestMapping(method = RequestMethod.GET, value = VERIFY_EMAIL)
    public String verifyEmail(ModelMap model, @RequestParam(value = "id") String verificationID) {
        Error error = new UserHandler().verifyEmail(verificationID);
        if (error == null) {
            setAlerts(model, null, null, "alert.success.verification", null);
            return REDIRECT + LogicalViewNames.getNameVerifyEmail();
        }
        model.addAttribute(error.getType().toString(), error.getMsg());
        return LogicalViewNames.getNameErrorPage();
    }
}