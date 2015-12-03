package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserEventRelation;

import java.util.List;

/**
 * Created by Micha Piertzik on 17.11.2015.
 */
public class EventsHandlerImpl implements EventsHandler {

    public boolean createEvent(String eventname, String mail) {

        DBController dbController = DBController.getInstance();
        User user = new User(mail);
        return dbController.addEvent(new Event(eventname, user));
    }

    public boolean deleteEvent(Event e) {

        DBController dbController = DBController.getInstance();
        return dbController.deleteEvent(e);
    }

    public static List<UserEventRelation> getCurrentEvents(String mail) {

        DBController dbController = DBController.getInstance();
        User user = new User(mail);
        List<UserEventRelation> userEventsRelations = dbController.getHostedUERs(user);
        userEventsRelations.addAll(dbController.getInvitedUERs(user));
        return userEventsRelations;
    }

    public boolean userIsHostOfRequestedEvent() {
        return true;
    }
}