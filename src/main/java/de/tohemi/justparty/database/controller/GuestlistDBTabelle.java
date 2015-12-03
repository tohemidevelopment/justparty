package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.datamodel.Accepted;

/**
 * Created by Micha Piertzik on 03.12.2015.
 */
public class GuestlistDBTabelle {

    public static final String TABLE_NAME = "guestlist";


    public static Accepted getAcceptedObjectForStatus(int status) {
        switch (status) {
            case 1:
                return Accepted.ACCEPTED;
            case 2:
                return Accepted.DECLINED;
            case 3:
                return Accepted.NOTSURE;
        }
        return null;
    }

    public static int getIntStatusForAcceptedObject(Accepted status) {
        if (status == null) {
            return 0;
        }
        switch (status) {
            case ACCEPTED:
                return 1;
            case DECLINED:
                return 2;
            case NOTSURE:
                return 3;
        }
        return 0;
    }
}
