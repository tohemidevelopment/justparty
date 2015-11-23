package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Micha Piertzik on 17.11.2015.
 */
public class EventCreator {

    public boolean createEvent(String eventname) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username (=email)
        DBController dbController = DBController.getInstance();
        User user = new User(username);
        return dbController.add(new Event(eventname, user));
    }
}
