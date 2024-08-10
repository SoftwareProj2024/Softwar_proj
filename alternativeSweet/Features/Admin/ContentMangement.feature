Feature: Content Management
  As an admin
  I want to manage the content shared on the system
  So that I can maintain quality and relevance

  Scenario: View all shared content
    Given I am logged in as an admin
    When I navigate to the content management page
    Then I should see a list of all shared content including recipes and posts

  Scenario: Add a new recipe
    Given I am logged in as an admin
    And I am on the content management page
    When I select "Add Recipe"   
    And I fill in the recipe name "cookies" and contents "biscuits and chocolate" 
    Then I submit details
    And I should see the new recipe in the list

  Scenario: Add a new post
    Given I am logged in as an admin
    And I am on the content management page
    When I select "Add Post"
    And I fill in the post name "first post" and contents "hello welcome to our sweets" 
    Then i submit
    And I should see the new post in the list

  Scenario: Edit an existing recipe
    Given I am logged in as an admin
    And I am on the content management page
    When I select a recipe
    And I selcet "Edit"
    And I update the recipe content by its name name "cookies"
    Then i submit
    And I should see the updated recipe in the list

  Scenario: Edit an existing post
    Given I am logged in as an admin
    And I am on the content management page
    When I select a post
    And I select "Edit"
    And I update the post content by its name "first post" and contents 
    Then i submit
    And I should see the updated post in the list

  Scenario: Delete a recipe
    Given I am logged in as an admin
    And I am on the content management page
    When I select a recipe name "cookies"
    And I select "Delete"
    Then the recipe should be deleted
    And I should not see the recipe in the list

  Scenario: Delete a post
    Given I am logged in as an admin
    And I am on the content management page
    When I select a post name "first post"
    And I select "Delete"
    Then the post should be deleted
    And I should not see the post in the list

  Scenario: Manage user feedback
    Given I am logged in as an admin
    When I navigate to the feedback management page
    Then I should see a list of user feedback
    When I select a feedback
    And I select "Respond"
    And I write a response
    Then the user should receive the response
    And I should see the response in the feedback list
