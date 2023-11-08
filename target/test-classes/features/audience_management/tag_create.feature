@tag_create
Feature: Tag create
Background: headers definition
  Scenario: organizer does not exist

    Given user creates request body for tag creation with non existing organizer
    When  user sends post request for tag creation with non existing organizer
    Then  user validates organizer not found error for tag creation with "404" status code

  Scenario: tag group does not exist for related organizer

    Given user creates request body for tag creation  with non existing tag group
    When  user sends post request with non existing tag group
    Then  user validates tag group not found error for tag creation with "404" status code

  Scenario: tag group is not linked to organizer

    Given user creates request body with tag group which is not linked to organizer
    When  When user sends post request with tag group which is not linked to organizer
    Then  user validates tag group is not linked to organizer error with "409" status code

  Scenario: tag group is archived

    Given user creates request body when tag group archived
    When  When user sends post request when tag group archived
    Then  user validates tag group is archived error with "409" status code

  Scenario: name already exist for related organizer

    Given user creates request body for tag creation with existing name
    When  user sends post request for tag creation with existing name
    Then  user validates name already exist for organizer error for tag creation with "409" status code

  Scenario: name already archived for related organizer

    Given user creates request body with archived name
    When  user sends post request with archived name
    Then  user validates tag creation

  Scenario: tag creation

    Given user creates request body for tag creation
    When  When user sends post request for tag creation
    Then  user validates tag creation



