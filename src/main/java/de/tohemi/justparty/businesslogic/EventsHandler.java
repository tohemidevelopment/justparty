package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Micha Piertzik on 17.11.2015.
 */
public class EventsHandler {

    public boolean createEvent(String eventname) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String mail = auth.getName(); //get logged in username (=email)
        DBController dbController = DBController.getInstance();
        User user = new User(mail);
        return dbController.addEvent(new Event(eventname, user));
    }

    public static List<Event> getCurrentEvents() {
        List<Event> events = new ArrayList<Event>();
        Event testevent = new Event("Testevent", new User(""));
        testevent.setBegin(Calendar.getInstance());
        events.add(testevent);
        events.add(testevent);
        Event testevent2 = new Event("Hosted event", new User(""));
        testevent2.setBegin(Calendar.getInstance());
        testevent2.setHosted(true);
        events.add(testevent2);
        return events;
    }
}
