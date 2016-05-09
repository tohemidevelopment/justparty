package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.datamodel.*;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Micha Piertzik on 17.11.2015.
 */
public class EventsHandlerImpl implements EventsHandler {

    public boolean createEvent(String eventname, String mail) {

        DBEventController dbController = DBEventController.getInstance();
        User user = UserFactory.create(mail, true);
        EmailSender sender = new EmailSender();
        sender.sendCreateConfirmation(user, eventname);
        return dbController.addEvent(new ConcreteEvent(eventname, UserFactory.create(user.getEmail())));
    }

    public boolean deleteEvent(int id, String mail) {

        DBEventController dbController = DBEventController.getInstance();
        Event event = new ConcreteEvent(null, null);
        event.setId(id);
        return dbController.deleteEvent(event, UserFactory.create(mail));
    }

    public static List<UserEventRelation> getCurrentEvents(String mail) {

        DBEventController dbController = DBEventController.getInstance();
        User user = UserFactory.create(mail);
        List<UserEventRelation> userEventsRelations = dbController.getHostedUERs(user);
        userEventsRelations.addAll(dbController.getInvitedUERs(user));
        return userEventsRelations;
    }

    public boolean userIsHostOfRequestedEvent(int id, String mailFromLoggedInUser) {

        DBEventController dbController = DBEventController.getInstance();
        return dbController.userIsHostOfRequestedEvent(UserFactory.create(mailFromLoggedInUser), new ConcreteEvent(id));
    }

    public boolean answerInvitation(int eventId, String mail, Accepted answer) {

        if (answer == null) {
            return false;
        }
        DBEventController dbController = DBEventController.getInstance();
        return dbController.updateGuest(new ConcreteEvent(eventId), UserFactory.create(mail), answer);
    }

    public List<UserEventRelation> getGuestlist(int id, String mail) {
        Event event = new ConcreteEvent(id);
        event.setEventOwner(UserFactory.create(mail));
        return DBEventController.getInstance().getInvitedUsers(event);
    }

    public Event getEvent(final int id, String mail) {

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

        event.setEventOwner(UserFactory.create(mail));
        final List<UserEventRelation> guestlist = getGuestlist(id, mail);
        Collections.sort(guestlist);
        event.setGuests(guestlist);
        return event;
    }

    public boolean updateEvent(Event eventChanges) {

        Event dbEvent = new DBEvent(eventChanges.getId());
        if (eventChanges.getName() != null) {
            dbEvent.setName(eventChanges.getName());
        }
        return false;
    }
}