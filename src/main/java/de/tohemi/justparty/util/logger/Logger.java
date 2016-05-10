package de.tohemi.justparty.util.logger;

import de.tohemi.justparty.datamodel.exceptions.InvalidEmailException;

/**
 * Created by Micha Piertzik on 10.05.2016.
 */
public interface Logger {

    void logInfo(String msg);

    void logWarning(String msg);

    void logError(String msg);

    void logException(Throwable e, String msg);

    void logException(Throwable e);
}
