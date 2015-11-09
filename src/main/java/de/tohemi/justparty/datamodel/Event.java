package main.java.de.tohemi.justparty.datamodel;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Heiko on 04.11.2015.
 */
public class Event {
    private String name;
    private String description;
    private Calendar begin;
    private Calendar end;
    private Location location;
    private User eventOwner;
    private List<Person> guests;
    private URL facebookLink;
    private URL googlePlusLink;
    private URL spotifyPlaylistLink;


    public Event(String name, User eventOwner){
        this.name=name;
        this.eventOwner=eventOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getGuests() {
        return guests;
    }

    public void setGuests(List<Person> guests) {
        this.guests = guests;
    }

    public void addGuest(List<Person> addguests){
        guests.addAll(addguests);
    }
}
