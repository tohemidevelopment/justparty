package de.tohemi.justparty.database.tables;

import de.tohemi.justparty.datamodel.Accepted;

/**
 * Created by Micha Piertzik on 03.12.2015.
 */
public class GuestlistDBTabelle {

    public static final String TABLE = "guestlist";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_EVENT = "event";
    public static final String COLUMN_GUEST = "guest";
    public static final int ACEPTED=1;
    public static final int DECLINED=2;
    public static final int NOTSURE=3;


    public static Accepted getAcceptedObjectForStatus(int status) {
        switch (status) {
            case ACEPTED:
                return Accepted.ACCEPTED;
            case DECLINED:
                return Accepted.DECLINED;
            case NOTSURE:
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
