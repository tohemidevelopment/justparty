package de.tohemi.justparty.controller;

import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.portlet.ModelAndView;

/**
 * Created by Tom on 07.11.2015.
 */
@org.springframework.stereotype.Controller
public class RegisterController {
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String printRegisterPage(ModelMap model)
    {
        return LogicalViewNames.getNameRegister();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/perform_registry")
    public String performRegister(ModelMap model)
    {
        return LogicalViewNames.getNameErrorPage();
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        User userDto = new User();
        model.addAttribute("user", userDto);
        return LogicalViewNames.getNameRegister();
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount
            (@ModelAttribute("user") User accountDto,
             BindingResult result, WebRequest request, Errors errors) {
        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        // rest of the implementation
        return null;
    }
    private User createUserAccount(User accountDto, BindingResult result) {
        User registered = null;
        //try {
        //    registered = service.registerNewUserAccount(accountDto);
        //} catch (Exception e) {
        //    return null;
        //}
        return registered;
    }
}