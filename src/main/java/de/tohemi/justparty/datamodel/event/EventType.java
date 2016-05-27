package de.tohemi.justparty.datamodel.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Micha Piertzik on 05.05.2016.
 */
public enum EventType {
    DEFAULT, BIRTHDAY;

    public static List<String> getEventTypes() {
        final List<String> eventTypes;
        eventTypes = new ArrayList<>();
        for (EventType eventtype : EventType.values()) {
            eventTypes.add(eventtype.toString());
        }
        return eventTypes;
    }
}
