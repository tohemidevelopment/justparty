package main.java.de.tohemi.justparty.datamodel;

import java.util.Calendar;

/**
 * Created by Heiko on 04.11.2015.
 */
public class User extends Person{
    private String name;
    //Zu Name: vor- und nachname getrennt oder zusammen?
    private Address address;
    private Calendar birthday;

    public User(EMail email) {

        super(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public EventOwner convertToEventOwner() {
        return new EventOwner(this);
    }
}
