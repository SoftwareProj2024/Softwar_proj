Feature: Order Management
  As a store owner or raw material supplier
  I want to process and track orders
  So that I can manage my sales efficiently

  Scenario: View orders
    Given I am logged in as a store owner 
    When I navigate to the order management page
    Then I should see a list of orders

  Scenario: Process an order
    Given I am logged in as a store owner
    And I am on the order management page
    When I select an order number "1" and I choose "Process"
    Then the order status becomes processed "processed"
    And I should see the updated status in the order list and return to management page

  Scenario: Track order status
    Given I am logged in as a store owner
    When I navigate to the order management page
    And I select an order by its number "1"
    Then I should see the current status of the order and return back to mangement page 
