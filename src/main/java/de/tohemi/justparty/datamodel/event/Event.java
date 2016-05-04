package de.tohemi.justparty.datamodel.event;

import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserEventRelation;

import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created by Micha Piertzik on 02.05.2016.
 */
public interface Event {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Date getBegin();

    void setBegin(Date begin);

    Date getEnd();

    void setEnd(Date end);

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
}
