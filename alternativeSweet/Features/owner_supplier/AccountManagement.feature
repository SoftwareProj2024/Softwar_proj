Feature: Account Management
  As a store owner or raw material supplier
  I want to manage my account details
  So that I can keep my business information up to date

  Scenario: View account details
    Given I am logged in as a store owner or raw material supplier
    When I navigate to the account management page
    Then I should see my account details

  Scenario: Update business information
    Given I am logged in as a store owner or raw material supplier
    When I navigate to the account management page And I select "Edit Business Information" and I enter username "ahmad" and pass "156"  update the business information
    Then i submit
    And I should see the updated information on the account management page
