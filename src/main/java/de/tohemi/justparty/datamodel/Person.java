package de.tohemi.justparty.datamodel;

import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;
import de.tohemi.justparty.datamodel.wrapper.EMail;

/**
 * Created by Heiko on 04.11.2015.
 */
public class Person {
    private String email;

    public Person(String email){
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(EMail email) {
        this.email = email.toString();
    }

    public void setEmail(String email) {
        try {
            new EMail(email); //TEST IF VALID
            this.email =email;
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        }
    }
}
