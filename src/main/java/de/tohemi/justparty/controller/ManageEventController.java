package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Tom on 07.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/manageEvent")
public class ManageEventController extends JPController{
    @RequestMapping(method = RequestMethod.GET)
    public String printEvents(ModelMap model, @RequestParam(value = "alert_success", required = false) String alert_success) {

        setAlerts(model, null, null, alert_success, null);
        String mail = getMailFromLoggedInUser();
        model.addAttribute("currentevents", EventsHandlerImpl.getCurrentEvents(mail));
        return LogicalViewNames.getNameEventManager();
    }
}