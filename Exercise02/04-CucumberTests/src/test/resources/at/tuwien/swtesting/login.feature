Feature: Login to Bugzilla
  As a user I want to authenticate myself before making persistent changes.

  Scenario: Login as user admin
    Given I am on the homepage
    When I go to the loginpage
	And I login as user "admin@example.com" with password "Test1234"
    Then I should be on the homepage
	And I should be logged in
