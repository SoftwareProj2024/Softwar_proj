Feature: Communication and Notification
  As a store owner or raw material supplier
  I want to communicate with users and other suppliers
  So that I can handle inquiries and collaborate effectively

  Scenario: Use the messaging system to communicate with users
    Given I am logged in as a store owner 
    When I navigate to the messaging dashboard 
    And I select a user "musa" and I write a message "hello man"
    Then i submit
    And message sent success and store Owner return to the messaging dashboard 

  Scenario: Use the messaging system to communicate with other suppliers
    Given I am logged in as a store owner 
    When I navigate to the messaging dashboard 
    And I select a supllier "musa" and I write a message "hello man"
    Then i submit
    And message sent success and user return to the messaging dashboard 
    
    
    Scenario: user sending message to owner
    Given I am logged in as a user
    When I navigate to the messaging dashboard 
    And I select a store owner "musa" and I write a message "hello owner"
    Then i submit
    And message sent success and user return to the messaging dashboard 

 # Scenario: Receive notifications for special requests [BONUS]
  #  Given I am logged in as a store owner or raw material supplier
   # And I have opted in for email notifications
    #When a user makes a special request
    #Then I should receive an email notification
