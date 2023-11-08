@find_all_tag_groups

  Feature: Find all tag groups related to organizer id

    Scenario: Find all tag groups with non existing organizer
      Given user creates request body for listing all tag groups
      When user sends post request for listing tag groups with non existing organizer
      Then user validates organizer not found error for listing tag groups
      And user verifies http status code "404" for non existing organizer

    Scenario: Find all tag groups when archived_at column is not null on core_tag_group table
      Given user creates request body for checking archived groups
      When user send post request to list all tag groups
      Then user validates all tag group those not archived
      And user validates all tag groups those archived
      And user verifies http status code "200"



