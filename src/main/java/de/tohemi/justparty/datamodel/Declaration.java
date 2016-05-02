package de.tohemi.justparty.datamodel;

/**
 * Created by xce35l2 on 25.04.2016.
 */
public class Declaration {
    private String name;
    private User user;
    private boolean bringWithByAll;
    private int event_id;

    public Declaration(String name, User user, boolean bringWithByAll, int event_id){
        this.bringWithByAll = bringWithByAll;
        this.event_id = event_id;
        this.name = name;
        this.user = user;
    }

    public int getEventId() {
        return event_id;
    }

    public void setEventId(int event_id) { this.event_id = event_id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getBringWithByAll() {
        return bringWithByAll;
    }

    public void setBringWithByAll(boolean bringWithByAll) {
        this.bringWithByAll = bringWithByAll;
    }
}
