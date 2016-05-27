package de.tohemi.justparty.businesslogic;

/**
 * Created by Heiko on 24.11.2015.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        //default constructor
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
