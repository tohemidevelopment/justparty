package de.tohemi.justparty.datamodel.wrapper;

import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;

/**
 * Created by xce35l7 on 07.11.2015.
 */
public class EMail {
    private static final String REGEX_MAIL = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}";
    private String email;

    public EMail(final String email) throws InvalidEmailException {
        if (!isEmailValid(email)){
            throw new InvalidEmailException("Invalid email: " + email);
        }
        this.email = email.toLowerCase();
    }

    public static boolean isEmailValid(String mail) {
        return mail.toLowerCase().matches(REGEX_MAIL);
    }

    @Override
    public String toString() {
        return email;
    }
}