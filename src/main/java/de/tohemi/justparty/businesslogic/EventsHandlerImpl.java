package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.controller.DBDeclarationController;
import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.database.controller.DBGuestlistController;
import de.tohemi.justparty.datamodel.Accepted;
import de.tohemi.justparty.datamodel.Declaration;
import de.tohemi.justparty.datamodel.UserEventRelation;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.datamodel.event.EventFactory;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;

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
        Event event = EventFactory.createEvent();
        event.setName(eventname);
        event.setEventOwner(UserFactory.create(user.getEmail()));
        return dbController.addEvent(event);
    }

    public boolean deleteEvent(int id, String mail) {

        DBEventController dbController = DBEventController.getInstance();
        Event event = EventFactory.createEvent(id);
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
        return dbController.userIsHostOfRequestedEvent(UserFactory.create(mailFromLoggedInUser), EventFactory.createEvent(id));
    }

    public boolean userIsInvitedToEvent(int id, String mailFromLoggedInUser){
        DBEventController dbController = DBEventController.getInstance();
        List<UserEventRelation> uerList = dbController.getInvitedUERs(UserFactory.create(mailFromLoggedInUser));
        for (UserEventRelation uer:uerList) {
            if(uer.getEvent().getId()==id)
                return true;
        }
        return false;
    }

    public boolean answerInvitation(int eventId, String mail, Accepted answer) {

        if (answer == null) {
            return false;
        }
        DBGuestlistController dbController = DBGuestlistController.getInstance();
        return dbController.updateGuestEventStatus(EventFactory.createEvent(eventId), UserFactory.create(mail), answer.getValue());
    }

    public List<UserEventRelation> getGuestlist(int id) {
        Event event = EventFactory.createEvent(id);
        return DBGuestlistController.getInstance().getInvitedUsers(event.getId());
    }

    public Event getEvent(final int id) {

        //Example event to mock unimpleented DB connection
        Event event = DBEventController.getInstance().getEventById(id);
        final List<UserEventRelation> guestlist = getGuestlist(id);
        Collections.sort(guestlist);
        event.setGuests(guestlist);
        final List<Declaration> declarations = DBDeclarationController.getInstance().getDeclarations(event);
        event.setDeclaration(declarations);
        return event;
    }

    public boolean updateEvent(Event eventChanges) {

        Event dbEvent = EventFactory.createEvent(eventChanges.getId(), true);
        if (eventChanges.getName() != null) {
            dbEvent.setName(eventChanges.getName());
        }
        if (eventChanges.getDescription() != null) {
            dbEvent.setDescription(eventChanges.getDescription());
        }
        if (eventChanges.getBegin() != null) {
            dbEvent.setBegin(eventChanges.getBegin());
        }
        if (eventChanges.getEnd() != null) {
            dbEvent.setEnd(eventChanges.getEnd());
        }
        if (eventChanges.getLocation() != null) {
            dbEvent.setLocation(eventChanges.getLocation());
        }
        if (eventChanges.getEventOwner() != null) {
            dbEvent.setEventOwner(eventChanges.getEventOwner());
        }
        if (eventChanges.getFacebookLink() != null) {
            dbEvent.setFacebookLink(eventChanges.getFacebookLink());
        }
        if (eventChanges.getGooglePlusLink() != null) {
            dbEvent.setGooglePlusLink(eventChanges.getGooglePlusLink());
        }
        if (eventChanges.getGuests() != null) {
            dbEvent.setGuests(eventChanges.getGuests());
        }
        if (eventChanges.getSpotifyPlaylistLink() != null) {
            dbEvent.setSpotifyPlaylistLink(eventChanges.getSpotifyPlaylistLink());
        }
        if (eventChanges.getWishlistLink() != null) {
            dbEvent.setWishlistLink(eventChanges.getWishlistLink());
        }
        //TODO: add missing field eventType

        return true;
    }

    public int getNewestEventIdOfUser(String mailFromHost) {
        List<Integer> event_ids =DBEventController.getInstance().getEventIDsOfUser(mailFromHost);
        return Collections.max(event_ids);
    }
}