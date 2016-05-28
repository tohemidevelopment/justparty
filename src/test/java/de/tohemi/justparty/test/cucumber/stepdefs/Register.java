package de.tohemi.justparty.test.cucumber.stepdefs;

import cucumber.api.java.en.And;
import de.tohemi.justparty.test.cucumber.BrowserHolder;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Heiko on 07.12.2015.
 */
public class Register {
    @Autowired
    private BrowserHolder browser;

    @And("^email \"([^\"]*)\", pw1 \"([^\"]*)\", pw2 \"([^\"]*)\", terms \"([^\"]*)\"$")
    public void email_pw1_pw2_terms(String email, String pw1, String pw2, String accept) throws Throwable {
        browser.getDriver().findElement(By.name("email")).sendKeys(email);
        browser.getDriver().findElement(By.name("password")).sendKeys(pw1);
        browser.getDriver().findElement(By.name("password_repeat")).sendKeys(pw2);
        if ("true".equals(accept)) {
            browser.getDriver().findElement(By.name("terms")).click();
        }
    }

    @And("^\"([^\"]*)\" error is shown$")
    public void error_is_shown(String errorType) throws Throwable {
        browser.getDriver().findElement(By.name("error_" + errorType));
    }


}
