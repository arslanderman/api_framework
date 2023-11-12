package stepdefinitions.audience_management_step_defs;

import base_url_set_up.CoreBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import pojo.audience_pojo.FindAllTagsPositivePojo;
import pojo.audience_pojo.ResponseTagAndTagGroupPojo;
import pojo.audience_pojo.TagAndTagGroupResponseDataPositive;
import pojo.audience_pojo.TagEntitySecondPojo;
import utilities.ConfigReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static utilities.DBUtils.getColumnData;
import static utilities.DBUtils.getColumnDataAsInteger;

public class FindAllTagsLinkToAnEntity extends CoreBaseURL {
    TagEntitySecondPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    TagAndTagGroupResponseDataPositive actualDataWithTag_or_TagGroup_ID;
    FindAllTagsPositivePojo actualDataWithTagDetails;
    Response response;
    ObjectMapper obj = new ObjectMapper();

    List<String> entityType = Arrays.asList(ConfigReader.getProperty("entity_type"));
    List<String> entityTypeList = Arrays.asList(ConfigReader.getProperty("entity_type1"), ConfigReader.getProperty("entity_type2"),
            ConfigReader.getProperty("entity_type3"), ConfigReader.getProperty("entity_type4"));
    List<Integer> listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"), "id_organizer");
    List<Object> listOfEntityTypes = getColumnData(ConfigReader.getProperty("query_entity_types"), "entity_type");
    List<Integer> listOfEntitiesLinkToTag = getColumnDataAsInteger(ConfigReader.getProperty("query_entities_on_core_tag_entity"), "entity_id");
    List<Integer> listOfEntitiesLinkToOrganizer = getColumnDataAsInteger(ConfigReader.getProperty("query_entities_with_organizer"), "entity_id");
    List<Object> listOfArchivedTags = getColumnData(ConfigReader.getProperty("query_archived_tags"), "id");
    List<Integer> listOfArchivedEntities = getColumnDataAsInteger(ConfigReader.getProperty("query_archived_entity"), "entity_id");

    int j = Integer.parseInt(ConfigReader.getProperty("org_id"));
    int i = Integer.parseInt(ConfigReader.getProperty("entity_no_1"));

    {
        coreSetUp();
        spec.pathParams("first", "setting", "second", "tag", "third", "find-by-entity-id-and-type");
    }

