@tags_linked_to_entity

  Feature: Finding all tags those link to given entity

    Scenario: organizer does exist

      Given user creates request body for finding all tags link to an entity
      When user sends post request for finding all tags link to an entity
      Then user validates organizer not found error with "404" status code

    Scenario: entity type found

      Given user creates request body for finding all tags link to an entity
      When user sends post request for finding all tags link to an entity
      Then user validates entity type not found error with "404" status code

    Scenario: entity found

      Given user creates request body for finding all tags link to an entity
      When user sends post request for finding all tags link to an entity
      Then user validates entity not found error with "404" status code

    Scenario: entity link to organizer

      Given user creates request body for finding all tags link to an entity
      When user sends post request for finding all tags link to an entity
      Then user validates entity not link to organizer error with "409" status code

    Scenario: find all tags link to entity

      Given user creates request body for finding all tags link to an entity
      When user sends post request for finding all tags link to an entity
      Then user validates all tags link to given entity with "200" status code




