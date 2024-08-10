#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: User Account
  Scenario: User signs up for a new account
    Given the user is not logged in 
    When the user enters username  "mahmoud" password "123" and role "Material_supplier"
    Then the user account should be created successfully
    
    
    
  Scenario: User signs in with valid details 
    Given the user is not logged in 
    When the user enters username "musa" and password "123" role "user" 
    Then user enters to the dash
    
  Scenario: User updates his password 
    Given the user is logged in 
    When the user when the user chooses manage account and then update account and the user chooses update "password" and enters a new password "123456" 
    Then update successful 
    And user returns to dash
  
  
  Scenario: User updates his username 
    Given the user is logged in 
    When the user when the user chooses manage account and then update account And the user chooses update "username" and enters a new username "khader"  
    Then update successful 
    And user returns to dash
  
  
  
  
  
  