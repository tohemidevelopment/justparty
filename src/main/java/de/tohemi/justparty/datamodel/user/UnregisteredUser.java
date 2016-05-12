package de.tohemi.justparty.datamodel.user;

import de.tohemi.justparty.datamodel.address.Address;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.util.SystemProperties;

import java.sql.Date;

/**
 * Created by Heiko on 04.11.2015.
 */
public class UnregisteredUser implements User {
    private String email;

    public UnregisteredUser(String email) {
        this.email = email;
    }

    public UnregisteredUser(EMail email) {
        this.email = email.toString();
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

        if (EMail.isEmailValid(email)) {
            this.email = email;
        }
        SystemProperties.getLogger().logError("Email not valid: " + email);
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public void setLastName(String lastName) {//implement
    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public void setFirstName(String firstName) {//implement
    }

    @Override
    public Address getAddress() {
        return null;
    }

    @Override
    public void setAddress(Address address) {//implement
    }

    @Override
    public Date getBirthday() {
        return null;
    }

    @Override
    public void setBirthday(Date birthday) { //implement
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof UnregisteredUser)) {
            return false;
        }
        UnregisteredUser unregisteredUser = (UnregisteredUser) obj;
        return email.equals(unregisteredUser.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
