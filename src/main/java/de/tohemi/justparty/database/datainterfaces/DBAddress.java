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

    @Override
    public String getStreet() {
        return null;
    }

    @Override
    public void setStreet(String street) { }

    @Override
    public String getHouseNumber() {
        return null;
    }

    @Override
    public void setHouseNumber(String houseNumber) { }

    @Override
    public ZipCode getZipCode() {
        return null;
    }

    @Override
    public void setZipCode(ZipCode zipCode) { }

    @Override
    public String getCity() {
        return null;
    }

    @Override
    public void setCity(String location) { }

    @Override
    public String getCountry() {
        return null;
    }

    @Override
    public void setCountry(String country) { }

    @Override
    public void setID(int id) { }
    @Override
    public int getID() {
        return id;
    }
}
