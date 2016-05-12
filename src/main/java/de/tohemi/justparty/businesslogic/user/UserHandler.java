package de.tohemi.justparty.businesslogic.user;

import de.tohemi.justparty.businesslogic.EmailSender;
import de.tohemi.justparty.businesslogic.Error;
import de.tohemi.justparty.businesslogic.ErrorType;
import de.tohemi.justparty.businesslogic.UserNotFoundException;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.UserRoles;
import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.user.DBAccessUser;
import de.tohemi.justparty.datamodel.user.User;
import de.tohemi.justparty.datamodel.user.UserFactory;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.util.IDGenerator;
import de.tohemi.justparty.util.SystemProperties;

/**
 * Created by Micha Piertzik on 23.11.2015.
 */
public class UserHandler {

    public static final int MIN_PASSWD_LENGTH = 4;

    public Error createUser(String email, String password, String matchingPassword, boolean acceptedTerms) {
        Error error = new Error("", null);
        User user = null;
        try {
            user = UserFactory.create(new EMail(email));
        } catch (InvalidEmailException e) {
            SystemProperties.getLogger().logException(e);
            error = new Error("register.error.email", ErrorType.EMAIL);
        }
        if (!passwordValid(password, matchingPassword)) {
            error =  new Error("register.error.password", ErrorType.PASSWORD);
        }
        if (!acceptedTerms) {
            error =  new Error("register.error.terms", ErrorType.TERMS);
        }
        //At this Point: Userdata is Valid
        DBUserController dbController = DBUserController.getInstance();
        try {
            if (dbController.userIsRegistered(user.getEmail())) {
                //User ist registered as USER
                error =  new Error("register.error.email.taken", ErrorType.EMAIL);
            }
            //User is registered as NOUSER
            if (dbController.changeToUser(user, HashFunction.getHash(password))) {
                //User role updated in DB
                sendVerificationEmail(user.getEmail());
                error =  null;
            }

        } catch (UserNotFoundException e) {
            SystemProperties.getLogger().logException(e);
            //User not in DB
            if (dbController.addUser(user, UserRoles.USER, HashFunction.getHash(password))) {
                //User added to DB
                sendVerificationEmail(user.getEmail());
                error = null;
            }
        }
        return error;
    }

    private void sendVerificationEmail(String email) {
        EmailSender sender = new EmailSender();
        String id = IDGenerator.generateID(50);
        sender.sendEmailVerification(new DBAccessUser(email), id);
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

    public Error verifyEmail(String verificationID) {
        if (DBUserController.getInstance().verificationIDIsValid(verificationID)) {
            DBUserController.getInstance().verifyEmail(verificationID);
            return null;
        }
        return new Error("Bestätigung Fehlgeschlafen", ErrorType.GENERAL);
    }
}
