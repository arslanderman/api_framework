@update_active_field
  Feature: Subscription settings updating active field

    Scenario: organizer does exist

      Given user creates request body for subscription setting update active field
      When user sends post request for subscription setting update active field
      Then user validates organizer not found error with "404" status code for updating active field

    Scenario: subscription setting exists

      Given user creates request body for subscription setting update active field
      When user sends post request for subscription setting update active field
      Then user validates subscription setting not found error with "404" status code

    Scenario: subscription setting links to organizer

      Given user creates request body for subscription setting update active field
      When user sends post request for subscription setting update active field
      Then user validates subscription setting not link to organizer error with "409" status code

    Scenario: subscription setting is not archived

      Given user creates request body for subscription setting update active field
      When user sends post request for subscription setting update active field
      Then user validates subscription setting is archived error with "409" status code

    Scenario: subscription setting update active field

      Given user creates request body for subscription setting update active field
      When user sends post request for subscription setting update active field
      Then user validates subscription setting table is_active field is updated with "204" status code
