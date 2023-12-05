@subscriptions_unarchive

Feature: Subscription settings unarchive

  Scenario: organizer exists

    Given user creates request body for subscription setting unarchive
    When user sends post request for subscription setting unarchive
    Then user validates organizer not found error with "404" status code for subscription unarchive

  Scenario: subscription setting exists

    Given user creates request body for subscription setting unarchive
    When user sends post request for subscription setting unarchive
    Then user validates subscription setting not found error with "404" status code for subscription setting unarchive

  Scenario: subscription setting links to organizer

    Given user creates request body for subscription setting unarchive
    When user sends post request for subscription setting unarchive
    Then user validates subscription setting not link to organizer error with "409" status code for subscription setting unarchive

  Scenario: subscription setting is archived

    Given user creates request body for subscription setting unarchive
    When user sends post request for subscription setting unarchive
    Then user validates subscription setting is not archived error with "409" status code for subscription setting unarchive

  Scenario: subscription setting unarchive

    Given user creates request body for subscription setting unarchive
    When user sends post request for subscription setting unarchive
    Then user validates archive_at to null on core_subscription_settings with "204" status code