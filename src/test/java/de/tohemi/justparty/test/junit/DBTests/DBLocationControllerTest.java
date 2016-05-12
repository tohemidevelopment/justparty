package de.tohemi.justparty.test.junit.DBTests;

import de.tohemi.justparty.database.controller.DBLocationController;
import de.tohemi.justparty.datamodel.address.Address;
import de.tohemi.justparty.datamodel.address.ConcreteAddress;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * Created by xce35l2 on 28.04.2016.
 */
public class DBLocationControllerTest {

    public DBLocationController conL;
    public Location location;
    public Address address;

    @Before
    public void setUp() throws Exception, ZipCodeInvalidException {
        address = new ConcreteAddress("Teststraße", "12", new ZipCode(12345), "Testort", "Testland");
        conL = new DBLocationController().getInstance();
        location = new Location("LocationName", address, false);
        conL.addLocation(location);
        int id = conL.getLocationID(location);
        address.setID(id);
    }

    @After
    public void tearDown() throws Exception {
        conL.setZipCode(new ZipCode(12345), address.getID());
        conL.setStreet("Teststraße", address.getID());
        conL.setHouseNumber("12", address.getID());
        conL.setCity("Testort", address.getID());
        conL.setCountry("Testland", address.getID());
        conL.deleteLocation(location);
    }

    @Test
    public void getInstance() throws Exception {
        Assert.notNull(DBLocationController.getInstance());
    }

    @Test
    public void getLocationByID() throws Exception, ZipCodeInvalidException {
        Assert.isInstanceOf(Location.class, conL.getLocationByID(address.getID()));
    }

    @Test
    public void addLocation() throws Exception {
        conL.deleteLocation(location);
        Assert.isTrue(conL.addLocation(location));
    }

    @Test
    public void deleteLocation() throws Exception {
        Assert.isTrue(conL.deleteLocation(location));
    }
    //TODO: Unknown error with street, fix asap
    /*
    @Test
    public void updateLocation() throws Exception {
        Assert.isTrue(conL.updateLocation(location));
    }*/

    @Test
    public void getLocationID() throws Exception {
        Assert.notNull(conL.getLocationID(location));
    }

    @Test
    public void getStreet() throws Exception  {
        Assert.isTrue("Teststraße".equals(conL.getStreet(address.getID())));
    }

    @Test
    public void setStreet() throws Exception  {
        conL.setStreet("Teststraß1", address.getID());
        Assert.isTrue("Teststraß1".equals(conL.getStreet(address.getID())));

    }

    @Test
    public void getHouseNumber() throws Exception  {
        Assert.isTrue("12".equals(conL.getHouseNumber(address.getID())));

    }

    @Test
    public void setHouseNumber() throws Exception  {
        conL.setHouseNumber("23", address.getID());
        Assert.isTrue("23".equals(conL.getHouseNumber(address.getID())));

    }

    @Test
    public void getZipCode() throws Exception  {
        Assert.isTrue("12345".equals(conL.getZipCode(address.getID()).toString()));

    }

    @Test
    public void setZipCode() throws Exception  {
        conL.setZipCode(new ZipCode(12346), address.getID());
        Assert.isTrue("12346".equals(conL.getZipCode(address.getID()).toString()));

    }

    @Test
    public void getCity() throws Exception  {
        Assert.isTrue("Testort".equals(conL.getCity(address.getID())));


    }

    @Test
    public void setCity() throws Exception  {
        conL.setCity("Teststraß1", address.getID());
        Assert.isTrue("Teststraß1".equals(conL.getCity(address.getID())));

    }

    @Test
    public void getCountry() throws Exception  {
        Assert.isTrue("Testland".equals(conL.getCountry(address.getID())));

    }

    @Test
    public void setCountry() throws Exception  {
        conL.setCountry("Teststraß1", address.getID());
        Assert.isTrue("Teststraß1".equals(conL.getCountry(address.getID())));

    }

}