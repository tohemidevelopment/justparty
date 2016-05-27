package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.businesslogic.factories.EventsHandlerFactory;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventType;
import de.tohemi.justparty.viewinterface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Micha Piertzik on 27.05.2016.
 */
public class EventAssistant extends JPController {

    private static EventAssistant instance;

    private EventAssistant() {}

    public String getViewName(ModelMap model, @RequestParam(value = "id") int id) {
        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        if (userIsNotHost(id, eventsHandler)) {
            //TODO: Show Error String, User not host
            return REDIRECT + ERROR;
        }

        String mailFromLoggedInUser = getMailFromLoggedInUser();
        Event event = eventsHandler.getEvent(id, mailFromLoggedInUser);
        model.addAttribute("event", event);
        if (event.getEventType() == null) {
            //display chooseEventType
            model.addAttribute("types", EventType.getEventTypes());
            return LogicalViewNames.getNameChooseEventType();
        }
        return LogicalViewNames.getNameEditEvent();
    }

    public synchronized static EventAssistant getInstance() {
        if (instance == null) {
            return new EventAssistant();
        }
        return instance;
    }
}
