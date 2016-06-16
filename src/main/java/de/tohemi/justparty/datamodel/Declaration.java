package de.tohemi.justparty.datamodel;

import de.tohemi.justparty.datamodel.user.User;

/**
 * Created by xce35l2 on 25.04.2016.
 */
public class Declaration {
    private int id;
    private String name;
    private User user;
    private boolean bringWithByAll;
    private int eventID;

    public Declaration(String name, User user, boolean bringWithByAll, int eventID){
        this.bringWithByAll = bringWithByAll;
        this.eventID = eventID;
        this.name = name;
        this.user = user;
    }
    public Declaration(int id,String name, User user, boolean bringWithByAll, int eventID){
        this.id=id;
        this.bringWithByAll = bringWithByAll;
        this.eventID = eventID;
        this.name = name;
        this.user = user;
    }

    public int getId() {
        return id;
    }
    public int getEventId() {
        return eventID;
    }

    public void setEventId(int eventID) { this.eventID = eventID; }

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
