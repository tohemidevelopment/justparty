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

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(EMail email) {
//implement
    }

    @Override
    public void setEmail(String email) {
//implement
    }

    @Override
    public String getLastName() {
        return controller.getLastName(email);
    }

    @Override
    public void setLastName(String lastName) {
        controller.setLastName(lastName, email);
    }

    @Override
    public String getFirstName() {
        return controller.getFirstName(email);
    }

    @Override
    public void setFirstName(String firstName) {
        controller.setFirstName(firstName, email);
    }

    @Override
    public Address getAddress() {
        return controller.getAddress(email);
    }

    @Override
    public void setAddress(Address address) {//implement

    }

    public void setAddress(DBAddress address) {
        controller.setAddress(address, email);
    }

    @Override
    public Date getBirthday() {
        return controller.getBirthday(email);
    }

    @Override
    public void setBirthday(Date birthday) {
        controller.setBirthday(birthday, email);
    }


}
