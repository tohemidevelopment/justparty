package de.tohemi.justparty.datamodel.event;


import com.google.gson.Gson;

/**
 * Created by Micha Piertzik on 04.05.2016.
 */
final public class EventFactory {

    static public Event createEvent(final int id, final EventType eventType, final boolean dbAccess) {
        if (eventType == EventType.BIRTHDAY) {
            return new BirthdayEvent(id);
        }

        //Eventtype == Default
        if (dbAccess) {
            return new DBAccessEvent(id);
        }
        return new ConcreteEvent(id);
    }

    public static Event createEvent(final int id) {
        return createEvent(id, EventType.DEFAULT, false);
    }

    public static Event createEvent(final int id, final EventType eventType) {

        return createEvent(id, eventType, false);
    }

    public static Event createEvent(final int id, final boolean dbAccess) {

        return createEvent(id, EventType.DEFAULT, dbAccess);
    }

    public static Event createEvent() {

        return new ConcreteEvent();
    }

    public static Event createEventFromJson(final int id, final String jsonString) {
        Gson gson = new Gson();
        Event event = gson.fromJson(jsonString, ConcreteEvent.class);
        event.setId(id);
        return event;
    }
}
