package stepdefinitions.audience_management_step_defs;

import audience_management_test_data.Headers;
import base_url_set_up.AudienceManagementBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import pojo.ResponseTagAndTagGroupPojo;
import pojo.TagAndTagGroupPojo;
import pojo.TagAndTagGroupResponseDataPositive;
import utilities.ConfigReader;
import utilities.DBUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static utilities.DBUtils.*;

public class TagGroupCreateDynStepDefs extends AudienceManagementBaseURL {

    TagAndTagGroupPojo requestBody;
    Response response;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    TagAndTagGroupResponseDataPositive actualDataWithTag_or_TagGroup_ID;
    ObjectMapper obj = new ObjectMapper();
    int i = Integer.parseInt(ConfigReader.getProperty("org_id"));
    int y = Integer.parseInt(ConfigReader.getProperty("status_a"));
    int j = Integer.parseInt(ConfigReader.getProperty("status_b"));
    int z = Integer.parseInt(ConfigReader.getProperty("status_c"));

        {
            audienceManagementSetUp();
            spec.pathParams("first", "setting", "second", "tag", "third", "group", "fourth", "create");
        }

        @Given("user creates request for tag group creation")
        public void user_creates_request_for_tag_group_creation () {
            requestBody = new TagAndTagGroupPojo(i, null, ConfigReader.getProperty("name"),
                    ConfigReader.getProperty("parent_id"), null, null, null);
        }
        @When("user sends post request for dynamic tag group creation")
        public void user_sends_post_request_for_dynamic_tag_group_creation () throws SQLException {

            //response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}");
            //response.prettyPrint();
        }
        @Then("user validates dynamic tag group creation")
        public void user_validates_dynamic_tag_group_creation () throws SQLException, IOException {
            createConnection();
            List<Integer> listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"), "id_organizer");
            List<Object> listOfTagGroups = getColumnData(ConfigReader.getProperty("query_all_tag_groups"), "id");
            List<Object> listOfTagGroupsWithOrg = getColumnData(ConfigReader.getProperty("query_parent_tag_for_tag_group"), "id");
            List<Object> listOfUnarchivedTagGroups = getColumnData(ConfigReader.getProperty("query_not_archived_tag_group"), "id");
            List<Object> listOfUnarchivedTagGroupNames = getColumnData(ConfigReader.getProperty("query_existing_tag_group_names"), "name");
            response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}");
            if (!listOfOrganizers.contains(Integer.parseInt(ConfigReader.getProperty("org_id")))) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"), ConfigReader.getProperty("concept"),
                        null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(response.getStatusCode(), y);
                System.out.println("error : organizer does not exist!");

            } else if (listOfUnarchivedTagGroupNames.contains(ConfigReader.getProperty("name"))) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_name_exists_group"),
                        ConfigReader.getProperty("concept"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                //System.out.println(actualData);
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(response.getStatusCode(), j);
                System.out.println("error : name already exists!");

            } else if (requestBody.getParentId() == null) {
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                Assert.assertFalse(actualDataWithTag_or_TagGroup_ID.getData().getId().toString().isEmpty());
                Assert.assertTrue(listOfUnarchivedTagGroupNames.contains(ConfigReader.getProperty("name")));
                Assert.assertEquals(response.getStatusCode(), z);

            } else if (!listOfTagGroups.contains(ConfigReader.getProperty("parent_id"))) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_non_exist"),
                        ConfigReader.getProperty("concept_tag_group_non_existing"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(response.getStatusCode(), y);
                System.out.println("error : tag group does not exist!");
            } else if (!listOfTagGroupsWithOrg.contains(ConfigReader.getProperty("parent_id"))) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_parent_id_not_linked"),
                        ConfigReader.getProperty("concept"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(response.getStatusCode(), j);
                System.out.println("error : parent group does not link to organizer!");
            } else if (!listOfUnarchivedTagGroups.contains(ConfigReader.getProperty("parent_id"))) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_archived"),
                        ConfigReader.getProperty("concept_tag_group_archived"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(response.getStatusCode(), j);
                System.out.println("error : parent group is archived!");
            } else {
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                //System.out.println("actual dataaa "+actualDataWithTag_or_TagGroup_ID);
                Assert.assertFalse(actualDataWithTag_or_TagGroup_ID.getData().getId().toString().isEmpty());
                System.out.println("response with tag group id returned");
                //Assert.assertTrue(listOfUnarchivedTagGroupNames.contains(ConfigReader.getProperty("name")));
                Assert.assertEquals(response.getStatusCode(), z);
            }
        }
    }
