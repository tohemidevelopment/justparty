package main.java.de.tohemi.justparty.controller;

import main.java.de.tohemi.justparty.database.controller.DBController;
import main.java.de.tohemi.justparty.datamodel.Event;
import main.java.de.tohemi.justparty.datamodel.User;
import main.java.de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Heiko on 04.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/createEvent")
public class EventController {
    @RequestMapping(method = RequestMethod.GET)
    public String printCreateEvent(ModelMap model) {
        return LogicalViewNames.getNameCreateEvent();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createEvent(@RequestParam(value = "eventname")String name,@RequestParam(value = "user") User eventOwner, ModelMap model) {

        //DB sollte hier im Controller nichts zu suchen haben! Hier nur Geschäftslogik aufrufen, die kann dann auf die DB zugreifen
        DBController dbController = new DBController();
        dbController.insertEvent(new Event(name, eventOwner), "");
        return LogicalViewNames.getNameEventManager();
    }
}
