package de.tohemi.justparty.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Micha Piertzik on 01.12.2015.
 */
public abstract class JPController {

    protected String getMailFromLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
