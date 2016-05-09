package de.tohemi.justparty.datamodel.user;

import de.tohemi.justparty.datamodel.wrapper.EMail;

/**
 * Created by Micha Piertzik on 09.05.2016.
 */
public final class UserFactory {

    public static User create(final EMail email) {
        return UserFactory.create(email, false, false);
    }

    public static User create(final String email) {
        return UserFactory.create(email, false, false);
    }

    public static User create(final EMail email, final boolean dbAccess, final boolean unregistered) {
        if (!unregistered) {
            if (!dbAccess) {
                return new ConcreteUser(email);
            }
            if (dbAccess) {
                return new DBAccessUser(email);
            }
        }
        if (!dbAccess) {
            return new UnregisteredUser(email);
        }
        return null;
    }

    public static User create(final String email, final boolean dbAccess, final boolean unregistered) {
        if (!unregistered) {
            if (!dbAccess) {
                return new ConcreteUser(email);
            }
            if (dbAccess) {
                return new DBAccessUser(email);
            }
        }
        if (!dbAccess) {
            return new UnregisteredUser(email);
        }
        return null;
    }

    public static User create(final EMail email, final boolean dbAccess) {
        if (dbAccess) {
            return new DBAccessUser(email);
        }
        return new ConcreteUser(email);
    }

    public static User create(final String email, final boolean dbAccess) {
        if (dbAccess) {
            return new DBAccessUser(email);
        }
        return new ConcreteUser(email);
    }
}
