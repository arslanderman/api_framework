@tag_group_update

  Feature: Tag Group Update

    Scenario: tag group update with non existing organizer

      Given user creates request body for tag group update with non existing organizer
      When  user sends post request for tag group update with non existing organizer
      Then user validates organizer not found error for tag group update with "404" status code

    Scenario: tag group update when tag group does not exist

      Given user creates request body with non existing tag group
      When  When user sends post request with non existing tag group
      Then user validates tag group not found error with "404" status code

    Scenario: tag group update when tag group does not link to organizer

      Given user creates request body for tag group update which is not linked to organizer
      When  When user sends post request with tag group update request body is not linked to organizer
      Then user validates tag group is not linked to organizer error with "409" status code for update ep

    Scenario:  tag group update when tag group is archived

      Given user creates request body for with archived tag group
      When  user sends post request with archived tag group
      Then user validates tag group is archived error with "409" status code for update ep

    Scenario: tag group update when name already exist for organizer

      Given user creates request body with existing name for organizer
      When  user sends post request for tag group update with existing name
      Then  user validates name already exist error for update ep with "409" status code

    Scenario: tag group update with archived name for organizer

      Given user creates request body with archived name for organizer
      When  user sends post request for tag group update with archived name
      Then  user validates tag group update with empty response with "204" status code

    Scenario: tag group update when parent id is null

      Given user creates request body for tag group update without parent_id
      When  user sends post request for tag group update without parent_id
      Then  user validates tag group update with empty response with "204" status code

    Scenario: tag group update when parent id and tag id are same

      Given user creates request body with same parent_id and tag_group_id
      When  user sends post request for tag group update with same parent_id and tag_group_id
      Then  user validates parent_id and id can not be same error with "409" status code

    Scenario: tag group update when parent group does not exist

      Given user creates request body with non existing parent_id for uppate ep
      When  user sends post request for tag group update with non existing parent_id
      Then  user validates parent group not found error with "409" status code

    Scenario: tag group update when parent group does not link to organizer

      Given user creates request body with parent_id which has not link to organizer for uppate ep
      When  user sends post request for tag group update with parent_id which has not link to organizer
      Then  user validates parent group is not link to organizer error with "409" status code for update ep

    Scenario:  tag group update when parent group is archived

      Given user creates request body for with archived parent group for update ep
      When  user sends post request with archived parent group for update ep
      Then user validates parent group is archived error with "409" status code for update ep

    Scenario: tag group update

      Given user creates request body for update ep
      When  user sends post request for tag group update
      Then  user validates tag group update with empty response with "204" status code

