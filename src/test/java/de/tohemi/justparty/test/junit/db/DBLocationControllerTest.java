package de.tohemi.justparty.test.junit.db;

import de.tohemi.justparty.database.controller.DBLocationController;
import de.tohemi.justparty.datamodel.address.Address;
import de.tohemi.justparty.datamodel.address.ConcreteAddress;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.junit.*;
import org.springframework.util.Assert;

/**
 * Created by xce35l2 on 28.04.2016.
 */
public class DBLocationControllerTest {

    public static DBLocationController conL;
    public static Location location;
    public static Address address;

    @BeforeClass
    public static void setUp() throws Exception {
        conL = new DBLocationController().getInstance();
        address = new ConcreteAddress("Teststraße", "12", new ZipCode(12345), "Testort", "Testland");
        location = new Location("LocationName", address, false);
        conL.addLocation(location);
        int id = conL.getLocationID(location);
        address.setID(id);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        conL.deleteLocation(location);
    }

    @Test
    public void getInstance() throws Exception {
        Assert.notNull(DBLocationController.getInstance());
    }

    @Test
    public void getLocationByID() throws Exception {
        Assert.isInstanceOf(Location.class, conL.getLocationByID(address.getID()));
    }

    @Test
    public void getLocationID() throws Exception {
        Assert.notNull(conL.getLocationID(location));
    }

    @Test
    public void getStreet() throws Exception  {
        Assert.isTrue(location.getAddress().getStreet().equals(conL.getStreet(address.getID())));
    }

    @Test
    public void setStreet() throws Exception  {
        conL.setStreet("Teststraß1", address.getID());
        location.getAddress().setStreet("Teststraß1");
        Assert.isTrue(location.getAddress().getStreet().equals(conL.getStreet(address.getID())));
    }

    @Test
    public void getHouseNumber() throws Exception  {
        Assert.isTrue(location.getAddress().getHouseNumber().equals(conL.getHouseNumber(address.getID())));
    }

    @Test
    public void setHouseNumber() throws Exception  {
        conL.setHouseNumber("23", address.getID());
        location.getAddress().setHouseNumber("23");
        Assert.isTrue(location.getAddress().getHouseNumber().equals(conL.getHouseNumber(address.getID())));
    }

    @Test
    public void getZipCode() throws Exception  {
        Assert.isTrue(location.getAddress().getZipCode().toString().equals(conL.getZipCode(address.getID()).toString()));
    }

    @Test
    public void setZipCode() throws Exception  {
        conL.setZipCode(new ZipCode(12346), address.getID());
        location.getAddress().setZipCode(new ZipCode(12346));
        Assert.isTrue(location.getAddress().getZipCode().toString().equals(conL.getZipCode(address.getID()).toString()));
    }

    @Test
    public void getCity() throws Exception  {
        Assert.isTrue(location.getAddress().getCity().equals(conL.getCity(address.getID())));
    }

    @Test
    public void setCity() throws Exception  {
        conL.setCity("Teststraß1", address.getID());
        location.getAddress().setCity("Teststraß1");
        Assert.isTrue(location.getAddress().getCity().equals(conL.getCity(address.getID())));
    }

    @Test
    public void getCountry() throws Exception  {
        Assert.isTrue(location.getAddress().getCountry().equals(conL.getCountry(address.getID())));
    }

    @Test
    public void setCountry() throws Exception  {
        conL.setCountry("Teststraß1", address.getID());
        location.getAddress().setCountry("Teststraß1");
        Assert.isTrue(location.getAddress().getCountry().equals(conL.getCountry(address.getID())));
    }

}