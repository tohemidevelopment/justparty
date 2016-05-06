package de.tohemi.justparty.datamodel.event;

import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserEventRelation;

import java.net.URL;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Heiko on 04.11.2015.
 */
public class ConcreteEvent implements Event {
    private String eventname;
    private String description;
    private Timestamp begin;
    private Timestamp end;
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

    ConcreteEvent(int id) {
        this.id = id;
    }

    ConcreteEvent() {
    }

    public String getName() {
        return eventname;
    }

    public void setName(String name) {
        this.eventname = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getBegin() {
        return begin;
    }

    public void setBegin(Timestamp begin) { this.begin = begin; }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
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