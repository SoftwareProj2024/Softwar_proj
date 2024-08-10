Feature: User Management
  As an admin
  I want to manage user accounts including users, store owners, and raw material suppliers
  So that I can control access to the system

  Scenario: View all user accounts
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "1" from the dashboard options
    And I am on the user management page
    When I view the list of users
    Then I should see all users in the list

  Scenario: Add a new user account
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "User Management" from the dashboard options
    And I am on the user management page
    When I choose to add a new user and I enter the username "ahmad" , password "123" , and role "user"
    And I submit the new user details
    Then I should see all users in the list
    And I should see a success message "User added successfully."

  Scenario: Delete a user account
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "User Management" from the dashboard options
    And I am on the user management page
    When I choose to delete the user with username "mohammad123"
    Then I should see all users in the list
    And I should see a success message "User deleted successfully."

  Scenario: Update user information
    Given I am logged in as an admin
    And I am on the admin dashboard
    And I select "User Management" from the dashboard options
    And I am on the user management page
    When I choose to update the user with oldusername "ahmad" and I enter the new username "ahmad" and I enter the new password "newpassword" and role "user"
    And I submit the updated user details
    Then I should see all users in the list
    And I should see a success message "User updated successfully."
