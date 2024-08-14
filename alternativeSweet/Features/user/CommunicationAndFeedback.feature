Feature: Communication and Feedback

  As a beneficiary user,
  I want to directly communicate with store owners and suppliers for inquiries or assistance,
  and provide feedback on purchased products and shared recipes,
  So that I can ensure a satisfying experience and help improve the offerings on the platform.

  Scenario: Direct Communication with Store Owners and Suppliers
    Given I am logged in as user
    And I am on the user dashboard
    When I choose to send a message to "owner" and enter the message "string"
    Then I should see a success message "message sent successfully."
    And return back to the user dashboard



  Scenario: Provide Feedback on Purchased Products
    Given I am logged in as user
    And I have received a purchased dessert
    When I navigate to the My Orders page
    And I select the dessert to provide feedback on and enter feedback message "that is a good dessert"
    Then I should see a success message "feedback send  successfully."   
    And return back to the user dashboard

#  Scenario: Provide Feedback on Shared Recipes
#    Given I am logged in as user
#    And I have viewed a recipe shared by another user
#    When I navigate to the "Shared Recipes" page
#    And I select a recipe to provide feedback on
#    And I rate the recipe and leave a comment
#    Then my feedback should be posted successfully
#    And the user who shared the recipe should be notified of my feedback
