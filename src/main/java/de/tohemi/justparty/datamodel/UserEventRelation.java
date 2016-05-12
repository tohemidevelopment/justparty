package de.tohemi.justparty.datamodel;

import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.event.Event;
import de.tohemi.justparty.viewInterface.JPDateFormat;


/**
 * Created by Micha Piertzik on 01.12.2015.
 */
public class UserEventRelation implements Comparable<UserEventRelation> {
    private User user;
    private Event event;
    private Accepted accepted;

    public UserEventRelation(Event event, User user) {
        this(event, user, null);
    }

    public UserEventRelation(Event event, User user, Accepted accepted) {
        this.user = user;
        this.event = event;
        if (event.getEventOwner() != null && event.getEventOwner().equals(user)) {
            setAccepted(Accepted.HOST);
        } else {
            setAccepted(accepted);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Accepted getAccepted() {
        return accepted;
    }

    public void setAccepted(Accepted accepted) {
        if (!getHosted()) {
            this.accepted = accepted;
        }
    }

    public String getDate() {
        java.util.Date begin = event.getBegin();
        if (begin != null) {
            return JPDateFormat.getSimpleDateFormat().format(begin.getTime());
        }
        return "";
    }

    public String getName() {
        return event.getName();
    }

    public int getId() {
        return event.getId();
    }

    public boolean getHosted() {
        if (accepted == null){
            return false;
        }
        return this.accepted.equals(Accepted.HOST);
    }

    @Override
    public int compareTo(UserEventRelation object) {
        return this.accepted.ordinal() - object.accepted.ordinal();
    }

    @Override
    public boolean equals(Object obj) { return super.equals(obj); }

    @Override
    public int hashCode() {return super.hashCode();}
}
