package main.java.de.tohemi.justparty.datamodel;

/**
 * Created by Heiko on 08.11.2015.
 */


//As connection between Event and Address for further information
public class Location {
    public Location(String name, Address address, boolean publicLocation) {
        this.name = name;
        this.address = address;
        this.publicLocation = publicLocation;
    }

    private String name;
    private Address address;
    private boolean publicLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isPublicLocation() {
        return publicLocation;
    }

    public void setPublicLocation(boolean publicLocation) {
        this.publicLocation = publicLocation;
    }
}
