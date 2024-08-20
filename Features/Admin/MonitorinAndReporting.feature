Feature: Monitoring and Reporting
  As an admin
  I want to monitor profits and generate financial reports
  So that I can analyze business performance

  Scenario: View profit reports
    Given I am logged in as an admin
    When I navigate to the reporting page
    And I select "Profit Reports"
    Then I should see a report of profits

  Scenario: Generate a financial report
    Given I am logged in as an admin
    When I navigate to the reporting page
    And I select "Generate Financial Report"
    Then I should see a financial report for the selected 

  Scenario: Identify best-selling products in each store
    Given I am logged in as an admin
    When I navigate to the reporting page
    And I select "Best-Selling Products"
    Then I should see a list of best-selling products in each store

  Scenario: Gather and display statistics on registered users by City
    Given I am logged in as an admin
    When I navigate to the reporting page
    And I select "User Statistics by City"
    Then I should see statistics on registered users by city
    And I should see data for cities like Nablus and Jenin
