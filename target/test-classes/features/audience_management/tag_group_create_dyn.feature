@tag_group_create_dyn

  Feature: tag group create

    Scenario: tag group create with all scenarios

      Given user creates request for tag group creation
      When  user sends post request for dynamic tag group creation
      Then user validates dynamic tag group creation