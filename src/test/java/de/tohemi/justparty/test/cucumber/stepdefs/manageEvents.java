package de.tohemi.justparty.test.cucumber.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by Heiko on 21.12.2015.
 */
public class ManageEvents {
    @When("^I click on \"([^\"]*)\"$")
    public void I_click_on(String arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @When("^I chose \"([^\"]*)\" for Event \"([^\"]*)\"$")
    public void I_chose_for_Event(String arg1, String arg2) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^Invitation for \"([^\"]*)\" and User \"([^\"]*)\" is \"([^\"]*)\"$")
    public void Invitation_for_and_User_is(String arg1, String arg2, String arg3) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @When("^I click on \"([^\"]*)\" for Event \"([^\"]*)\"$")
    public void I_click_on_for_Event(String arg1, String arg2) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }
}
