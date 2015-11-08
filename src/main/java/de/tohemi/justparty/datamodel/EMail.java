package main.java.de.tohemi.justparty.datamodel;

/**
 * Created by xce35l7 on 07.11.2015.
 */
public class EMail {
    String email;

    public EMail(String email) {
        this.email = email;
        //TODO: validate email address
        /*
        Sollten wir da nicht einfach einen Checker anwenden? Weil ich wüsste nicht wozu eine eigene Klasse nötig ware..
         */
    }

    @Override
    public String toString() {
        return email;
    }
}