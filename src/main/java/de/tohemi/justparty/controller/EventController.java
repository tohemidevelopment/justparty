package main.java.de.tohemi.justparty.controller;

import main.java.de.tohemi.justparty.Event;
import main.java.de.tohemi.justparty.User;

/**
 * Created by Heiko on 04.11.2015.
 */
public class EventController {
    DBController dbController= new DBController();
    public void createEvent(String name, User eventOwner){
        dbController.insertEvent(new Event(name, eventOwner));
    }
}
