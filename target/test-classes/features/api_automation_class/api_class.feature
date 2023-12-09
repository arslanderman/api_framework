@api_class

Feature: Api Class

  Scenario: get request for users
    Given user creates request for getting user data
    When user sends get request for user data
    Then user validates user data with a "200" status code