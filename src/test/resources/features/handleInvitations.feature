Feature: Handle Invitations
    Scenario: Accept
        Given I am logged in with user "cucumber-user@test.de" and pw "test"
        And I see "manageEvents"
        When I chose "Accept" for Event "TestInvitation-Event"
        Then Invitation for "TestInvitation-Event" and User "cucumber-user@test.de" is "accepted"

    Scenario: Decline
        Given I am logged in with user "cucumber-user@test.de" and pw "test"
        And I see "manageEvents"
        When I chose "Decline" for Event "TestInvitation-Event"
        Then Invitation for "TestInvitation-Event" and User "cucumber-user@test.de" is "declined"

    Scenario: Unsure
        Given I am logged in with user "cucumber-user@test.de" and pw "test"
        And I see "manageEvents"
        When I chose "insure" for Event "TestInvitation-Event"
        Then Invitation for "TestInvitation-Event" and User "cucumber-user@test.de" is "unsure"

