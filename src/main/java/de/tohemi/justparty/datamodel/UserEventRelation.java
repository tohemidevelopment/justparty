package de.tohemi.justparty.datamodel;

import de.tohemi.justparty.view_interface.JPDateFormat;


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
        if (event.getEventOwner() != null && event.getEventOwner().equals(user)) {
            accepted = Accepted.HOST;
        }
        this.user = user;
        this.event = event;
        setAccepted(accepted);
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

    public int compareTo(UserEventRelation object) {

        return this.accepted.ordinal() - object.accepted.ordinal();
    }
}
