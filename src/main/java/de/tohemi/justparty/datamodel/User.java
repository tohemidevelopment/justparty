package de.tohemi.justparty.datamodel;

import de.tohemi.justparty.datamodel.wrapper.EMail;

import java.util.Calendar;

/**
 * Created by Heiko on 04.11.2015.
 */
public class User extends Person {
    //private String username;
   // private String password;
   // private String matchingPassword;
    private String lastName;
    private String firstName;
    private Address address;
    private Calendar birthday;

    public User(String email) {
        super(email);
    }

    public User(String email, String lastName, String firstName, Address address, Calendar birthday) {
        super(email);
        //this.password = password;
       // this.matchingPassword = matchingPassword;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.birthday = birthday;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    //


    /*public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
    */
}
