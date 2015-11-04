package main.java.de.tohemi.justparty;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Heiko on 04.11.2015.
 */
public class Event {
    private String name;
    private String description;
    private Calendar begin;
    private Calendar end;
    private Address address;
    private User eventOwner;
    private ArrayList<Person> guests;
    private String facebookLink;
    private String googlePlusLink;
    private String spotifyPlaylistLink;


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

    public ArrayList<Person> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Person> guests) {
        this.guests = guests;
    }
}
