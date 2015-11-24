package de.tohemi.justparty.businesslogic;

import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserRoles;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.wrapper.EMail;

/**
 * Created by Micha Piertzik on 23.11.2015.
 */
public class UserHandler {

    public static final int MIN_PASSWD_LENGTH = 4;

    public Error createUser(String email, String password, String matchingPassword, boolean acceptedTerms) {
        User user;
        try {
            user = new User(new EMail(email));
        } catch (InvalidEmailException e) {
            //log exception
            return new Error("register.error.email", ErrorType.EMAIL);
        }
        if(!passwordValid(password, matchingPassword)) {
            return new Error("register.error.password", ErrorType.PASSWORD);
        }
        if (!acceptedTerms)
        {
            return new Error("register.error.terms", ErrorType.TERMS);
        }
        try {
            if(DBController.getInstance().userIsRegistered(user.getEmail())){
                return new Error("register.error.email.taken", ErrorType.EMAIL);
            }
            if(DBController.getInstance().changeToUser(user,HashFunction.getHash(password))){
                return null;
            }

        } catch (UserNotFoundException e) {
            if (DBController.getInstance().addUser(user, UserRoles.USER, HashFunction.getHash(password)))
            {
                return null;
            }
        }
        //Add User to DB

        return new Error("", null);
    }

    private boolean passwordValid(String password, String matchingPassword) {
        if (password != null && password.length() >= MIN_PASSWD_LENGTH && password.equals(matchingPassword)){
            return true;
        }
        return false;
    }
}
