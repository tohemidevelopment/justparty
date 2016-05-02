package de.tohemi.justparty.test.junit;

import de.tohemi.justparty.database.controller.DBLocationController;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.Address;
import de.tohemi.justparty.datamodel.Location;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.exceptions.ZipCodeInvalidException;
import de.tohemi.justparty.datamodel.wrapper.EMail;
import de.tohemi.justparty.datamodel.wrapper.ZipCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import static org.junit.Assert.*;

/**
 * Created by xce35l2 on 28.04.2016.
 */
public class DBLocationControllerTest {

    public DBLocationController conL;
    public Location location;
    public Address address;

    @Before
    public void setUp() throws Exception, ZipCodeInvalidException {
        address = new Address("Teststraße", "12", new ZipCode(12345), "Testort", "Testland");
        conL = new DBLocationController().getInstance();
        location = new Location("LocationName", address, false);
        conL.addLocation(location);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getInstance() throws Exception {
        Assert.notNull(DBLocationController.getInstance());
    }

    @Test
    public void getLocationByID() throws Exception, ZipCodeInvalidException {
        int id = conL.getLocationID(location);
        Assert.isInstanceOf(Location.class, conL.getLocationByID(id));
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

    @Test
    public void updateLocation() throws Exception {
        Assert.isTrue(conL.updateLocation(location));
    }

    @Test
    public void getLocationID() throws Exception {
        Assert.notNull(conL.getLocationID(location));
    }

}