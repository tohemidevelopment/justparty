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
import org.springframework.web.util.HtmlUtils;

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
        if(eventsHandler.createEvent(HtmlUtils.htmlEscape(eventname), mail))
        {
            return REDIRECT + "/manageEvent";
        }
        return LogicalViewNames.getNameErrorPage();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public  String deleteEvent(ModelMap model, @RequestParam(value = "id") int id)
    {
        System.out.println("Aufruf delete event");
        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler("");
        if (eventsHandler.deleteEvent(id, getMailFromLoggedInUser())){
            model.addAttribute("alert_success", "alert.success.delete_event");
        }else{
            model.addAttribute("alert_warning", "alert.warning.delete_event");
        }
        return REDIRECT + "manageEvent";
    }
}
