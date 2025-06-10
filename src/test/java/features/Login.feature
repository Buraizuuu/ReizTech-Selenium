Feature:  Login Page
  Scenario: Login using valid username and password
    Given User navigates to login page
    When User populates username and password
    And Clicks login button
    Then Verify if user successfully logged-in

Scenario: User logs in and searches the directory by name
    Given User navigates to login page
    When User populates username and password
    And Clicks login button
    And User clicks directory menu
    Then Search via name
