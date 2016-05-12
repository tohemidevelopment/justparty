package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.businesslogic.factories.EventsHandlerFactory;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.viewInterface.LogicalViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Micha Piertzik on 09.12.2015.
 */
@Controller
public class EventAssistantController extends JPController {

    @RequestMapping(method = RequestMethod.GET, value = EDITEVENT)
    public String editEventData(final ModelMap model, @RequestParam(value = "id") final int id) {

        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        if (userIsNotHost(id, eventsHandler)) {
            //TODO: Show Error String, User not host
            return REDIRECT + ERROR;
        }
        model.addAttribute("alert_info", "alert.notimplyet");

        String mailFromLoggedInUser = getMailFromLoggedInUser();
        Event event = eventsHandler.getEvent(id, mailFromLoggedInUser);
        model.addAttribute("event", event);
        return LogicalViewNames.getNameEditEvent();
    }

    @RequestMapping(method = RequestMethod.GET, value = EVENTDATA)
    public String showEventData(final ModelMap model, @RequestParam(value = "id") final int id) {

        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        if (userIsNotHost(id, eventsHandler)) {
            //TODO: Show Error String, User not host
            return REDIRECT + ERROR;
        }
        model.addAttribute("alert_info", "alert.notimplyet");

        String mailFromLoggedInUser = getMailFromLoggedInUser();
        Event event = eventsHandler.getEvent(id, mailFromLoggedInUser);
        model.addAttribute("event", event);
        return LogicalViewNames.getNameEventData();
    }

    @RequestMapping(method = RequestMethod.POST, value = EVENTDATA)
    public String getEventData(@RequestBody final String jsonString, @RequestHeader(value = "id") final int id) {

        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        if (userIsNotHost(id, eventsHandler)) {
            //TODO: Show Error String, User not host
            return REDIRECT + ERROR;
        }
        final Event eventChanges = EventFactory.createEventFromJson(id, jsonString);
        eventsHandler.updateEvent(eventChanges);
        return null;
    }

}
