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

    /**
     *
     * @deprecated  use other instead
     */
    @Deprecated
    public ConcreteUser(EMail email) {
        this.email = email.toString();
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(EMail email) {
        this.email = email.toString();
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
