package main.java.de.tohemi.justparty.datamodel;

import main.java.de.tohemi.justparty.datamodel.ZipCode;

/**
 * Created by Heiko on 04.11.2015.
 */
public class Address {

    private String street;
    private String houseNumber;
    private ZipCode zipCode;
    private String location;
    private String country;

    public Address(String street, String houseNumber, ZipCode zipCode, String location, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.location = location;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
