package de.tohemi.justparty.test.cucumber.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import de.tohemi.justparty.test.cucumber.BrowserHolder;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Heiko on 08.12.2015.
 */
public class Login {

    @Autowired
    private BrowserHolder browser;

    @And("^email \"([^\"]*)\", pw \"([^\"]*)\"$")
    public void email_pw(String email, String pw) throws Throwable {
        browser.getDriver().findElement(By.name("username")).sendKeys(email);
        browser.getDriver().findElement(By.name("password")).sendKeys(pw);
    }
}
