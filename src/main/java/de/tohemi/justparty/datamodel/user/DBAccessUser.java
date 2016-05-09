package de.tohemi.justparty.datamodel.user;

import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.database.datainterfaces.DBAddress;
import de.tohemi.justparty.datamodel.address.Address;
import de.tohemi.justparty.datamodel.wrapper.EMail;

import java.sql.Date;

/**
 * Created by Heiko on 26.12.2015.
 */
public class DBAccessUser implements User {
    private String email;
    private DBUserController controller;

    public DBAccessUser(String email) {
        controller = DBUserController.getInstance();
        this.email = email;
    }

    public DBAccessUser(EMail email) {
        controller = DBUserController.getInstance();
        this.email = email.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(EMail email) {

    }

    public void setEmail(String email) {

    }

    public String getLastName() {
        return controller.getLastName(email);
    }

    public void setLastName(String lastName) {
        controller.setLastName(lastName, email);
    }

    public String getFirstName() {
        return controller.getFirstName(email);
    }

    public void setFirstName(String firstName) {
        controller.setFirstName(firstName, email);
    }

    public Address getAddress() {
        return controller.getAddress(email);
    }

    public void setAddress(Address address) {

    }

    public void setAddress(DBAddress address) {
        controller.setAddress(address, email);
    }

    public Date getBirthday() {
        return controller.getBirthday(email);
    }

    public void setBirthday(Date birthday) {
        controller.setBirthday(birthday, email);
    }


}
