package de.tohemi.justparty.datamodel.user;

import de.tohemi.justparty.datamodel.address.Address;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(EMail email) {
        this.email = email.toString();
    }

    public void setEmail(String email) {
        try {
            new EMail(email); //TEST IF VALID
            this.email = email;
        } catch (InvalidEmailException e) {
            SystemProperties.getLogger().logException(e);
        }
    }

    public String getLastName() {
        return null;
    }

    public void setLastName(String lastName) {
    }

    public String getFirstName() {
        return null;
    }

    public void setFirstName(String firstName) {
    }

    public Address getAddress() {
        return null;
    }

    public void setAddress(Address address) {
    }

    public Date getBirthday() {
        return null;
    }

    public void setBirthday(Date birthday) {
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
