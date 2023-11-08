@rule_create

  Feature: Creating tag rule

    Scenario: organizer does exist

      Given user creates request body for rule creation
      When user sends post request for rule creation
      Then user validates organizer not found error with "404" status code for rule creation

    Scenario: name does not exist or archived with linked organizer
      Given user creates request body for rule creation
      When user sends post request for rule creation
      Then user validates name already exists error with "409" status code for rule creation

    Scenario: tags_added and tags_removed credentials are not given empty as input for request
      Given user creates request body for rule creation
      When user sends post request for rule creation
      Then user validates tags_added and tags_removed credentials are empty with "409" status code

    Scenario: criteria is valid for rule creation
      Given user creates request body for rule creation
      When user sends post request for rule creation
      Then user validates criteria is invalid with "409" status code

    Scenario: all entity types are valid in criteria
      Given user creates request body for rule creation
      When user sends post request for rule creation
      Then user validates invalid entity type in criteria with "409" status code

    Scenario: for each tag in criteria they exist
      Given user creates request body for rule creation
      When user sends post request for rule creation
      Then user validates one of the tag in criteria not found with "404" status code

    Scenario: for each tag in criteria link to organizer
      Given user creates request body for rule creation
      When user sends post request for rule creation
      Then user validates one of the tag in criteria not link to organizer with "409" status code

    Scenario: for each tag in criteria are not archived
      Given user creates request body for rule creation
      When user sends post request for rule creation
      Then user validates one of the tag in criteria is archived with "409" status code

    Scenario: for each tag in tags_added and tags_removed they exist
      Given user creates request body for rule creation
      When user sends post request for rule creation
      Then user validates one of the tag in tags_added and tags_removed is not found with "404" status code

    Scenario: for each tag in tags_added and tags_removed link to organizer
      Given user creates request body for rule creation
      When user sends post request for rule creation
      Then user validates one of the tag in tags_added and tags_removed not link to organizer with "409" status code

    Scenario: for each tag in tags_added and tags_removed are not archived
      Given user creates request body for rule creation
      When user sends post request for rule creation
      Then user validates one of the tag in tags_added and tags_removed is archived with "409" status code

     Scenario: tag rule creation
       Given user creates request body for rule creation
       When user sends post request for rule creation
       Then user validates new row on core_tag_rule with "201" status code
