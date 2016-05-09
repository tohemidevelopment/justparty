package de.tohemi.justparty.datamodel.address;

import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.jboss.netty.channel.AdaptiveReceiveBufferSizePredictor;

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

    public String getCity() {
        return city;
    }

    public void setCity(String location) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setID(int id){
        this.id = id;
    }
    public int getID() {
        return id;
    }
}
