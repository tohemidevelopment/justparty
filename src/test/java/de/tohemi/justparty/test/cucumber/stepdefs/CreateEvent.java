package de.tohemi.justparty.test.cucumber.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.tohemi.justparty.businesslogic.EventsHandlerImpl;
import de.tohemi.justparty.database.controller.DBController;
import de.tohemi.justparty.datamodel.Event;
import de.tohemi.justparty.datamodel.User;
import de.tohemi.justparty.datamodel.UserEventRelation;
import de.tohemi.justparty.test.cucumber.BrowserHolder;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * Created by Heiko on 08.12.2015.
 */
public class CreateEvent {
    @Autowired
    private BrowserHolder browser;

    @And("^eventname \"([^\"]*)\"$")
    public void eventname(String eventname) throws Throwable {
        browser.getDriver().findElement(By.name("eventname")).sendKeys(eventname);
    }

    @And("^\"([^\"]*)\" is created$")
    public void is_created(String eventname) throws Throwable {

    }

    @And("^\"([^\"]*)\" is created for user \"([^\"]*)\"$")
    public void is_created_for_user(String eventname, String email) throws Throwable {
        List<UserEventRelation> eventlist=EventsHandlerImpl.getCurrentEvents(email);
        for (UserEventRelation rel:eventlist){
            if(rel.getName().equals(eventname)) {
                return;
            }
        }
        throw new Exception();
    }

    @Then("^\"([^\"]*)\" is deleted for user \"([^\"]*)\"$")
    public void is_deleted_for_user(String eventname, String email) throws Throwable {
        List<UserEventRelation> eventlist=EventsHandlerImpl.getCurrentEvents(email);
        for (UserEventRelation rel:eventlist){
            if(rel.getName().equals(eventname)) {
                throw new Exception();
            }
        }

    }


    @When("^I delete the event \"([^\"]*)\" for user \"([^\"]*)\"$")
    public void I_delete_the_event_for_user(String eventname, String email) throws Throwable {

            List<UserEventRelation> eventlist=EventsHandlerImpl.getCurrentEvents(email);
            for (UserEventRelation rel:eventlist){
                if(rel.getName().equals(eventname)) {
                    browser.getDriver().findElement(By.id("delete_"+rel.getEvent().getId())).click();
                    break;
                }
            }

    }
}
