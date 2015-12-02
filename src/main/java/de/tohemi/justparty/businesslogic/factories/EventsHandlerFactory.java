package de.tohemi.justparty.businesslogic.factories;

import de.tohemi.justparty.businesslogic.EventsHandler;
import de.tohemi.justparty.businesslogic.EventsHandlerImpl;

/**
 * Created by Micha Piertzik on 01.12.2015.
 */
public class EventsHandlerFactory {

    public EventsHandler getEventsHandler(String s) {
        return new EventsHandlerImpl();
    }
}
