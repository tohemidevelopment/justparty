package main.java.de.tohemi.justparty.datamodel.exceptions;

/**
 * Created by Micha Piertzik on 12.11.2015.
 */
public class ZipCodeInvalidException extends Throwable {
    public ZipCodeInvalidException() {
    }

    public ZipCodeInvalidException(String message) {
        super(message);
    }

    public ZipCodeInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZipCodeInvalidException(Throwable cause) {
        super(cause);
    }

    public ZipCodeInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}