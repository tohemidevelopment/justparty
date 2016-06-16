package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.businesslogic.factories.EventsHandlerFactory;
import de.tohemi.justparty.datamodel.Accepted;
import de.tohemi.justparty.datamodel.UserEventRelation;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.viewinterface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by Heiko on 04.11.2015.
 */
@org.springframework.stereotype.Controller
public class EventController extends JPController {

    @RequestMapping(method = RequestMethod.GET, value = CREATE_EVENT)
    public String printCreateEvent() {
        return LogicalViewNames.getNameCreateEvent();
    }

    @RequestMapping(method = RequestMethod.POST, value = CREATE_EVENT)
    public String createEvent(@RequestParam(value = "eventname")String eventname) {

        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        String mail = getMailFromLoggedInUser();
        if(eventsHandler.createEvent(HtmlUtils.htmlEscape(eventname), mail))
        {
            return REDIRECT + EDITEVENT+"?id="+eventsHandler.getNewestEventIdOfUser(mail);
        }
        return REDIRECT + ERROR;
    }


    @RequestMapping(method = RequestMethod.POST, value = DELETE)
    public  String deleteEvent(ModelMap model, @RequestParam(value = "id") int id)
    {
        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        if (eventsHandler.deleteEvent(id, getMailFromLoggedInUser())){
            model.addAttribute("alert_success", "alert.success.delete_event");
        }else{
            model.addAttribute("alert_warning", "alert.warning.delete_event");
        }
        return REDIRECT + MANAGE_EVENT;
    }

    @RequestMapping(method = RequestMethod.GET, value = GUESTS)
    public String showGuestlist(ModelMap model, @RequestParam (value = "id") int id){
        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        String mailFromLoggedInUser = getMailFromLoggedInUser();
        if (!eventsHandler.userIsHostOfRequestedEvent(id, mailFromLoggedInUser)){
            //TODO: Show Error String, User not host
            return REDIRECT + ERROR;
        }

        List<UserEventRelation> guestlist = eventsHandler.getGuestlist(id);
        Collections.sort(guestlist);
        model.addAttribute("guests", guestlist);

        return EventAssistant.getInstance().showData(model, id);
    }

    @RequestMapping(method = RequestMethod.GET, value = VIEW_EVENT)
    public String viewEvent(ModelMap model, @RequestParam (value = "id") int id){
        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        String mailFromLoggedInUser = getMailFromLoggedInUser();

        if (!eventsHandler.userIsHostOfRequestedEvent(id, mailFromLoggedInUser) && !eventsHandler.userIsInvitedToEvent(id, mailFromLoggedInUser)){
            //TODO: Show Error String, User not host
            return REDIRECT + ERROR;
        }

        List<UserEventRelation> guestlist = eventsHandler.getGuestlist(id);

        Accepted acceptedStatus=null;
        int numberAccepted = 0;
        int numberNotSure = 0;
        for (UserEventRelation uer: guestlist) {
            if(uer.getUser().getEmail().equals(mailFromLoggedInUser)){
                acceptedStatus=uer.getAccepted();
            }
            if(uer.getAccepted()== Accepted.ACCEPTED){
                numberAccepted++;
            }
            if(uer.getAccepted()== Accepted.NOTSURE){
                numberNotSure++;
            }

        }
        Event event = eventsHandler.getEvent(id);
        model.addAttribute("acceptedStatus", acceptedStatus);
        model.addAttribute("currentUser", mailFromLoggedInUser);
        model.addAttribute("guests", guestlist);
        model.addAttribute("numberAccepted", numberAccepted);
        model.addAttribute("numberNotSure", numberNotSure);
        model.addAttribute("numberInvited", guestlist.size());
        model.addAttribute("event", event);
        model.addAttribute("eventType", event.getEventType().name());
        return EventAssistant.getInstance().showData(model, id);
    }
}