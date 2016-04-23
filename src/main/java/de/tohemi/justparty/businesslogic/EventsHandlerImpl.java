package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.database.datainterfaces.DBUser;
import de.tohemi.justparty.datamodel.Accepted;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserEventRelation;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.util.IDGenerator;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Micha Piertzik on 17.11.2015.
 */
public class EventsHandlerImpl implements EventsHandler {

    public boolean createEvent(String eventname, String mail) {

        DBController dbController = DBController.getInstance();
        DBUser user = new DBUser(mail);
        EmailSender sender = new EmailSender();
        sender.sendCreateConfirmation(user, eventname);
        return dbController.addEvent(new Event(eventname, user.getEmailuser()));
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

        DBController dbController = DBController.getInstance();
        return dbController.userIsHostOfRequestedEvent(new User(mailFromLoggedInUser), new Event(id));
    }

    public boolean answerInvitation(int eventId, String mail, Accepted answer) {

        if (answer == null) {
            return false;
        }
        DBController dbController = DBController.getInstance();
        return dbController.updateGuest(new Event(eventId), new User(mail), answer);
    }

    public List<UserEventRelation> getGuestlist(int id, String mail) {
        Event event = new Event(id);
        event.setEventOwner(new User(mail));
        return DBController.getInstance().getInvitedUsers(event);
    }

    public Event getEvent(final int id, String mail) {

        //Example event to mock unimpleented DB connection
        Event event = null;
        try {
            event = DBController.getInstance().getEventById(id);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (ZipCodeInvalidException e) {
            e.printStackTrace();
        }

        event.setEventOwner(new User(mail));
        final List<UserEventRelation> guestlist = getGuestlist(id, mail);
        Collections.sort(guestlist);
        event.setGuests(guestlist);
        return event;
    }
}