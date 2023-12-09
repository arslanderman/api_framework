@dummy_json

Feature: Getting Token

  Scenario: getting token with correct credentials
    Given user creates request body for getting token
    When user sends get request for getting dummy json token
    Then user validates token and other data with a "200" status code