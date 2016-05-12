package de.tohemi.justparty.viewInterface;

import java.text.SimpleDateFormat;

/**
 * Created by Micha Piertzik on 30.11.2015.
 */
public class JPDateFormat {

    public static SimpleDateFormat getSimpleDateFormat() {
        //TODO: check locale
        return new SimpleDateFormat("dd.MM.yyyy");
    }
}
