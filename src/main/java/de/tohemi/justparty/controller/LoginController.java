package main.java.de.tohemi.justparty.controller;

import main.java.de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tom on 07.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/login")
public class LoginController extends Controller {@RequestMapping(method = RequestMethod.GET)
public String printCreateEvent(ModelMap model)
{
    setglobalStrings(model);
    return LogicalViewNames.getNameLogin();
}


}
