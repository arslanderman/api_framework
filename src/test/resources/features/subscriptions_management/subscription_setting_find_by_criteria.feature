@subscription_find_by_criteria

  Feature: Subscription Find All By Criteria

    Scenario: organizer exists

      Given user creates request body for finding all subscriptions by criteria
      When user sends post request for finding all subscriptions by criteria
      Then user validates organizer not found error with "404" status code for finding all subscriptions by criteria

    Scenario: shop exists

       Given user creates request body for finding all subscriptions by criteria
       When user sends post request for finding all subscriptions by criteria
       Then user validates shop not found error with "404" status code for finding all subscriptions by criteria

     Scenario: shop links to organizer

       Given user creates request body for finding all subscriptions by criteria
       When user sends post request for finding all subscriptions by criteria
       Then user validates shop not link to organizer with "409" status code for finding all subscriptions by criteria

     Scenario: fina all subscriptions by criteria

       Given user creates request body for finding all subscriptions by criteria
       When user sends post request for finding all subscriptions by criteria
       Then user validates all subscriptions displayed linked to criteria with "200" status code