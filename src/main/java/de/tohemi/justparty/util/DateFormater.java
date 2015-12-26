package de.tohemi.justparty.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Heiko on 26.12.2015.
 */
public class DateFormater {
    public static String formatDate(Calendar date) {
        SimpleDateFormat format=new SimpleDateFormat("dd.MM.yyyy");
        return format.format(date.getTime());
    }
}
