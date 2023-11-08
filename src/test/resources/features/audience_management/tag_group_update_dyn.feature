@tag_group_update_dyn

  Feature: Tag group update with db

    Scenario: Tag group update with all scenarios

      Given user creates request for tag group update
      When  user sends post request for dynamic tag group update
      Then  user validates tag group update

