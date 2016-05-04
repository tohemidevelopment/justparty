Feature: Login
  Scenario: fail
    Given I see "login"
    And email "cucumber-user@test.de", pw "wrong"
    When I submit the "login" form
    Then Now I see "login"

  Scenario: success
    Given I see "login"
    And email "cucumber-user@test.de", pw "test"
    When I submit the "login" form
    Then Now I see "manageEvent"

