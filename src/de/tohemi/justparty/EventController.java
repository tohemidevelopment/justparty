package de.tohemi.justparty;

/**
 * Created by Heiko on 04.11.2015.
 */
public class EventController {
    DBController dbController= new DBController();
    public void createEvent(String name, User eventOwner){
        dbController.insertEvent(new Event(name, eventOwner));
    }
}