    @Given("user creates request body for finding all tags link to an entity")
    public void user_creates_request_body_for_finding_all_tags_link_to_an_entity() {
        requestBody = new TagEntitySecondPojo(i, ConfigReader.getProperty("entity_type"), j);
    }
    @When("user sends post request for finding all tags link to an entity")
    public void user_sends_post_request_for_finding_all_tags_link_to_an_entity() {
        response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}");
        response.prettyPrint();
    }
    @Then("user validates organizer not found error with {string} status code")
    public void user_validates_organizer_not_found_error_with_status_code(String statusCode_a) throws IOException {
        if (!listOfOrganizers.contains(j)) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"), ConfigReader.getProperty("concept"),
                    null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode_a));
            System.out.println("organizer does not exist");
            Assert.assertNotNull(actualDataWithTag_or_TagGroup_ID.getData());
        }
    }
    @Then("user validates entity type not found error with {string} status code")
    public void user_validates_entity_type_not_found_error_with_status_code(String statusCode_a) throws IOException {
        if (!listOfEntityTypes.contains(ConfigReader.getProperty("entity_type"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_entity_type_not_found"),
                    ConfigReader.getProperty("concept_entity_type_not_found"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode_a));
            System.out.println("error : entity type not found!");
            Assert.assertNotNull(actualDataWithTag_or_TagGroup_ID.getData());
        }
    }
    @Then("user validates entity not found error with {string} status code")
    public void user_validates_entity_not_found_error_with_status_code(String statusCode_a) throws IOException {
        for (String s : entityType) {
            if (s.equals(entityTypeList.get(0)) && !listOfEntitiesLinkToTag.contains(i)) {
                responseBody = new ResponseTagAndTagGroupPojo(null, ConfigReader.getProperty("concept_customer"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode_a));
                System.out.println("entity not found");
                Assert.assertNotNull(actualDataWithTag_or_TagGroup_ID.getData());
            } else if (s.equals(entityTypeList.get(1)) && !listOfEntitiesLinkToTag.contains(i)) {
                responseBody = new ResponseTagAndTagGroupPojo(null, ConfigReader.getProperty("concept_event"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode_a));
                System.out.println("entity not found");
                Assert.assertNotNull(actualDataWithTag_or_TagGroup_ID.getData());
            } else if (s.equals(entityTypeList.get(2)) && !listOfEntitiesLinkToTag.contains(i)) {
                responseBody = new ResponseTagAndTagGroupPojo(null, ConfigReader.getProperty("concept_session"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode_a));
                System.out.println("entity not found");
                Assert.assertNotNull(actualDataWithTag_or_TagGroup_ID.getData());
            } else if (s.equals(entityTypeList.get(3)) && !listOfEntitiesLinkToTag.contains(i)) {
                responseBody = new ResponseTagAndTagGroupPojo(null, ConfigReader.getProperty("concept_product"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode_a));
                System.out.println("entity not found");
                Assert.assertNotNull(actualDataWithTag_or_TagGroup_ID.getData());
            } else if (!entityTypeList.contains(s)){
                System.out.println("entity not found");
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                Assert.assertNotNull(actualDataWithTag_or_TagGroup_ID.getData());
            }
        }
    }
    @Then("user validates entity not link to organizer error with {string} status code")
    public void user_validates_entity_not_link_to_organizer_error_with_status_code(String statusCode_b) throws IOException {
        for (String s : entityType) {
            if (s.equals(entityTypeList.get(0)) && !listOfEntitiesLinkToOrganizer.contains(i)) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_customer_not_link_org"), null, null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode_b));
                System.out.println("error : entity not link to organizer!");
                Assert.assertNotNull(actualDataWithTag_or_TagGroup_ID.getData());
            } else if (s.equals(entityTypeList.get(1)) && !listOfEntitiesLinkToOrganizer.contains(i)) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_event_not_link_org"), null, null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode_b));
                System.out.println("error : entity not link to organizer!");
                Assert.assertNotNull(actualDataWithTag_or_TagGroup_ID.getData());
            } else if (s.equals(entityTypeList.get(2)) && !listOfEntitiesLinkToOrganizer.contains(i)) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_session_not_link_org"), null, null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode_b));
                System.out.println("error : entity not link to organizer!");
                Assert.assertNotNull(actualDataWithTag_or_TagGroup_ID.getData());
            } else if (s.equals(entityTypeList.get(3)) && !listOfEntitiesLinkToOrganizer.contains(i)) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_product_not_link_org"), null, null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode_b));
                System.out.println("error : entity not link to organizer!");
                Assert.assertNotNull(actualDataWithTag_or_TagGroup_ID.getData());
            } else if (!entityTypeList.contains(s)){
                System.out.println("entity not link to organizer!");
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                Assert.assertNotNull(actualDataWithTag_or_TagGroup_ID.getData());
            }
        }
    }
    @Then("user validates all tags link to given entity with {string} status code")
    public void user_validates_all_tags_link_to_given_entity_with_status_code(String statusCode_c) throws IOException {
            actualDataWithTagDetails = obj.readValue(response.asString(), FindAllTagsPositivePojo.class);
            System.out.println("actualdata " + actualDataWithTagDetails);
            Assert.assertNotNull(actualDataWithTagDetails.getData());
            Assert.assertFalse(actualDataWithTagDetails.getData().toString().isEmpty());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode_c));
            boolean flag = false;
            boolean flag1 = false;
        for (int k = 0; k < actualDataWithTagDetails.getData().size(); k++) {
            if (listOfArchivedTags.contains(actualDataWithTagDetails.getData().get(k).getId())) {
                System.out.println("error : one of the displayed tag is archived ");
                flag = true;
            } else if (listOfArchivedEntities.contains(i)) {
                System.out.println("error : entity is archived");
                flag1 = true;
            }
        }
            Assert.assertFalse(flag);
            Assert.assertFalse(flag1);
    }
 }
