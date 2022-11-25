@url
Feature: Registration

  @Signup
  Scenario Outline: sign up a new user on gootickets.com
    Given user is on the sign up page
    And user selects the gender
    Then user enters firstname and lastname as "<firstname>" and "<lastname>"
    And user provides email as "<email>"
    When user enters the password as "<firstpassword>"
    And user confirms the new password as "<secondpassword>"
    Then user clicks on general terms and conditions radio button
    And user clicks on create your account button and sees the message as "<message>"

Examples:
    |firstname|lastname|email|firstpassword|secondpassword|message|
    |Andrew   |Ryan    |aryan@gmail.com|aryan@123|aryan@123|Welcome to your account dashboard       |

