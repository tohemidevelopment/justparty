package de.tohemi.justparty.datamodel;

import java.net.URL;

/**
 * Created by Heiko on 04.11.2015.
 */
public class BirthdayEvent extends Event {

    private URL amazonWishlistLink;

    public URL getAmazonWishlistLink() {
        return amazonWishlistLink;
    }

    public void setAmazonWishlistLink(URL amazonWishlistLink) {
        this.amazonWishlistLink = amazonWishlistLink;
    }

    public BirthdayEvent(String name, User eventOwner) {
        super(name, eventOwner);
    }
}
