package de.tohemi.justparty.test.cucumber.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.tohemi.justparty.database.controller.DBUserController;
import de.tohemi.justparty.datamodel.user.UserFactory;
import de.tohemi.justparty.test.cucumber.BaseIntegration;
import de.tohemi.justparty.test.cucumber.BrowserHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import static de.tohemi.justparty.test.cucumber.BaseIntegration.chromeDriver;

/**
 * Created by Heiko on 07.12.2015.
 */
public class Basic {
    @Autowired
    private BrowserHolder browser;

    @Before
    public void setUp() {
        browser.setDriver(chromeDriver());
    }

    @Given("^I see \"([^\"]*)\"$")
    public void I_see(String view) throws Throwable {
        browser.getDriver().get("http://localhost:8080/" + view);
    }

    @When("^I submit the \"([^\"]*)\" form$")
    public void I_submit_the_form(String formName) throws Throwable {
        WebElement loginForm = browser.getDriver().findElement(By.id(formName + "_form"));
        loginForm.submit();
        new WebDriverWait(browser.getDriver(), BaseIntegration.TIMEOUT).until(ExpectedConditions.stalenessOf(loginForm));
    }

    @Then("^Now I see \"([^\"]*)\"$")
    public void Now_I_see(String view) throws Throwable {
        System.out.println(view);
        System.out.println(browser.getDriver().getCurrentUrl());
        if (!browser.getDriver().getCurrentUrl().contains("http://localhost:8080/" + view))
            throw new Exception();
    }

    @After
    public void tearDown() {
        browser.getDriver().quit();
        DBUserController.getInstance().removeUser(UserFactory.create("cucumber@test.de"));
    }


    @Given("^I am logged in with user \"([^\"]*)\" and pw \"([^\"]*)\"$")
    public void I_am_logged_in_with_user_and_pw(String email, String password) throws Throwable {
        browser.getDriver().get("http://localhost:8080/login");
        browser.getDriver().findElement(By.name("username")).sendKeys(email);
        browser.getDriver().findElement(By.name("password")).sendKeys(password);
        WebElement loginForm = browser.getDriver().findElement(By.id("login_form"));
        loginForm.submit();
        new WebDriverWait(browser.getDriver(), BaseIntegration.TIMEOUT).until(ExpectedConditions.stalenessOf(loginForm));

    }
}
