package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.datamodel.Accepted;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserEventRelation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Micha Piertzik on 17.11.2015.
 */
public class EventsHandlerImpl implements EventsHandler {

    public boolean createEvent(String eventname, String mail) {

        DBController dbController = DBController.getInstance();
        User user = new User(mail);
        return dbController.addEvent(new Event(eventname, user));
    }

    public static List<UserEventRelation> getCurrentEvents(String mail) {

        DBController dbController = DBController.getInstance();
        User user = new User(mail);
        //dbController.getHostedEventsLightweight(user);

        //Testevents
        List<UserEventRelation> events = new ArrayList<UserEventRelation>();
        Event event = new Event("Testevent", new User("m.goethegymka@web.de"));
        UserEventRelation testevent = new UserEventRelation(event, new User(mail), Accepted.DECLINED);
        event.setBegin(Calendar.getInstance());
        events.add(testevent);
        events.add(testevent);
        events.add(testevent);

        Event event2 = new Event("Testevent", new User("test@test.test"));
        UserEventRelation testevent2 = new UserEventRelation(event2, new User(mail), Accepted.NOTSURE);
        event2.setBegin(Calendar.getInstance());
        events.add(testevent2);



        return events;
    }

    public boolean userIsHostOfRequestedEvent() {
        //TODO: Implement
        return true;
    }
}
