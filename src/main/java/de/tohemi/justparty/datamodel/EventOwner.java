package main.java.de.tohemi.justparty.datamodel;

/**
 * Created by xce35l7 on 07.11.2015.
 */
public class EventOwner extends User {

    public EventOwner(User user) {
        super(user.getEmail());
        setAddress(user.getAddress());
        setBirthday(user.getBirthday());
        setName(user.getName());
    }
}
