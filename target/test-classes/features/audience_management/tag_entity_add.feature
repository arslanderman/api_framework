@tag_entity_add

  Feature: Tag entity add

    Scenario: linking a tag to an entity (customer,event,session,product) with all scenarios

      Given user creates request body for tag entity add
      When  user sends post request for tag entity add
      Then  user verifies link between entity and tag

