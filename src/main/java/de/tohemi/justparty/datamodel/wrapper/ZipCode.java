package main.java.de.tohemi.justparty.datamodel.wrapper;

import main.java.de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;

import java.text.DecimalFormat;

/**
 * Created by xce35l7 on 07.11.2015.
 */
public class ZipCode {
    public static final String FORMAT = "00000";
    private int zipInt;

    public ZipCode(int zip) throws ZipCodeInvalidException {
        if (!isZipValid(zip)){
            throw new ZipCodeInvalidException("Invalid zip code: " + Integer.toString(zip));
        }
        this.zipInt = zip;
    }

    public static boolean isZipValid(int zip) {
        return zip >= 0 && zip < 100000;
    }

    public int getZipInt() {
        return zipInt;
    }

    @Override
    public String toString() {
        return new DecimalFormat(ZipCode.FORMAT).format(zipInt);
    }
}
