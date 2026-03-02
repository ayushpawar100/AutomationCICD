@tag
Feature: Error Validation
I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Login Error validation
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password> 
    Then "Incorrect email or password." message is displayed

    Examples:
      | name                | password  |
      | kunalrana@gmail.com | Kunal@12  |
