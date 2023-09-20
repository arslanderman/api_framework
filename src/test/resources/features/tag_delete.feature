@tag_delete

  Feature: Tag delete

    Scenario: tag delete with all scenario

      Given user creates request body for tag delete
      When  user sends post request for tag delete
      Then  user validates tag deletion