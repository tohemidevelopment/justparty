Feature: Event
    Scenario: Create
        Given I am logged in with user "cucumber-user@test.de" and pw "test"
        And I see "createEvent"
        And eventname "CucumberTestEvent"
        When I submit the "createEvent" form
        Then Now I see "manageEvent"
        And "CucumberTestEvent" is created for user "cucumber-user@test.de"




















#    Scenario: Delete
#        Given I am logged in with user "cucumber-user@test.de" and pw "test"
#        And I see "manageEvent"
#        When I delete the event "CucumberTestEvent" for user "cucumber-user@test.de"
#        Then "CucumberTestEvent" is deleted for user "cucumber-user@test.de"