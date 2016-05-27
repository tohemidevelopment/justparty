package de.tohemi.justparty.database.datainterfaces;

import de.tohemi.justparty.database.controller.DBLocationController;
import de.tohemi.justparty.datamodel.address.Address;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;

/**
 * Created by Heiko on 26.12.2015.
 */
public class DBAddress implements Address{
    private int id;

    public DBAddress(int id) {
        this.id = id;
    }

    @Override
    public String getStreet() { return DBLocationController.getInstance().getStreet(id);}

    @Override
    public void setStreet(String street) { DBLocationController.getInstance().setStreet(street, id); }

    @Override
    public String getHouseNumber() {
        return DBLocationController.getInstance().getHouseNumber(id);
    }

    @Override
    public void setHouseNumber(String houseNumber) { DBLocationController.getInstance().setHouseNumber(houseNumber,id); }

    @Override
    public ZipCode getZipCode() {
        return DBLocationController.getInstance().getZipCode(id);
    }

    @Override
    public void setZipCode(ZipCode zipCode) { DBLocationController.getInstance().setZipCode(zipCode, id); }

    @Override
    public String getCity() {
        return DBLocationController.getInstance().getCity(id);
    }

    @Override
    public void setCity(String city) { DBLocationController.getInstance().setCity(city, id); }

    @Override
    public String getCountry() { return DBLocationController.getInstance().getCountry(id); }

    @Override
    public void setCountry(String country) { DBLocationController.getInstance().setCountry(country, id); }

    @Override
    public void setID(int id) { this.id = id;}

    @Override
    public int getID() {
        return id;
    }
}
