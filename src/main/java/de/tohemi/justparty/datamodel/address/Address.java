package de.tohemi.justparty.datamodel.address;

import de.tohemi.justparty.datamodel.wrapper.ZipCode;

/**
 * Created by Micha Piertzik on 09.05.2016.
 */
public interface Address {

    String getStreet();

    void setStreet(String street);

    String getHouseNumber();

    void setHouseNumber(String houseNumber);

    ZipCode getZipCode();

    void setZipCode(ZipCode zipCode);

    String getCity();

    void setCity(String location);

    String getCountry();

    void setCountry(String country);

    void setID(int id);

    int getID();
}
