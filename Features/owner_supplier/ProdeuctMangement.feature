Feature: Product Management
  As a store owner or raw material supplier
  I want to manage my products
  So that I can keep my inventory up to date and track sales performance

  Scenario: Add a new product
    Given I am logged in as a store owner or material supplier
    When I navigate to the product management dashbored
    And select add "Add Product" I fill in the product name "molto" and price "200" and exp date "5/8/2025" 
    Then i submit the details
    And I should see the new product in my product list

  Scenario: Update an existing product
    Given I am logged in as a store owner or material supplier
    When I navigate to the product management dashbored
    And I select update "Edit" and choose a product name "molto" and update price "350" and exp date "5/8/2025"
    Then i submit
    And update success message pops and user return to management dashbored

  Scenario: Remove a product
    Given I am logged in as a store owner or material supplier
    When I navigate to the product management dashbored
    And I select delete "Delete" and choose product name "molto"
    Then i submit
    And delete message pops and user return to management dashbored

  Scenario: Monitor sales and profits
    Given I am logged in as a store owner or material supplier
    When I navigate to the sales and profits dashboared
    Then I should see a report of my sales and profits

  Scenario: Identify best-selling products
    Given I am logged in as a store owner or material supplier
    When I navigate to the sales and profits dashboared
    Then I should see a list of my best-selling products

  Scenario: Implement dynamic discount features
    Given I am logged in as a store owner or material supplier
    When I navigate to the discount management page and I select  "Add Discount"
    And I fill in the discount current price "200"
    Then i submit
    And message pops original price and discount price 
