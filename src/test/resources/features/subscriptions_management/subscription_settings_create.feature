@subscription_create

  Feature: Creating Subscription

    Scenario: organizer does exist

      Given user creates request body for subscription creation
      When user sends post request for subscription creation
      Then user validates organizer not found error with "404" status code for subscription creation

     Scenario: shop exists

       Given user creates request body for subscription creation
       When user sends post request for subscription creation
       Then user validates shop not found error with "404" status code for subscription creation

     Scenario: shop links to organizer

       Given user creates request body for subscription creation
       When user sends post request for subscription creation
       Then user validates shop not link to organizer with "409" status code for subscription creation

     Scenario: name is not empty or not filled only with spaces

       Given user creates request body for subscription creation
       When user sends post request for subscription creation
       Then user validates name is empty or filled with spaces with "409" status code

     Scenario: subscription create

       Given user creates request body for subscription creation
       When user sends post request for subscription creation
       Then user validates new rows on core_subscription_settings and core_subscription_setting_lang with "201" status code


