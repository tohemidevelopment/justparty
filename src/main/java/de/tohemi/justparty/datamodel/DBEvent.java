package de.tohemi.justparty.datamodel;

import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created by Micha Piertzik on 02.05.2016.
 * TODO: insert DB querys in every method
 */
public class DBEvent implements Event {

    private int id;

    public DBEvent(int id) {

        this.id = id;
    }

    public String getName() {
        return null;
    }

    public void setName(String name) {
        System.out.println("-------- " + name + "   " + id);
    }

    public String getDescription() {
        return null;
    }

    public void setDescription(String description) {

    }

    public Date getBegin() {
        return null;
    }

    public void setBegin(Date begin) {

    }

    public Date getEnd() {
        return null;
    }

    public void setEnd(Date end) {

    }

    public Location getLocation() {
        return null;
    }

    public void setLocation(Location location) {

    }

    public User getEventOwner() {
        return null;
    }

    public void setEventOwner(User eventOwner) {

    }

    public List<UserEventRelation> getGuests() {
        return null;
    }

    public void setGuests(List<UserEventRelation> guests) {

    }

    public URL getFacebookLink() {
        return null;
    }

    public void setFacebookLink(URL facebookLink) {

    }

    public URL getGooglePlusLink() {
        return null;
    }

    public void setGooglePlusLink(URL googlePlusLink) {

    }

    public URL getSpotifyPlaylistLink() {
        return null;
    }

    public void setSpotifyPlaylistLink(URL spotifyPlaylistLink) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public URL getWishlistLink() {
        return null;
    }

    public void setWishlistLink(URL wishlistLink) {

    }
}
