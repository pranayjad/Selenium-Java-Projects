Feature: Purchase product from Ecommerce website

  Background:
    Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test for Submitting order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on Confirmation page
    Examples:
      | name          | password | productName|
      | ppj@gmail.com | zaq1@WSX | ZARA COAT 3|