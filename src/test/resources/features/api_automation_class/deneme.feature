@deneme

Feature: Musterileri Bul

  Scenario: get request for users
    Given user creates request for fetching users
    When user sends get request for these new users
    Then user validates user data for users with a "200" status code