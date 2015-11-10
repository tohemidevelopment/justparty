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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getBegin() {
        return begin;
    }

    public void setBegin(Calendar begin) {
        this.begin = begin;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getEventOwner() {
        return eventOwner;
    }

    public void setEventOwner(User eventOwner) {
        this.eventOwner = eventOwner;
    }

    public List<Person> getGuests() {
        return guests;
    }

    public void setGuests(List<Person> guests) {
        this.guests = guests;
    }

    public URL getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(URL facebookLink) {
        this.facebookLink = facebookLink;
    }

    public URL getGooglePlusLink() {
        return googlePlusLink;
    }

    public void setGooglePlusLink(URL googlePlusLink) {
        this.googlePlusLink = googlePlusLink;
    }

    public URL getSpotifyPlaylistLink() {
        return spotifyPlaylistLink;
    }

    public void setSpotifyPlaylistLink(URL spotifyPlaylistLink) {
        this.spotifyPlaylistLink = spotifyPlaylistLink;
    }
}