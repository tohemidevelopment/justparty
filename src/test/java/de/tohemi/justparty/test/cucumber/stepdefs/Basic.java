package de.tohemi.justparty.test.cucumber.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
        browser.getDriver().get("http://localhost:8080/"+view);
    }

    @When("^I submit the \"([^\"]*)\" form$")
     public void I_submit_the_form(String formName) throws Throwable {
        WebElement loginForm = browser.getDriver().findElement(By.id(formName+"_form"));
        loginForm.submit();
        new WebDriverWait(browser.getDriver(), BaseIntegration.TIMEOUT).until(ExpectedConditions.stalenessOf(loginForm));
    }

    @Then("^Now I see \"([^\"]*)\"$")
    public void Now_I_see(String view) throws Throwable {

        if(!browser.getDriver().getCurrentUrl().equals("http://localhost:8080/"+view))
            throw new Exception();
    }
    @After
    public void tearDown() {
        browser.getDriver().quit();
    }


}
