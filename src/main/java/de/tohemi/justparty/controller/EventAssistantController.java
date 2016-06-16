package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.businesslogic.factories.EventsHandlerFactory;
import de.tohemi.justparty.datamodel.Declaration;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.event.EventType;
import de.tohemi.justparty.viewinterface.LogicalViewNames;
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

        return EventAssistant.getInstance().getViewName(model, id);
    }

    @RequestMapping(method = RequestMethod.POST, value = EVENTTYPE)
    public String setEventType(final ModelMap model, @RequestParam(value = "id") final int id, @RequestParam(value = "type") final String eventtype) {

        final EventType type = EventType.valueOfNullableString(eventtype);
        Event event = EventFactory.createEvent(id, type, true);
        event.setEventType(type);
        return EventAssistant.getInstance().getViewName(model, id);
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
        Event event = eventsHandler.getEvent(id);
        model.addAttribute("event", event);
        return LogicalViewNames.getNameEventData();
    }

    @RequestMapping(method = RequestMethod.POST, value = EVENTDATA)
    public String changeEventData(@RequestBody final String jsonString, @RequestHeader(value = "id") final int id) {

        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        if (userIsNotHost(id, eventsHandler)) {
            //TODO: Show Error String, User not host
            return REDIRECT + ERROR;
        }
        final Event eventChanges = EventFactory.createEventFromJson(id, jsonString);
        eventsHandler.updateEvent(eventChanges);
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = ADD_PREP)
    public String addPreparation(@RequestHeader(value = "id") final int id,
                                 @RequestHeader(value = "add") final String item,
                                 @RequestHeader(value = "byall") final boolean byall) {

        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        if (userIsNotHost(id, eventsHandler)) {
            //TODO: Show Error String, User not host
            return REDIRECT + ERROR;
        }
        final Event event = EventFactory.createEvent(id, true);
        event.addDeclaration(new Declaration(item, null, byall, id));
        return null;
    }

}
