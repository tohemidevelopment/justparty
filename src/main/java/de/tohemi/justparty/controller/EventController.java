package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EventsHandler;
import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Heiko on 04.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/createEvent")
public class EventController {
    @RequestMapping(method = RequestMethod.GET)
    public String printCreateEvent(ModelMap model) {
        return LogicalViewNames.getNameCreateEvent();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createEvent(@RequestParam(value = "eventname")String eventname, ModelMap model) {

        EventsHandler eventsHandler = new EventsHandler();
        if(eventsHandler.createEvent(eventname))
        {
            return LogicalViewNames.getNameEventManager();
        }
        return LogicalViewNames.getNameErrorPage();
    }
}
