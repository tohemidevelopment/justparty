package main.java.de.tohemi.justparty.datamodel;

import java.text.DecimalFormat;

/**
 * Created by xce35l7 on 07.11.2015.
 */
public class ZipCode {
    public static final String FORMAT = "00000";
    private int zipInt;

    public ZipCode(int zip) {
        this.zipInt = zip;
        //TODO: validate zip code
    }

    public int getZipInt() {
        return zipInt;
    }

    @Override
    public String toString() {
        return new DecimalFormat(ZipCode.FORMAT).format(zipInt);
    }
}
