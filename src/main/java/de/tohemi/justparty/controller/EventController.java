package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EventsHandler;
import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.businesslogic.factories.EventsHandlerFactory;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Heiko on 04.11.2015.
 */
@org.springframework.stereotype.Controller
public class EventController extends JPController {
    @RequestMapping(method = RequestMethod.GET, value = "/createEvent")
    public String printCreateEvent(ModelMap model) {
        return LogicalViewNames.getNameCreateEvent();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createEvent")
    public String createEvent(@RequestParam(value = "eventname")String eventname, ModelMap model) {

        EventsHandlerImpl eventsHandler = new EventsHandlerImpl();
        String mail = getMailFromLoggedInUser();
        if(eventsHandler.createEvent(eventname, mail))
        {
            return LogicalViewNames.REDIRECT + "/manageEvent";
        }
        return LogicalViewNames.getNameErrorPage();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String editEvent(ModelMap model){
        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler("");
        if (!eventsHandler.userIsHostOfRequestedEvent()){
            //TODO: Show Error String, User not host
            return LogicalViewNames.REDIRECT + "/error";
        }
        model.addAttribute("alert_info", "alert.notimplyet");
        //TODO: collect information need for edit page
        return LogicalViewNames.getNameEditEvent();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public  String deleteEvent(ModelMap model, @RequestParam(value = "id") int id)
    {
        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler("");
        if (eventsHandler.deleteEvent(id, getMailFromLoggedInUser())){
            model.addAttribute("alert_success" + "alert.success.delete_event");
        }else{
            model.addAttribute("alert_warning" + "alert.warning.delete_event");
        }
        return LogicalViewNames.REDIRECT + "/manageEvent";
    }
}
