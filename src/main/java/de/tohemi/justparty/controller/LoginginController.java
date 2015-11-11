package main.java.de.tohemi.justparty.controller;

import main.java.de.tohemi.justparty.view_interface.GeneralStrings;
import main.java.de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Micha Piertzik on 11.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/")
public class LoginginController extends Controller {
    @RequestMapping(method = RequestMethod.POST)
    public String printCreateEvent(@RequestParam Map<String,String> allRequestParams, ModelMap model) {
        setglobalStrings(model);
        String username = allRequestParams.get(GeneralStrings.getUSERNAME_STRING());
        String password = allRequestParams.get(GeneralStrings.getPASSWORD_STRING());
        //call method from model to login with username and password
        //method should return true or false
        boolean loginSucess = false;
        if (loginSucess){
            return LogicalViewNames.getNameEventManager();
        }
        return LogicalViewNames.getNameLogin();
    }
}
