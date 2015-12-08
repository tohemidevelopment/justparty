Feature: Register
  Scenario: incorrect email
    Given I see "register"
    And email "cucumber", pw1 "wurst", pw2 "wurst", terms "true"
    When I submit the "register" form
    Then Now I see "register"
    And "email" error is shown

  Scenario: correct register
    Given I see "register"
    And email "cucumber@test.de", pw1 "wurst", pw2 "wurst", terms "true"
    When I submit the "register" form
    Then Now I see "login?alert_success=alert.success.registration"