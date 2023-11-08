@tag_update
Feature: Tag update with db connection

  Scenario: Tag update with all scenarios

    Given user creates request for tag update
    When  user sends post request for tag update
    Then  user validates tag update