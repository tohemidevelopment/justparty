package de.tohemi.justparty.controller;

import com.google.gson.Gson;
import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.businesslogic.factories.EventsHandlerFactory;
import de.tohemi.justparty.datamodel.DBEvent;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Micha Piertzik on 09.12.2015.
 */
@Controller
public class EventAssistantController extends JPController {

    @RequestMapping(method = RequestMethod.GET, value = EDITEVENT)
    public String editEvent(ModelMap model, @RequestParam(value = "id") int id) {
        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler("");
        String mailFromLoggedInUser = getMailFromLoggedInUser();
        if (!eventsHandler.userIsHostOfRequestedEvent(id, mailFromLoggedInUser)) {
            //TODO: Show Error String, User not host
            return REDIRECT + ERROR;
        }
        model.addAttribute("alert_info", "alert.notimplyet");

        Event event = eventsHandler.getEvent(id, mailFromLoggedInUser);
        model.addAttribute("event", event);
        return LogicalViewNames.getNameEditEvent();
    }

    @RequestMapping(method = RequestMethod.POST, value = EVENTDATA)
    public String getEventData(@RequestBody final String jsonString) {

        System.out.println(jsonString);
        final Gson gson = new Gson();
        final Event eventChanges = gson.fromJson(jsonString, Event.class);
        System.out.println();
        Event dbEvent = new DBEvent();


        return null;
    }
}
