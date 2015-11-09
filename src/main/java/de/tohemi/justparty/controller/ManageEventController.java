package main.java.de.tohemi.justparty.controller;

import main.java.de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tom on 07.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/manageEvent")
public class ManageEventController {
        @RequestMapping(method = RequestMethod.GET)
        public String printCreateEvent(ModelMap model)
        {
            model.addAttribute("message","Neues Event Erstellt!");
            return LogicalViewNames.getNameEventManager();
        }

}


