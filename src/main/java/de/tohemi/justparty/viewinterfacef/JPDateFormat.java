package de.tohemi.justparty.viewinterfacef;

import java.text.SimpleDateFormat;

/**
 * Created by Micha Piertzik on 30.11.2015.
 */
public class JPDateFormat {

    private JPDateFormat(){}

    public static SimpleDateFormat getSimpleDateFormat() {
        //TODO: check locale
        return new SimpleDateFormat("dd.MM.yyyy");
    }
}
