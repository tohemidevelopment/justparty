package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String mail = auth.getName(); //get logged in username (=email)
        model.addAttribute("currentevents", EventsHandlerImpl.getCurrentEvents(mail));
        return LogicalViewNames.getNameEventManager();
    }
}