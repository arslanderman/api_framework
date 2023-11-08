@tag_group_create
Feature: Tag Group Create


  Scenario: organizer does not exist

  Given user creates request body with non existing organizer
  When  user sends post request with non existing organizer
  Then user validates organizer not found error with "404"status code

    Scenario: name already exist for related organizer

      Given user creates request body with existing name
      When user sends post request with existing name
      Then user validates name already exist for organizer error with 409 status code

    Scenario: parent group does not exist

      Given user creates request body with non existing parent group
      When  When user sends post request with non existing parent group
      Then user validates group not found error with 404 status code

    Scenario: parent group is not linked to organizer

      Given user creates request body with parent group is not linked to organizer
      When  When user sends post request with parent group is not linked to organizer
      Then user validates parent group is not linked to organizer error with 409 status code

    Scenario: parent group is archived

      Given user creates request body when parent group archived
      When  When user sends post request when parent group archived
      Then  user validates parent group is archived error with 409 status code

    Scenario: tag group create

      Given user creates request body for tag group creation
      When  When user sends post request for tag group creation
      Then  user validates tag group creation

    Scenario: tag creation

     Given user creates request body for tag group creation
     When  When user sends post request for tag group creation
     Then  user validates tag group creation without parent id


