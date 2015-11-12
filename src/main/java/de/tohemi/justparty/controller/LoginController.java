package main.java.de.tohemi.justparty.controller;

import main.java.de.tohemi.justparty.view_interface.GeneralStrings;
import main.java.de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Tom on 07.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/login")
public class LoginController extends Controller {
    @RequestMapping(method = RequestMethod.GET)
    public String printCreateEvent(@RequestParam Map<String,String> allRequestParams, ModelMap model) {
        setglobalStrings(model);
        return LogicalViewNames.getNameLogin();
    }
}