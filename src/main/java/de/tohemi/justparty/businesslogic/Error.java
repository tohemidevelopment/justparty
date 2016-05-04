package de.tohemi.justparty.businesslogic;


/**
 * Created by Micha Piertzik on 23.11.2015.
 */
public class Error {
    private String msg;
    private ErrorType type;

    public Error(String msg, ErrorType type) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public ErrorType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString() + ": "+ msg;
    }
}
