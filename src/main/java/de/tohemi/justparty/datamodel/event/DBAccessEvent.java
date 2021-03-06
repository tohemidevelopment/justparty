package de.tohemi.justparty.datamodel.event;

import de.tohemi.justparty.database.controller.DBDeclarationController;
import de.tohemi.justparty.database.controller.DBEventController;
import de.tohemi.justparty.database.controller.DBGuestlistController;
import de.tohemi.justparty.database.tables.GuestlistDBTabelle;
import de.tohemi.justparty.datamodel.Accepted;
import de.tohemi.justparty.datamodel.Declaration;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.UserEventRelation;
import de.tohemi.justparty.datamodel.user.User;

import java.net.URL;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Micha Piertzik on 02.05.2016.
 */
public class DBAccessEvent implements Event {

    private int id;

    public DBAccessEvent(final int id) {
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
    public Timestamp getBegin() {
        return DBEventController.getInstance().getBegin(id);
    }

    @Override
    public void setBegin(Timestamp begin) {
        DBEventController.getInstance().setBegin(id, begin);
    }

    @Override
    public Timestamp getEnd() {
        return DBEventController.getInstance().getEnd(id);
    }

    @Override
    public void setEnd(Timestamp end) {
        DBEventController.getInstance().setEnd(id, end);
    }

    @Override
    public Location getLocation() {
        return DBEventController.getInstance().getLocation(id);
    }

    @Override
    public void setLocation(Location location) {
        DBEventController.getInstance().setLocation(id, location);
    }

    @Override
    public User getEventOwner() {
        return DBEventController.getInstance().getEventOwner(id);
    }

    @Override
    public void setEventOwner(User eventOwner) {
        DBEventController.getInstance().setEventOwner(id, eventOwner);
    }

    @Override
    public List<UserEventRelation> getGuests() {
        return DBGuestlistController.getInstance().getInvitedUsers(id);
    }

    @Override
    public void setGuests(List<UserEventRelation> guests) {
        for (UserEventRelation ure : guests) {
            DBGuestlistController.getInstance().addGuestToEvent(ure.getEvent(), ure.getUser(), ure.getAccepted().getValue());
        }
    }

    @Override
    public void addGuest(User guest) {
        DBGuestlistController.getInstance().addGuestToEvent(this, guest, GuestlistDBTabelle.NOTSURE);
    }

    @Override
    public URL getFacebookLink() {
        return DBEventController.getInstance().getFacebookLink(id);
    }

    @Override
    public void setFacebookLink(URL facebookLink) {
        DBEventController.getInstance().setFacebookLink(id, facebookLink);
    }

    @Override
    public URL getGooglePlusLink() {
        return DBEventController.getInstance().getGooglePlusLink(id);
    }

    @Override
    public void setGooglePlusLink(URL googlePlusLink) {
        DBEventController.getInstance().setGooglePlusLink(id, googlePlusLink);
    }

    @Override
    public URL getSpotifyPlaylistLink() {
        return DBEventController.getInstance().getSpotifyLink(id);
    }

    @Override
    public void setSpotifyPlaylistLink(URL spotifyPlaylistLink) {
        DBEventController.getInstance().setSpotifyLink(id, spotifyPlaylistLink);
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
        return DBEventController.getInstance().getWishlistLink(id);
    }

    @Override
    public void setWishlistLink(URL wishlistLink) {
        DBEventController.getInstance().setWishlistLink(id, wishlistLink);
    }

    @Override
    public EventType getEventType() {
        return DBEventController.getInstance().getType(id);
    }

    @Override
    public void setEventType(EventType type) {
        DBEventController.getInstance().setType(id, type);
    }

    @Override
    public List<Declaration> getDeclaration() {
        return DBDeclarationController.getInstance().getDeclarations(this);
    }

    @Override
    public void setDeclaration(List<Declaration> declarations) {
        for (Declaration declaration : declarations) {
            addDeclaration(declaration);
        }
    }

    @Override
    public void addDeclaration(Declaration declaration) {
        DBDeclarationController.getInstance().addDeclaration(declaration);
    }

    @Override
    public Object getProperty(String key) {
        return null;
    }

    @Override
    public void setProperty(String key, Object value) {

    }
}
