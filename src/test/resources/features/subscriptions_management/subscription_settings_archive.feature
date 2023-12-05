@subscriptions_archive

Feature: Subscription settings archive

  Scenario: organizer exists

    Given user creates request body for subscription setting archive
    When user sends post request for subscription setting archive
    Then user validates organizer not found error with "404" status code for subscription archive

  Scenario: subscription setting exists

    Given user creates request body for subscription setting archive
    When user sends post request for subscription setting archive
    Then user validates subscription setting not found error with "404" status code for subscription setting archive

  Scenario: subscription setting links to organizer

    Given user creates request body for subscription setting archive
    When user sends post request for subscription setting archive
    Then user validates subscription setting not link to organizer error with "409" status code for subscription setting archive

  Scenario: subscription setting is not active

    Given user creates request body for subscription setting archive
    When user sends post request for subscription setting archive
    Then user validates subscription setting is active error with "409" status code for subscription setting archive

  Scenario: subscription setting does not link to event

    Given user creates request body for subscription setting archive
    When user sends post request for subscription setting archive
    Then user validates subscription setting is link to an event error with "409" status code for subscription setting archive

  Scenario: subscription setting does not link to session

    Given user creates request body for subscription setting archive
    When user sends post request for subscription setting archive
    Then user validates subscription setting is link to a session error with "409" status code for subscription setting archive

  Scenario: subscription setting does not link to sales phase

    Given user creates request body for subscription setting archive
    When user sends post request for subscription setting archive
    Then user validates subscription setting is link to a sales phase error with "409" status code for subscription setting archive

  Scenario: subscription setting has been bought

    Given user creates request body for subscription setting archive
    When user sends post request for subscription setting archive
    Then user validates subscription setting has not bought error with "409" status code for subscription setting archive

  Scenario: subscription is active on core_subscription

    Given user creates request body for subscription setting archive
    When user sends post request for subscription setting archive
    Then user validates subscription is not active on core_subscription error with "409" status code for subscription setting archive

  Scenario: subscription setting archive

    Given user creates request body for subscription setting archive
    When user sends post request for subscription setting archive
    Then user validates archive_at to now update on core_subscription_settings with "204" status code

