package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.wrapper.EMail;

/**
 * Created by Micha Piertzik on 23.11.2015.
 */
public class UserHandler {
    public de.tohemi.justparty.businesslogic.Error createUser(String username, String email, String password, String matchingPassword) {
        User user;
        try {
            user = new User(new EMail(email));
        } catch (InvalidEmailException e) {
            //log exception
            return new Error("register.error.email", ErrorType.EMAIL);
        }
        if (username != null && username.length() <= 2 || !DBController.getInstance().usernameAvailable(username)){
            return new Error("register.error.username", ErrorType.USERNAME);
        }
        user.setUsername(username);
        if(!DBController.getInstance().emailAvailable(user.getEmail())){
            return new Error("register.error.email.taken", ErrorType.EMAIL);
        }
        if(!passwordValid(password, matchingPassword)) {
            return new Error("register.error.password", ErrorType.PASSWORD);
        }
        //Add User to DB
        if (DBController.getInstance().createUserInDB(user, HashFunction.getHash(password)))
        {
            //Successful Registration
            return null;
        }
        return new Error("", null);
    }

    private boolean passwordValid(String password, String matchingPassword) {
        if (password != null && password.length() >= 4 && password.equals(matchingPassword)){
            return true;
        }
        return false;
    }
}
