package de.tohemi.justparty.datamodel.user;

import de.tohemi.justparty.datamodel.address.Address;
import de.tohemi.justparty.datamodel.wrapper.EMail;

import java.sql.Date;

/**
 * Created by Heiko on 04.11.2015.
 */
public class ConcreteUser implements User {
    private String email;
    private String lastName;
    private String firstName;
    private Address address;
    private Date birthday;

    public ConcreteUser(String email) {
        this.email = email;
    }

    @Deprecated
    public ConcreteUser(EMail email) {
        this.email = email.toString();
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(EMail email) {
        this.email = email.toString();
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
