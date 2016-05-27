package de.tohemi.justparty.datamodel.address;

import de.tohemi.justparty.datamodel.wrapper.ZipCode;

/**
 * Created by Heiko on 04.11.2015.
 */
public class ConcreteAddress implements Address{

    private String street;
    private String houseNumber;
    private ZipCode zipCode;
    private String city;
    private String country;
    private int id;

    public ConcreteAddress(String street, String houseNumber, ZipCode zipCode, String city, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }
    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String getHouseNumber() {
        return houseNumber;
    }

    @Override
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public ZipCode getZipCode() {
        return zipCode;
    }

    @Override
    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String location) {
        this.city = location;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public void setID(int id){
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }
}
