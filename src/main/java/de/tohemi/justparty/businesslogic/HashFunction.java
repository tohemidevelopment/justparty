package de.tohemi.justparty.businesslogic;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Micha Piertzik on 23.11.2015.
 */
public class HashFunction {
    public static String getHash(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);
        return hashedPassword;
    }
}
