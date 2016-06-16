package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.businesslogic.factories.EventsHandlerFactory;
import de.tohemi.justparty.database.controller.DBDeclarationController;
import de.tohemi.justparty.datamodel.Declaration;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.user.UserFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Heiko on 16.06.2016.
 */
@org.springframework.stereotype.Controller
public class DeclarationController extends JPController {



    @RequestMapping(method = RequestMethod.GET, value = BRING_PREP)
    public String bringPreparation(@RequestParam(value = "id") final int id) {
        Declaration declaration = DBDeclarationController.getInstance().getDeclarationWithId(id);
        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        if (declaration.getBringWithByAll() ||!declaration.getUser().getEmail().equals("") ||!eventsHandler.userIsInvitedToEvent(declaration.getEventId(), getMailFromLoggedInUser())&& !eventsHandler.userIsHostOfRequestedEvent(declaration.getEventId(),getMailFromLoggedInUser())) {
            return REDIRECT + ERROR;
        }
        declaration.setUser(UserFactory.create(getMailFromLoggedInUser()));
        if(DBDeclarationController.getInstance().updateDeclaration(declaration)){
            return REDIRECT +VIEW_EVENT+"?id="+declaration.getEventId();
        }
        return REDIRECT + ERROR;
    }

    @RequestMapping(method = RequestMethod.GET, value = FREE_PREP)
    public String freePreparation(@RequestParam(value = "id") final int id) {
        Declaration declaration = DBDeclarationController.getInstance().getDeclarationWithId(id);
        EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        if (declaration.getBringWithByAll() ||!declaration.getUser().getEmail().equals(getMailFromLoggedInUser()) || !eventsHandler.userIsInvitedToEvent(declaration.getEventId(), getMailFromLoggedInUser())&& !eventsHandler.userIsHostOfRequestedEvent(declaration.getEventId(),getMailFromLoggedInUser())) {
            return REDIRECT + ERROR;
        }
        declaration.setUser(UserFactory.create(""));
        if(DBDeclarationController.getInstance().updateDeclaration(declaration)){
            return REDIRECT +VIEW_EVENT+"?id="+declaration.getEventId();
        }
        return REDIRECT + ERROR;
    }

}
