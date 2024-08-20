Feature: Browse and Search for Dessert Recipes

  As a user
  I want to browse, search, and filter dessert recipes
  So that I can find desserts that meet my dietary needs or preferences
  
  
   Scenario: Search dessert recipes by name
    Given the user is logged in    
    And I am on the user dashboard 
    When I search for dessert recipes by name "Chocolate Cake"
    Then i should see the the dessert recipe and component

    Scenario: User make some Purchase
    Given the user is logged in 
    When i chose purchase optione then enter the product name "milk" and quntity "2" 
    Then user returns to dash
    
   Scenario: Filter dessert recipes by specific ingredient
    Given the user is logged in   
    When I filter dessert recipes by the ingredient "milk"
    Then I should see a list of dessert recipes that contain the ingredient I searched for