package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.businesslogic.factories.EventsHandlerFactory;
import de.tohemi.justparty.datamodel.Accepted;
import de.tohemi.justparty.viewinterfacef.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Tom on 07.11.2015.
 */
@org.springframework.stereotype.Controller
public class ManageEventController extends JPController {

    @RequestMapping(method = RequestMethod.GET, value = MANAGE_EVENT)
    public String printEvents(ModelMap model, @RequestParam(value = "alertSucces", required = false) String alertSucces) {

        setAlerts(model, null, null, alertSucces, null);
        String mail = getMailFromLoggedInUser();
        model.addAttribute("currentevents", EventsHandlerImpl.getCurrentEvents(mail));
        return LogicalViewNames.getNameEventManager();
    }

    @RequestMapping(method = RequestMethod.GET, value = ACCEPTED)
    public String changeAccepted(ModelMap model, @RequestParam(value = "id") int id, @RequestParam(value = "accepted") String accepted,
                                 @RequestParam(value = "generated", required = false) String generated) {

        String mail = getMailFromUserOrGeneratedURLParam(generated);
        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        Boolean failure = Boolean.valueOf(!eventsHandler.answerInvitation(id, mail,
                Accepted.valueOf(accepted)));
        model.addAttribute("show_alert", failure);
        return LogicalViewNames.getNameAlertAnswerInvitaion();
    }

    private String getMailFromUserOrGeneratedURLParam(String generated) {

        //TODO: if generated is not null and no user is logged in --> check generated Param and return UnregisteredUser | then: need to return a diffrent LogicalView name!

        String mail = getMailFromLoggedInUser();
        if ("anonymousUser".equals(mail)) {
            //Nobody is logged in
            return null;
        }
        return mail;
    }
}