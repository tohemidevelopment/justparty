package de.tohemi.justparty.datamodel.user;

import de.tohemi.justparty.datamodel.address.Address;
import de.tohemi.justparty.datamodel.wrapper.EMail;

import java.sql.Date;

/**
 * Created by Micha Piertzik on 09.05.2016.
 */
public interface User {

    String getEmail();

    /**
     *
     * @deprecated Use setEmail(String email) instead
     */
    @Deprecated
    void setEmail(EMail email);

    void setEmail(String email);

    String getLastName();

    void setLastName(String lastName);

    String getFirstName();

    void setFirstName(String firstName);

    Address getAddress();

    void setAddress(Address address);

    Date getBirthday();

    void setBirthday(Date birthday);

}
