Feature: Creating Instructions
  Creating instructions after an order has been created

  Background: Create an order
    Given I have an order reference of 45 characters
    And I create an order with instructions for "Ireland" with a currency of "Euro"
    Then I receive the order creation code of 202

  Scenario: Successfully creating an instruction
    Given I create an Instruction for "Ireland" with a currency of "Euro"
    When I submit the instruction
    Then I receive an instruction response code of 201

  @negativeTests
  Scenario: Error when recreating the same instruction
    Given I create an Instruction for "Ireland" with a currency of "Euro"
    When I submit the instruction
    And I submit the instruction again
    Then I receive an instruction response code of 400
