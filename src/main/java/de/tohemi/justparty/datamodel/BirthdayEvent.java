package main.java.de.tohemi.justparty.datamodel;

/**
 * Created by Heiko on 04.11.2015.
 */
public class BirthdayEvent extends Event {

    private String amazonWishlistLink;
    public BirthdayEvent(String name, User eventOwner) {
        super(name, eventOwner);
    }
}
