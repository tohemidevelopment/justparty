Feature: Register
  Scenario: incorrect email
    Given I see "register"
    And email "hans", pw1 "wurst", pw2 "wurst", terms "true"
    When I submit the "register" form
    Then Now I see "register"
    And "email" error is shown