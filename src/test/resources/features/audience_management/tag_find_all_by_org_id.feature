@tag_find_all_by_org

  Feature: tag find all by organizer id

    Scenario: find all tags

      Given user generates requset body with organizer id
      When user sends post request for finding all tags related to organizer
      Then user verifies all tags info listed consecutively
