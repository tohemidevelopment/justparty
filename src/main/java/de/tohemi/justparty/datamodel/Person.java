package de.tohemi.justparty.datamodel;

import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.wrapper.EMail;

/**
 * Created by Heiko on 04.11.2015.
 */
public class Person {
    private EMail email;

    public Person(EMail email){
        this.email=email;
    }

    public String getEmail() {
        return email.toString();
    }

    public void setEmail(EMail email) {
        this.email = email;
    }

    public void setEmail(String email) {
        try {
            this.email = new EMail(email);
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        }
    }
}
