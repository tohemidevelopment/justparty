package de.tohemi.justparty;

import java.util.Calendar;

/**
 * Created by Heiko on 04.11.2015.
 */
public class User extends Person{
    private String name;
    private Address address;
    private Calendar birthday;

    public User(String email) {
        super(email);
    }

}
