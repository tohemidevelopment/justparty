package de.tohemi.justparty.datamodel.event;

import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserEventRelation;

import de.tohemi.justparty.datamodel.user.User;

import java.net.URL;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Micha Piertzik on 02.05.2016.
 */
public interface Event {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Timestamp getBegin();

    void setBegin(Timestamp begin);

    Timestamp getEnd();

    void setEnd(Timestamp end);

    Location getLocation();

    void setLocation(Location location);

    User getEventOwner();

    void setEventOwner(User eventOwner);

    List<UserEventRelation> getGuests();

    void setGuests(List<UserEventRelation> guests);

    URL getFacebookLink();

    void setFacebookLink(URL facebookLink);

    URL getGooglePlusLink();

    void setGooglePlusLink(URL googlePlusLink);

    URL getSpotifyPlaylistLink();

    void setSpotifyPlaylistLink(URL spotifyPlaylistLink);

    int getId();

    void setId(int id);

    URL getWishlistLink();

    void setWishlistLink(URL wishlistLink);

    default EventType getEventType() {
        return EventType.DEFAULT;
    }
}
