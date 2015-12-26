package de.tohemi.justparty.database.datainterfaces;

import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.Address;
import de.tohemi.justparty.datamodel.User;

import java.util.Calendar;

/**
 * Created by Heiko on 26.12.2015.
 */
public class DBUser {
    String email;
    DBUserController controller= DBUserController.getInstance();
    public DBUser(String email){
        this.email=email;
    }

    public User getEmailuser(){
        return new User(email);
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return controller.getLastName(email);
    }

    public void setLastName(String lastName) {
        controller.setLastName(lastName,email);
    }

    public String getFirstName() {
        return controller.getFirstName(email);
    }

    public void setFirstName(String firstName) {
        controller.setFirstName(firstName, email);
    }

    public DBAddress getAddress() {
        return controller.getAddress(email);
    }

    public void setAddress(DBAddress address) {
        controller.setAddress(address, email);
    }

    public Calendar getBirthday() {
        return controller.getBirthday(email);
    }

    public void setBirthday(Calendar birthday) {
        controller.setBirthday(birthday, email);
    }


}
