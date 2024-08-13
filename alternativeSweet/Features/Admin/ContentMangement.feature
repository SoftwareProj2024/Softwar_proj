Feature: Content Management
  As an admin
  I want to manage the content shared on the system
  So that I can maintain quality and relevance

 
  Scenario: View all recipes
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "3" from the dashboard options
    And I am on the content management page
    When I select View Recipe then I should see the all recipes in the list
    Then return back to the content management page

#  Scenario: Add a new post
#    Given I am logged in as an admin
#    And I am on the content management page
#    When I select "Add Post"
#    And I fill in the post name "first post" and contents "hello welcome to our sweets" 
#    Then i submit
#    And I should see the new post in the list

#  Scenario: Edit an existing recipe
#    Given I am logged in as an admin
#    And I am on the content management page
#    When I select a recipe
#    And I selcet "Edit"
#    And I update the recipe content by its name name "cookies"
#    Then i submit
#    And I should see the updated recipe in the list

#  Scenario: Edit an existing post
#    Given I am logged in as an admin
#    And I am on the content management page
#    When I select a post
#    And I select "Edit"
#    And I update the post content by its name "first post" and contents 
#    Then i submit
#    And I should see the updated post in the list

  Scenario: Delete a recipe
     Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "3" from the dashboard options
    And I am on the content management page
    When  I select Delete  and enter recipe name "cookies"
    Then I should see a success message "Recipe deleted successfully."
    And I submit and should see the all recipes in the list after deleted
    And return back to the content management page
    

#  Scenario: Delete a post
#    Given I am logged in as an admin
#    And I am on the content management page
#    When I select a post name "first post"
#    And I select "Delete"
#    Then the post should be deleted
#    And I should not see the post in the list

  Scenario: response to  users feedback
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "3" from the dashboard options
    And I am on the content management page
    When I select Respond feedback and enter the feedback id "1" and write response message "thank you bro"
    And I should see a success message "response send  successfully."   
    And return back to the content management page

  Scenario: view user feedback
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "3" from the dashboard options
    And I am on the content management page
    When I select View feedback
    And I should see all feedbacks 
    And return back to the content management page
    
    
     Scenario: delete user feedback
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "3" from the dashboard options
    And I am on the content management page
    When I select Delete feedback and enter id feedback "1"
    And I should see a success message "feedback deleted successfully."
    And I should see all feedbacks 
    And return back to the content management page

