@subscriptions_delete

  Feature: Subscription settings delete

    Scenario: organizer does exist
      Given user creates request body for subscription setting delete with existing organizer
      When user sends post request for subscription setting delete
      Then user validates organizer not found error with "404" status code for subscription delete

    Scenario: subscription setting exists
      Given user creates request body for subscription setting delete with existing subscription
      When user sends post request for subscription setting delete
      Then user validates subscription setting not found error with "404" status code for subscription setting delete

    Scenario: subscription setting links to organizer
      Given user creates request body for subscription setting delete with linked organizer
      When user sends post request for subscription setting delete
      Then user validates subscription setting not link to organizer error with "409" status code for subscription setting delete

    Scenario: subscription setting is not active
      Given user creates request body for subscription setting delete with inactive subscription
      When user sends post request for subscription setting delete
      Then user validates subscription setting is active error with "409" status code

    Scenario: subscription setting does not link to event
      Given user creates request body for subscription setting delete with unlinked event
      When user sends post request for subscription setting delete
      Then user validates subscription setting is link to an event error with "409" status code

    Scenario: subscription setting does not link to session
      Given user creates request body for subscription setting delete with unlinked session
      When user sends post request for subscription setting delete
      Then user validates subscription setting is link to a session error with "409" status code

    Scenario: subscription setting does not link to sales phase
      Given user creates request body for subscription setting delete with unlinked sales phase
      When user sends post request for subscription setting delete
      Then user validates subscription setting is link to a sales phase error with "409" status code

    Scenario: subscription setting has not already been bought
      Given user creates request body for subscription setting delete with unbought subscription
      When user sends post request for subscription setting delete
      Then user validates subscription setting has already bought error with "409" status code

    Scenario: subscription setting delete
      Given user creates request body for subscription setting delete
      When user sends post request for subscription setting delete
      Then user validates row deletions on core_subscription_setting, core_subscription_setting_lang, core_reselling_rule, core_reselling_rule_event, core_th_change_rule, core_th_change_rule_event with "204" status code

