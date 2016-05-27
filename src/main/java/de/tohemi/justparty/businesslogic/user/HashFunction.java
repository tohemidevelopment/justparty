package de.tohemi.justparty.businesslogic.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Micha Piertzik on 23.11.2015.
 */
public class HashFunction {

    private HashFunction(){}

    public static String getHash(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
