Feature: Register
  Scenario: invalid email
    Given I see "register"
    And email "cucumber", pw1 "wurst", pw2 "wurst", terms "true"
    When I submit the "register" form
    Then Now I see "register"

  Scenario: invalid password
    Given I see "register"
    And email "cucumber@test.de", pw1 "wu", pw2 "wu", terms "true"
    When I submit the "register" form
    Then Now I see "register"

  Scenario: invalid password repetition
    Given I see "register"
    And email "cucumber@test.de", pw1 "wurst", pw2 "wurstX", terms "true"
    When I submit the "register" form
    Then Now I see "register"

  Scenario: terms not accepted
    Given I see "register"
    And email "cucumber@test.de", pw1 "wurst", pw2 "wurst", terms "false"
    When I submit the "register" form
    Then Now I see "register"

  Scenario: correct register
    Given I see "register"
    And email "cucumber@test.de", pw1 "wurst", pw2 "wurst", terms "true"
    When I submit the "register" form
    Then Now I see "login?alert_success=alert.success.registration"

    