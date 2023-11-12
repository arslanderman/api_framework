package stepdefinitions.audience_management_step_defs;

import base_url_set_up.CoreBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import pojo.audience_pojo.ResponseTagAndTagGroupPojo;
import pojo.audience_pojo.TagAndTagGroupResponseDataPositive;
import pojo.audience_pojo.TagEntityPojo;
import utilities.ConfigReader;

import java.io.EOFException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static utilities.DBUtils.*;

public class TagEntityAddStepDefs extends CoreBaseURL {

    TagEntityPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    //TagAndTagGroupResponseDataPositive actualDataPositive;
    TagAndTagGroupResponseDataPositive actualDataWithTag_or_TagGroup_ID;
    Response response;
    ObjectMapper obj = new ObjectMapper();
    int i = Integer.parseInt(ConfigReader.getProperty("org_id"));
    int y = Integer.parseInt(ConfigReader.getProperty("status_a"));
    int j = Integer.parseInt(ConfigReader.getProperty("status_b"));
    int z = Integer.parseInt(ConfigReader.getProperty("status_d"));
    int employeeId = Integer.parseInt(ConfigReader.getProperty("employeeId"));
    List<Integer> entityId = Arrays.asList(Integer.parseInt(ConfigReader.getProperty("entity_no_1")),
            Integer.parseInt(ConfigReader.getProperty("entity_no_2")), Integer.parseInt(ConfigReader.getProperty("entity_no_3")));


    {
        coreSetUp();
        spec.pathParams("first", "setting", "second", "tag", "third", "entity", "fourth", "add");
    }

    @Given("user creates request body for tag entity add")
    public void user_creates_request_body_for_tag_entity_add() {
        requestBody = new TagEntityPojo(ConfigReader.getProperty("tag_id"), entityId, ConfigReader.getProperty("entity_type"), employeeId, i);
        System.out.println("entity id list "+entityId);
    }

    @When("user sends post request for tag entity add")
    public void user_sends_post_request_for_tag_entity_add() {
        response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();
    }

    @Then("user verifies link between entity and tag")
    public void user_verifies_link_between_entity_and_tag() throws SQLException, IOException {
        createConnection();
        List<Integer> listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"), "id_organizer");
        List<Object> listOfTags = getColumnData(ConfigReader.getProperty("query_existing_tags"), "id");
        List<Object> listOfTagsForOrg = getColumnData(ConfigReader.getProperty("query_existing_tags_for_organizer"), "id");
        List<Object> listOfArchivedTags = getColumnData(ConfigReader.getProperty("query_archived_tags"), "id");
        List<Object> listOfEntityTypes = getColumnData(ConfigReader.getProperty("query_entity_types"), "entity_type");
        List<Integer> listOfCustomerEntities = getColumnDataAsInteger(ConfigReader.getProperty("query_entity_customer"), "id_customer");
        List<Integer> listOfEventEntities = getColumnDataAsInteger(ConfigReader.getProperty("query_entity_event"), "id_category");
        List<Integer> listOfSessionEntities = getColumnDataAsInteger(ConfigReader.getProperty("query_entity_session"), "id_session");
        List<Integer> listOfProductEntities = getColumnDataAsInteger(ConfigReader.getProperty("query_entity_product"), "id_product");
        List<Integer> listOfCustomerFromDifferentOrganizer =
                getColumnDataAsInteger(ConfigReader.getProperty("query_customers_from_different_organizers"), "id_customer");
        List<Integer> listOfEventFromDifferentOrganizer =
                getColumnDataAsInteger(ConfigReader.getProperty("query_events_from_different_organizers"), "id_category");
        List<Integer> listOfSessionFromDifferentOrganizer =
                getColumnDataAsInteger(ConfigReader.getProperty("query_sessions_from_different_organizers"), "id_session");
        List<Integer> listOfProductFromDifferentOrganizer =
                getColumnDataAsInteger(ConfigReader.getProperty("query_products_from_different_organizers"), "id_product");
        List<Integer> listOfAllEntities = getColumnDataAsInteger(ConfigReader.getProperty("query_existing_entities"), "entity_id");
        List<Object> listOfAllTagsLinkToEntity = getColumnData(ConfigReader.getProperty("query_existing_entities"), "tag_id");

        if (!listOfOrganizers.contains(Integer.parseInt(ConfigReader.getProperty("org_id")))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"), ConfigReader.getProperty("concept"),
                    null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), y);
            System.out.println("error : organizer does not exist!");

        } else if (!listOfTags.contains(ConfigReader.getProperty("tag_id"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_non_existing"),
                    ConfigReader.getProperty("concept_tag_non_existing"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(), y);
            System.out.println("error : tag does not exist!");

        } else if (!listOfTagsForOrg.contains(ConfigReader.getProperty("tag_id"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_not_linked_org"),
                    ConfigReader.getProperty("concept_tag_not_linked_org"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(), j);
            System.out.println("error : tag does not link to organizer!");

        } else if (listOfArchivedTags.contains(ConfigReader.getProperty("tag_id"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_archived_tag"),
                    ConfigReader.getProperty("concept_archived_tag"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(), j);
            System.out.println("error : tag has been archived!");

        } else if (!listOfEntityTypes.contains(ConfigReader.getProperty("entity_type"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_entity_type_not_found"),
                    ConfigReader.getProperty("concept_entity_type_not_found"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(), y);
            System.out.println("error : entity type not found!");

        } else for (int i : entityId) {
            if (ConfigReader.getProperty("entity_type").equals("customer") && !listOfCustomerEntities.contains(i)) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_customer"),
                        ConfigReader.getProperty("concept_customer"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(response.getStatusCode(), y);
                System.out.println("entity not found");
            } else if (ConfigReader.getProperty("entity_type").equals("event") && !listOfEventEntities.contains(i)) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_event"),
                        ConfigReader.getProperty("concept_event"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(response.getStatusCode(), y);
                System.out.println("entity not found");
            } else if (ConfigReader.getProperty("entity_type").equals("session") && !listOfSessionEntities.contains(i)) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_session"),
                        ConfigReader.getProperty("concept_session"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(response.getStatusCode(), y);
                System.out.println("entity not found");
            } else if (ConfigReader.getProperty("entity_type").equals("product") && !listOfProductEntities.contains(i)) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_product"),
                        ConfigReader.getProperty("concept_product"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(response.getStatusCode(), y);
                System.out.println("entity not found");
            } else if (listOfCustomerFromDifferentOrganizer.contains(i)) {
                System.out.println("entity not linked to organizer");
            } else if (listOfEventFromDifferentOrganizer.contains(i)) {
                System.out.println("entity not link to organizer");
            } else if (listOfSessionFromDifferentOrganizer.contains(i)) {
                System.out.println("entity not link to organizer");
            } else if (listOfProductFromDifferentOrganizer.contains(i)) {
                System.out.println("entity not link to organizer");
            }else if (listOfAllEntities.contains(i)&&listOfAllTagsLinkToEntity.contains(ConfigReader.getProperty("tag_id"))){
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_entity_link_to_tag"),
                        ConfigReader.getProperty("concept_entity_link_to_tag"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(response.getStatusCode(), j);
                System.out.println("entity already link to a tag");
            }else {
                try {
                    actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                    String id = actualDataWithTag_or_TagGroup_ID.getData().getId();
                    Assert.assertTrue(id == null || id.isEmpty());
                } catch (EOFException e) {
                    System.out.println("The response has no content, entity has been linked to the tag!");
                    Assert.assertEquals(response.getStatusCode(), z);
                   }
                }
            }
        }
    }




