package de.tohemi.justparty.view_interface;

import java.text.SimpleDateFormat;
import java.util.Formatter;

/**
 * Created by Micha Piertzik on 30.11.2015.
 */
public class JPDateFormat {


    public static SimpleDateFormat getSimpleDateFormat() {
        //TODO: check locale
        return new SimpleDateFormat("dd.MM.yyyy");
    }
}
