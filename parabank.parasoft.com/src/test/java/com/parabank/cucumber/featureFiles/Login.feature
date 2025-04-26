Feature: Login to ParaBank website

  As a registered user
  I want to log in to the application
  So that I can access my account dashboard

  Background:
    Given the user is on the Login page

  Scenario Outline: TC-99 Successful login with valid credentials
    When The user enters a valid username <name> and password <password> clicks the Login
    Then a "Welcome" message should be displayed
    And clicks the logout
    Examples:
      | name      | password |
      | TestUser1 | test |

  Scenario Outline: TC-100 Login with invalid Username and valid Password
    When The user enters a invalid username <name> or password <password> clicks the Login
    Then a "An internal error has occurred" error message should be displayed
    Examples:
      | name      | password |
      | TestUser10 | test |

  Scenario Outline: TC-101 Login with valid Username and invalid Password
    When The user enters a invalid username <name> or password <password> clicks the Login
    Then a "An internal error has occurred" error message should be displayed
    Examples:
      | name      | password |
      | TestUser1 | test12 |
