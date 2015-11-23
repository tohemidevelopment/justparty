package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.*;
import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Tom on 07.11.2015.
 */
@org.springframework.stereotype.Controller
public class RegisterController {
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String printRegisterPage(ModelMap model) {
        return LogicalViewNames.getNameRegister();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String performRegister(ModelMap model, @RequestParam(value = "email") String email,
                                  @RequestParam(value = "password") String password, @RequestParam(value = "password_repeat") String matchingPassword) {

        de.tohemi.justparty.businesslogic.Error error = new UserHandler().createUser( email, password, matchingPassword);
        if (error == null){
            model.addAttribute("alert_success", "alert.success.registration");
            return "redirect:" + LogicalViewNames.getNameLogin();
        }
        if (error.getType() == null)
        {
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