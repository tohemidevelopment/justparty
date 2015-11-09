package main.java.de.tohemi.justparty.controller;

import main.java.de.tohemi.justparty.view_interface.GeneralStrings;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * Created by Heiko on 04.11.2015.
 */

public class Controller {
    protected void setglobalVariables(ModelMap model){
        model.addAttribute(GeneralStrings.getATT_WEBAPPTITLE(),GeneralStrings.getWebappTitle());
    }

}