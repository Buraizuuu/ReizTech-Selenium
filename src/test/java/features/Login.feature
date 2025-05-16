Feature:  Login Page
  Scenario: Login using valid username and password
    Given User navigates to login page
    When User populates username and password
    And Clicks login button
    Then Verify if user successfully logged-in
