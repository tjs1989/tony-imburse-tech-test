Feature: Creating Orders
  Tests to confirm that we can create orders

  Scenario: Creating an order without instructions
    Given I have an order reference of 25 characters
    When I create an order without instructions
    Then I receive the order creation code of 201

  Scenario: Creating an order which was already created
    Given I have an order reference of 35 characters
    When I create an order without instructions
    And I create an order without instructions using the same reference
    Then I receive the order creation code of 400
