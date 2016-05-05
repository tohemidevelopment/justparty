package de.tohemi.justparty.datamodel.event;

import de.tohemi.justparty.datamodel.event.ConcreteEvent;
import de.tohemi.justparty.datamodel.event.EventType;

import java.net.URL;

/**
 * Created by Heiko on 04.11.2015.
 */
public class BirthdayEvent extends ConcreteEvent {

    private URL amazonWishlistLink;

    public URL getAmazonWishlistLink() {
        return amazonWishlistLink;
    }

    public void setAmazonWishlistLink(URL amazonWishlistLink) {
        this.amazonWishlistLink = amazonWishlistLink;
    }

    public BirthdayEvent(int id) {
        super(id);
    }

    @Override
    public EventType getEventType() {
        return EventType.BIRTHDAY;
    }
}
