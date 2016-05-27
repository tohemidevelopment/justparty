package de.tohemi.justparty.util;

import de.tohemi.justparty.util.logger.ConsoleLogger;
import de.tohemi.justparty.util.logger.Logger;

/**
 * Created by Micha Piertzik on 10.05.2016.
 */
public class SystemProperties {

    private SystemProperties(){}

    public static Logger getLogger() {
        return ConsoleLogger.getInstance();
    }
}
