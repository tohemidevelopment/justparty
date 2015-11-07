package main.java.de.tohemi.justparty.datamodel;

import main.java.de.tohemi.justparty.datamodel.EMail;

/**
 * Created by Heiko on 04.11.2015.
 */
public class Person {
    private EMail email;

    public Person(EMail email){
        this.email=email;
    }

    public EMail getEmail() {
        return email;
    }

    public String getEmailAsString() {
        return email.toString();
    }

    public void setEmail(EMail email) {
        this.email = email;
    }
}
