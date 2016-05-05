package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.database.datainterfaces.DBUser;
import de.tohemi.justparty.datamodel.*;
import de.tohemi.justparty.datamodel.event.ConcreteEvent;
import de.tohemi.justparty.datamodel.event.DBAccessEvent;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Micha Piertzik on 17.11.2015.
 */
public class EventsHandlerImpl implements EventsHandler {

    public boolean createEvent(String eventname, String mail) {

        DBEventController dbController = DBEventController.getInstance();
        DBUser user = new DBUser(mail);
        EmailSender sender = new EmailSender();
        sender.sendCreateConfirmation(user, eventname);
        return dbController.addEvent(new ConcreteEvent(eventname, user.getEmailuser()));
    }

    public boolean deleteEvent(int id, String mail) {

        DBEventController dbController = DBEventController.getInstance();
        Event event = EventFactory.createEvent(id);
        return dbController.deleteEvent(event, new User(mail));
    }

    public static List<UserEventRelation> getCurrentEvents(String mail) {

        DBEventController dbController = DBEventController.getInstance();
        User user = new User(mail);
        List<UserEventRelation> userEventsRelations = dbController.getHostedUERs(user);
        userEventsRelations.addAll(dbController.getInvitedUERs(user));
        return userEventsRelations;
    }

    public boolean userIsHostOfRequestedEvent(int id, String mailFromLoggedInUser) {

        DBEventController dbController = DBEventController.getInstance();
        return dbController.userIsHostOfRequestedEvent(new User(mailFromLoggedInUser), new ConcreteEvent(id));
    }

    public boolean answerInvitation(int eventId, String mail, Accepted answer) {

        if (answer == null) {
            return false;
        }
        DBEventController dbController = DBEventController.getInstance();
        return dbController.updateGuest(new ConcreteEvent(eventId), new User(mail), answer);
    }

    public List<UserEventRelation> getGuestlist(int id, String mail) {
        Event event = new ConcreteEvent(id);
        event.setEventOwner(new User(mail));
        return DBEventController.getInstance().getInvitedUsers(event);
    }

    public Event getEvent(final int id, String mail) {

        //Example event to mock unimpleented DB connection
        Event event = null;
        try {
            event = DBEventController.getInstance().getEventById(id);
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

    public boolean updateEvent(Event eventChanges) {

        Event dbEvent = new DBAccessEvent(eventChanges.getId());
        if (eventChanges.getName() != null) {
            dbEvent.setName(eventChanges.getName());
        }


        return false;
    }
}