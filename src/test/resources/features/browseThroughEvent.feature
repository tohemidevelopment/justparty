Feature: Browse Through Events
    Scenario: Show View
        Given I am logged in with user "cucumber-user@test.de" and pw "test"
        When I click on "navbar-manageEvents"
        Then Now I see "manageEvents"


