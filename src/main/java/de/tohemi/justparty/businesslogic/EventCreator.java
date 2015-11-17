package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;

/**
 * Created by Micha Piertzik on 17.11.2015.
 */
public class EventCreator {

    public boolean createEvent(String eventname, String username) {
        User user = DBController.getInstance().getUser(username);
        new Event(eventname, user);
        return false;
    }
}
