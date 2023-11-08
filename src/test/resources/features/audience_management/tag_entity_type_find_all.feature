@tag_entity_type_find_all
Feature: Find all entity types


  Scenario: tag entity type find all

    Given user enters empty data
    When user sends post request for tag entity type find all end point
    Then  user validates entity listed types