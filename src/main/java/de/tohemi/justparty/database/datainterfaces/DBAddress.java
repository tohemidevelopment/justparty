package de.tohemi.justparty.database.datainterfaces;

import de.tohemi.justparty.datamodel.address.Address;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;

/**
 * Created by Heiko on 26.12.2015.
 * TODO: implement Methods
 */
public class DBAddress implements Address{
    private int id;

    public DBAddress(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return null;
    }

    public void setStreet(String street) {

    }

    public String getHouseNumber() {
        return null;
    }

    public void setHouseNumber(String houseNumber) {

    }

    public ZipCode getZipCode() {
        return null;
    }

    public void setZipCode(ZipCode zipCode) {

    }

    public String getCity() {
        return null;
    }

    public void setCity(String location) {

    }

    public String getCountry() {
        return null;
    }

    public void setCountry(String country) {

    }

    public void setID(int id) {

    }

    public int getID() {
        return id;
    }
}
