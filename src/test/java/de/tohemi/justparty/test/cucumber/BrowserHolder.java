package de.tohemi.justparty.test.cucumber;

import org.openqa.selenium.WebDriver;

/**
 * Created by Heiko on 07.12.2015.
 */
public class BrowserHolder {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}