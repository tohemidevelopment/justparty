package main.java.de.tohemi.justparty.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Micha Piertzik on 11.11.2015.
 */
@Configuration
@ImportResource({ "classpath:webSecurityConfig.xml" })
public class SecSecurityConfig {
    public SecSecurityConfig() {
        super();
    }
}