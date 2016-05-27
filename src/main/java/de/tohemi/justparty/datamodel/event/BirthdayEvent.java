package de.tohemi.justparty.datamodel.event;


import java.net.URL;

/**
 * Created by Heiko on 04.11.2015.
 */
public class BirthdayEvent extends ConcreteEvent {

    private URL amazonWishlistLink;
    private EventType type;

    public BirthdayEvent(int id) {
        super(id);
        type = EventType.BIRTHDAY;
    }


    public URL getAmazonWishlistLink() {
        return amazonWishlistLink;
    }

    public void setAmazonWishlistLink(URL amazonWishlistLink) {
        this.amazonWishlistLink = amazonWishlistLink;
    }

    @Override
    public EventType getEventType() {
        return EventType.BIRTHDAY;
    }
}
