package de.tohemi.justparty.util.logger;

/**
 * Created by Micha Piertzik on 10.05.2016.
 */
public class ConsoleLogger implements Logger {

    private static ConsoleLogger instance;

    private ConsoleLogger() {
    }

    public static synchronized ConsoleLogger getInstance() {

        if (instance == null) {
            instance = new ConsoleLogger();
        }
        return instance;
    }

    @Override
    public void logInfo(String msg) {
        System.out.println(msg);
    }


    @Override
    public void logWarning(String msg) {
        System.out.println(msg);
    }

    @Override
    public void logError(String msg) {
        System.err.println(msg);
    }

    @Override
    public void logException(Throwable e, String msg) {
        System.err.println(msg);
        logException(e);
    }

    @Override
    public void logException(Throwable e) {
        System.err.println(e);
        System.err.println(stackTraceToString(e));
    }

    private String stackTraceToString(Throwable e) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append("\tat ");
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
