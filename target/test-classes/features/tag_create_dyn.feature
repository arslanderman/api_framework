@tag_create_dyn
  Feature: Tag Create

    Scenario: tag create with all scenerios
      Given user creates request for tag creation
      When user sends post request for dynamic tag creation
      Then user validates tag creation with a new row on db