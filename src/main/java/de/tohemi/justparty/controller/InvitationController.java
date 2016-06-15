package de.tohemi.justparty.controller;

import de.tohemi.justparty.businesslogic.EmailSender;
import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.businesslogic.factories.EventsHandlerFactory;
import de.tohemi.justparty.businesslogic.user.UserHandler;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;
import de.tohemi.justparty.viewinterface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Tom on 18.04.2016.
 */

@org.springframework.stereotype.Controller
public class InvitationController extends JPController {


    @RequestMapping(method = RequestMethod.GET, value = INVITE_USER)
    public String printWelcomePage() {
        return LogicalViewNames.getNameInviteUsers();
    }

    @RequestMapping(method = RequestMethod.POST, value = INVITE)
    public String invite(final @RequestParam(value = "email") String email,
                         final @RequestParam(value = "id") int id,
                         final @RequestParam(value = "message", required = false) String message) {

        final EventsHandlerImpl eventsHandler = (EventsHandlerImpl) new EventsHandlerFactory().getEventsHandler();
        if (userIsNotHost(id, eventsHandler)) {
            //TODO: Show Error String, User not host
            return REDIRECT + ERROR;
        }
        User invitedUser = new UserHandler().createPersonIfNotExisting(email);
        User host = UserFactory.create(getMailFromLoggedInUser(), true);
        new EmailSender().sendInvitation(invitedUser, host, EventFactory.createEvent(id, true));
        return null;
    }
}
