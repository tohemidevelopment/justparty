package de.tohemi.justparty.datamodel;

import de.tohemi.justparty.view_interface.JPDateFormat;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Heiko on 04.11.2015.
 */
public class ConcreteEvent implements Event {
    private String name;
    private String description;
    private Date begin;
    private Date end;
    private Location location;
    private User eventOwner;
    private List<UserEventRelation> guests;
    //TODO: Implement class for things to bring with to party
    //private List<Things> things;
    private URL facebookLink;
    private URL googlePlusLink;
    private URL spotifyPlaylistLink;
    private int id;
    private URL wishlistLink;

    public ConcreteEvent(int id) {
        this.id = id;
    }

    public ConcreteEvent(String name, User eventOwner){
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

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
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

    public List<UserEventRelation> getGuests() {
        return guests;
    }

    public void setGuests(List<UserEventRelation> guests) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public URL getWishlistLink() {
        return wishlistLink;
    }

    public void setWishlistLink(URL wishlistLink) {
        this.wishlistLink = wishlistLink;
    }
}