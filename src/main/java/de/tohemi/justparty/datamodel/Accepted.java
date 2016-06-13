package de.tohemi.justparty.datamodel;

import de.tohemi.justparty.database.tables.GuestlistDBTabelle;

/**
 * Created by Micha Piertzik on 01.12.2015.
 */
public enum Accepted {
    ACCEPTED(GuestlistDBTabelle.ACCEPTED), NOTSURE(GuestlistDBTabelle.NOTSURE), DECLINED(GuestlistDBTabelle.DECLINED), HOST(0);

    private final int value;

    Accepted(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
