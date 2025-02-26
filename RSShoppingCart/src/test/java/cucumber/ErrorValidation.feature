Feature: Check Login functionality of Ecommerce website

  @ErrorValidation
  Scenario Outline: Negative1 test for Login
    Given I landed on Ecommerce page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed
    Examples:
      | name         | password |
      | pj@gmail.com | zaq1@WSX |