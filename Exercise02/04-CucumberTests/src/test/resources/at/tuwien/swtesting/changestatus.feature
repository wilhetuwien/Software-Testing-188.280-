Feature: Change status of bug
    As a user i want to be able to change the status of a bug

  Scenario: Set status of a cloned report to RESOLVED FIXED
    Given I am logged in on the showbugpage and I have created a bug and cloned it
    When I set bug status to "RESOLVED"
    And I set resolution status to "FIXED"
    And I set the comment to "Fixed this bug "
    And I submit the bugreport
    Then I should be on the processbugpage
    And I go to the bug i changed
    And the bug status should be "RESOLVED FIXED (edit)"
    And the second comment should be "Fixed this bug"
    And I clean up after my changestatustest
