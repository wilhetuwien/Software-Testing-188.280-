Feature: Search bugs
  As a user I want to be able to find reported bugs according to some criteria.

  Scenario: Search for two open bugs in specified product catalog
    Given I am logged in on the showbugpage and I have created two bugreports with summary "searchforbugreport"
    When I go to the searchpage
    And I set bugstatus field to "Open"
    And I set product field to "TestProduct"
    And I set the search term to "searchforbugreport"
    And I submit the search
    Then I should be on the bugslistpage
    And the result count should be "2 bugs found."
    And I cleanup after my searchtest
