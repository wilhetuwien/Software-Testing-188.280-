Feature: Report bugs
  As a user I want to be able to add a bug report to the database.

  Scenario: Add a new bug report
    Given I am logged in on the homepage
    When I go to the createbugpage
    And I add a summary "TestCreateBug"
    And I add a description "A simple test for creating a bugreport"
    And I submit the bug
    Then I should be on the showbugpage
    And there should be a bugcreationelement present
    And the first comment should be "A simple test for creating a bugreport"
    And the summary should be "TestCreateBug"
    And I clean up after my createbugtest
