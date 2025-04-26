Feature: Register to ParaBank website

  As a new user
  I want to register to the application
  So that I can login and access my account dashboard

  Background:
    Given the user is on the Login page

  Scenario Outline: TC-102 Register a new user
    When click on Register link
    And enter user <name> data and click Register
    Then account "created successfully" message should be displayed
    When click on open new account link
    Then "Account Opened" message should be displayed
    And clicks the logout
    Examples:
      | name      |
      | TestUser3 |