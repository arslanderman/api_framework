@tag_group_delete

Feature: Tag Group Delete

  Scenario: tag group delete with non existing organizer

    Given user creates request body for tag group delete with non existing organizer
    When  user sends post request for tag group delete with non existing organizer
    Then user validates organizer not found error for tag group delete with "404" status code

  Scenario: tag group delete when tag group does not exist

    Given user creates request body with non existing tag group for delete ep
    When  When user sends post request with non existing tag group for delete ep
    Then user validates tag group not found error with "404" status code for delete ep

  Scenario: tag group delete when tag group does not link to organizer

    Given user creates request body for tag group delete which is not linked to organizer
    When  user sends post request which is not linked to organizer for delete ep
    Then user validates tag group is not linked to organizer error with "409" status code for delete ep

  Scenario:  tag group delete when tag group is archived

    Given user creates request body with archived tag group for delete ep
    When  user sends post request for delete ep
    Then user validates tag group is archived error with "409" status code for delete ep

  Scenario: tag group delete when tag group is not archived

    Given user creates request body with not archived tag group for delete ep
    When  user sends post request for delete ep with not archived tag group
    Then  user validates tag group delete with empty response with "204" status code

  Scenario: tag group delete when tag group link to at least one tag those not archived

    Given user creates request body with tag group which has link to at least one tag those not archived
    When  user sends post request with tag group that has link to at least one tag
    Then  user validates tag group linked to tag not archived error with "409" status code

  Scenario: tag group delete when tag group link to one tag those archived

    Given user creates request body with tag group which has link to at least one tag those archived
    When  user sends post request with tag group that has link to at least one tag which is archived
    Then  user validates  empty response with "204" status code with linked archived tag

  Scenario: tag group delete when tag group link to child group those not archived

    Given user creates request body with tag group which has link to at least one child tag group those not archived
    When  user sends post request with tag group which has link to at least one child tag group those not archived
    Then  user validates tag group linked to group not archived error with "409" status code

  Scenario: tag group delete when tag group link to child group those archived

    Given user creates request body with tag group which has link to at least one child tag group those archived
    When  user sends post request with tag group which has link to at least one child tag group those archived
    Then  user validates  empty response with "204" status code with linked archived tag group



