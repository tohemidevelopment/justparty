package de.tohemi.justparty.datamodel;

/**
 * Created by Micha Piertzik on 01.12.2015.
 */
public enum Accepted {
    ACCEPTED(1), NOTSURE(2), DECLINED(3), HOST(0);

    private final int value;

    Accepted(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
