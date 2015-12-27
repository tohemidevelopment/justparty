package de.tohemi.justparty.businesslogic.user;

import de.tohemi.justparty.businesslogic.*;
import de.tohemi.justparty.businesslogic.Error;
import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.database.datainterfaces.DBUser;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserRoles;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.util.IDGenerator;

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
            return new de.tohemi.justparty.businesslogic.Error("register.error.email", ErrorType.EMAIL);
        }
        if (!passwordValid(password, matchingPassword)) {
            return new Error("register.error.password", ErrorType.PASSWORD);
        }
        if (!acceptedTerms) {
            return new Error("register.error.terms", ErrorType.TERMS);
        }
        //At this Point: Userdata is Valid
        DBController dbController = DBController.getInstance();
        try {
            if (dbController.userIsRegistered(user.getEmail())) {
                //User ist registered as USER
                return new Error("register.error.email.taken", ErrorType.EMAIL);
            }
            //User is registered as NOUSER
            if (dbController.changeToUser(user, HashFunction.getHash(password))) {
                //User role updated in DB
                sendVerificationEmail(user.getEmail());
                return null;
            }

        } catch (UserNotFoundException e) {
            //User not in DB
            if (dbController.addUser(user, UserRoles.USER, HashFunction.getHash(password))) {
                //User added to DB
                sendVerificationEmail(user.getEmail());
                return null;
            }
        }
        return new Error("", null);
    }

    private void sendVerificationEmail(String email) {
        EmailSender sender= new EmailSender();
        String id= IDGenerator.generateID(50);
        sender.sendEmailVerification(new DBUser(email), id);
        DBUserController.getInstance().addVerificationData(email, id);

    }

    private boolean passwordValid(String password, String matchingPassword) {

        if (password == null) {
            return false;
        }
        if (password.length() < MIN_PASSWD_LENGTH) {
            return false;
        }
        if (!password.equals(matchingPassword)) {
            return false;
        }
        return true;
    }

    public Error verifyEmail(String verificationID){
        if(DBUserController.getInstance().verificationIDIsValid(verificationID)){
            DBUserController.getInstance().verifyEmail(verificationID);
            return null;
        }
        return new Error("Bestätigung Fehlgeschlafen",ErrorType.GENERAL);
    }
}
