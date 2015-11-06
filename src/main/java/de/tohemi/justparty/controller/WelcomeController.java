package main.java.de.tohemi.justparty.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Micha on 06.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/")
public class WelcomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcomePage(ModelMap model)
    {
        model.addAttribute("message","Herzlich Willkommen!");
        model.addAttribute("discription","Hier entsteht 'justParty' für Sie!");
        return "welcomepage";
    }
}
