package de.tohemi.justparty.datamodel.event;

import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserEventRelation;
import java.net.URL;
import java.sql.Date;
import java.util.List;

/**
 * Created by Micha Piertzik on 02.05.2016.
 * TODO: insert DB querys in every method
 */
public class DBAccessEvent implements Event {

    private int id;

    DBAccessEvent(final int id) {

        this.id = id;
    }

    @Override
    public String getName() {
        return DBEventController.getInstance().getName(id);
    }

    @Override
    public void setName(String name) {
        DBEventController.getInstance().setName(id, name);
    }

    @Override
    public String getDescription() {
        return DBEventController.getInstance().getDescription(id);
    }

    @Override
    public void setDescription(String description) {
        DBEventController.getInstance().setDescription(id, description);
    }

    @Override
    public Date getBegin() {
        return DBEventController.getInstance().getBegin(id);
    }

    @Override
    public void setBegin(Date begin) {
        DBEventController.getInstance().setBegin(id, begin);
    }

    @Override
    public Date getEnd() {
        return DBEventController.getInstance().getEnd(id);
    }

    @Override
    public void setEnd(Date end) {
        DBEventController.getInstance().setEnd(id, end);
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public void setLocation(Location location) {

    }

    @Override
    public User getEventOwner() {
        return null;
    }

    @Override
    public void setEventOwner(User eventOwner) {

    }

    @Override
    public List<UserEventRelation> getGuests() {
        return null;
    }

    @Override
    public void setGuests(List<UserEventRelation> guests) {

    }

    @Override
    public URL getFacebookLink() {
        return null;
    }

    @Override
    public void setFacebookLink(URL facebookLink) {

    }

    @Override
    public URL getGooglePlusLink() {
        return null;
    }

    @Override
    public void setGooglePlusLink(URL googlePlusLink) {

    }

    @Override
    public URL getSpotifyPlaylistLink() {
        return null;
    }

    @Override
    public void setSpotifyPlaylistLink(URL spotifyPlaylistLink) {

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
        return null;
    }

    @Override
    public void setWishlistLink(URL wishlistLink) {

    }
}
