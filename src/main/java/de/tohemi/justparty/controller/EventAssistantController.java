package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.businesslogic.factories.EventsHandlerFactory;
import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
        //TODO: collect information need for edit page
        return LogicalViewNames.getNameEditEvent();
    }

    @RequestMapping(method = RequestMethod.POST, value = EVENTDATA)
    public String getEventData(ModelMap model) {


        return null;
    }
}
