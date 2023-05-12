Feature: Report bugs
  As a user I want to be able to add a bug report to the database.

  Background:
    Given I am on the homepage
    When I go to the loginpage
	And I login as user "admin@example.com" with password "Bugzilla1"
    Then I should be on the homepage

  Scenario: Add a new bug report
    Given I am on the homepage
    When I go to the createbugpage
    And I add a summary "TestCreateBug"
    And I add a description "A simple test for creating a bugreport"
    And submit the bug
    Then I should be on the showbugpage
    And there should be a bugcreationelement present
    And the first comment should be "A simple test for creating a bugreport"
    And the summary should be "TestCreateBug"
