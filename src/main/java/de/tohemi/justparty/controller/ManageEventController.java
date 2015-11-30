package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EventsHandler;
import de.tohemi.justparty.view_interface.LogicalViewNames;
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
    public String printCreateEvent(ModelMap model) {

        model.addAttribute("alert_info", "alert.notimplyet");
        model.addAttribute("currentevents", EventsHandler.getCurrentEvents());
        return LogicalViewNames.getNameEventManager();
    }
}