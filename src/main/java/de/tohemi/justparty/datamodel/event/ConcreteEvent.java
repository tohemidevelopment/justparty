package de.tohemi.justparty.datamodel.event;

import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.UserEventRelation;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.util.SystemProperties;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //TODO: Implement class for things to bring with to party private List<Things> things;
    private String facebookLink;
    private URL googlePlusLink;
    private String spotifyPlaylistLink;
    private int id;
    private String wishlistLink;
    private EventType eventType;
    private Map properties;

    public ConcreteEvent(int id) {
        this();
        this.id = id;
    }

    public ConcreteEvent() {
        properties = new HashMap<String, Object>();
    }

    @Override
    public String getName() {
        return eventname;
    }

    @Override
    public void setName(String name) {
        this.eventname = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Timestamp getBegin() {
        return begin;
    }

    @Override
    public void setBegin(Timestamp begin) {
        this.begin = begin;
    }

    @Override
    public Timestamp getEnd() {
        return end;
    }

    @Override
    public void setEnd(Timestamp end) {
        this.end = end;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public User getEventOwner() {
        return eventOwner;
    }

    @Override
    public void setEventOwner(User eventOwner) {
        this.eventOwner = eventOwner;
    }

    @Override
    public List<UserEventRelation> getGuests() {
        return guests;
    }

    @Override
    public void setGuests(List<UserEventRelation> guests) {
        this.guests = guests;
    }

    @Override
    public URL getFacebookLink() {
        try {
            return new URL(facebookLink);
        } catch (MalformedURLException e) {
            SystemProperties.getLogger().logException(e);
        }
        return null;
    }

    @Override
    public void setFacebookLink(URL facebookLink) {
        if(facebookLink== null)
        {
            this.facebookLink = null;
            return;
        }
        this.facebookLink = facebookLink.toString();
    }

    @Override
    public URL getGooglePlusLink() {
        return googlePlusLink;
    }

    @Override
    public void setGooglePlusLink(URL googlePlusLink) {
        this.googlePlusLink = googlePlusLink;
    }

    @Override
    public URL getSpotifyPlaylistLink() {
        try {
            return new URL(spotifyPlaylistLink);
        } catch (MalformedURLException e) {
            SystemProperties.getLogger().logException(e);
        }
        return null;
    }

    @Override
    public void setSpotifyPlaylistLink(URL spotifyPlaylistLink) {
        if(spotifyPlaylistLink== null)
        {
            this.spotifyPlaylistLink = null;
            return;
        }
        this.spotifyPlaylistLink = spotifyPlaylistLink.toString();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public URL getWishlistLink() {
        try {
            return new URL(wishlistLink);
        } catch (MalformedURLException e) {
            SystemProperties.getLogger().logException(e);
        }
        return null;
    }

    @Override
    public void setWishlistLink(URL wishlistLink) {
        if(wishlistLink== null)
        {
            this.wishlistLink = null;
            return;
        }
        this.wishlistLink = wishlistLink.toString();
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public void setEventType(EventType eventType) {

        this.eventType = eventType;
    }

    @Override
    public Object getProperty(String key) {
        return properties.get(key);
    }

    @Override
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }
}