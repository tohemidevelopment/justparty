package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.datamodel.Accepted;
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

    public boolean deleteEvent(int id, String mail) {

        DBController dbController = DBController.getInstance();
        Event event = new Event(null, null);
        event.setId(id);
        return dbController.deleteEvent(event, new User(mail));
    }

    public static List<UserEventRelation> getCurrentEvents(String mail) {

        DBController dbController = DBController.getInstance();
        User user = new User(mail);
        List<UserEventRelation> userEventsRelations = dbController.getHostedUERs(user);
        userEventsRelations.addAll(dbController.getInvitedUERs(user));
        return userEventsRelations;
    }

    public boolean userIsHostOfRequestedEvent(int id, String mailFromLoggedInUser) {
        return true;
    }

    public boolean answerInvitation(int eventId, String mail, Accepted answer){

        if (answer == null){
            return false;
        }
        DBController dbController = DBController.getInstance();
        return dbController.updateGuest(new Event(eventId),new User(mail), answer);
    }

    public List<UserEventRelation> getGuestlist(int id) {
        Event event = new Event(id);
        return DBController.getInstance().getInvitedUsers(event);
    }
}