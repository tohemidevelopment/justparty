package main.java.de.tohemi.justparty.controller;

import main.java.de.tohemi.justparty.database.controller.DBController;
import main.java.de.tohemi.justparty.datamodel.Event;
import main.java.de.tohemi.justparty.datamodel.User;
import main.java.de.tohemi.justparty.view_interface.LogicalViewNames;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Heiko on 04.11.2015.
 */
@org.springframework.stereotype.Controller
@RequestMapping(value = "/createEvent")
public class EventController {
    @RequestMapping(method = RequestMethod.GET)
    public String printCreateEvent(ModelMap model)
    {
        model.addAttribute("message","Erstellen Sie ein neues Event");
        return LogicalViewNames.getNameCreateEvent();
    }

    DBController dbController= new DBController();
    public void createEvent(String name, User eventOwner){
        dbController.insertEvent(new Event(name, eventOwner));
    }
}
