package stepdefinitions;

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
import java.util.Arrays;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static utilities.DBUtils.*;

public class TagCreateDynStepDefs extends AudienceManagementBaseURL {

    TagAndTagGroupPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    TagAndTagGroupResponseDataPositive actualDataWithTagID;
    Response response;
    ObjectMapper obj = new ObjectMapper();
    int i = Integer.parseInt(ConfigReader.getProperty("org_id"));
    int y = Integer.parseInt(ConfigReader.getProperty("status_a"));
    int j = Integer.parseInt(ConfigReader.getProperty("status_b"));
    int z = Integer.parseInt(ConfigReader.getProperty("status_c"));


    {
        audienceManagementSetUp();
        spec.pathParams("first","setting","second","tag","third","create");
    }


    @Given("user creates request for tag creation")
    public void user_creates_request_for_tag_creation() {

        requestBody = new TagAndTagGroupPojo(i,null,ConfigReader.getProperty("name"),null,
                ConfigReader.getProperty("color"),null,ConfigReader.getProperty("tag_group_id"));
        System.out.println("requestbody "+requestBody);
    }
    @When("user sends post request for dynamic tag creation")
    public void user_sends_post_request_for_dynamic_tag_creation() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}");
    }
    @Then("user validates tag creation with a new row on db")
    public void user_validates_tag_creation_with_a_new_row_on_db() throws SQLException, IOException {
        createConnection();
        List<Integer> listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"),"id_organizer");
        List<Object> listOfTagGroups = getColumnData(ConfigReader.getProperty("query_all_tag_groups"),"id");
        List<Object> listOfTagsWithOrg = getColumnData(ConfigReader.getProperty("query_tags_org_id"),"id");
        List<Object> listOfTagGroupsWithOrg = getColumnData(ConfigReader.getProperty("query_tag_group"),"id");
        List<Object> listOfUnarchivedTagGroups = getColumnData(ConfigReader.getProperty("query_not_archived_tag_group"),"id");
        List<Object> listOfUnarchivedTagNames = getColumnData(ConfigReader.getProperty("query_existing_tag_names"),"name");
        List<Object> listOfNamesNotArchived = getColumnData(ConfigReader.getProperty("query_existing_names"),"name");

        if(!listOfOrganizers.contains(Integer.parseInt(ConfigReader.getProperty("org_id")))){
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"), ConfigReader.getProperty("concept"),
                        null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(response.getStatusCode(),y);
                System.out.println("error : organizer does not exist!");

        } else if (!listOfTagGroups.contains(ConfigReader.getProperty("tag_group_id"))) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_non_exist"),
                    ConfigReader.getProperty("concept_tag_group_non_existing"), null,null);
               actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
               Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
               Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
               Assert.assertEquals(response.getStatusCode(),y);
               System.out.println("error : tag group does not exist!");

        } else if (!listOfTagGroupsWithOrg.contains(ConfigReader.getProperty("tag_group_id"))) {
               responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_not_linked"),
                    ConfigReader.getProperty("concept_tag_group_not_linked"),null,null);
               actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
               Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
               Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
               Assert.assertEquals(response.getStatusCode(),j);
               System.out.println("error : tag group does not link to organizer!");

        } else if (!listOfUnarchivedTagGroups.contains(ConfigReader.getProperty("tag_group_id"))) {
               responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_archived"),
                    ConfigReader.getProperty("concept_tag_group_archived"),null,null);
               actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
               Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
               Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
               Assert.assertEquals(response.getStatusCode(),j);
               System.out.println("error : tag group is archived!");

        } else if (listOfNamesNotArchived.contains(ConfigReader.getProperty("name"))) {
               responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_name_exists"),
                    ConfigReader.getProperty("concept"),null,null);
               actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
               Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
               Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
               Assert.assertEquals(response.getStatusCode(),j);
               System.out.println("error : name already exists!");
        } else {
                actualDataWithTagID = obj.readValue(response.asString(),TagAndTagGroupResponseDataPositive.class);
                Assert.assertFalse(actualDataWithTagID.getData().getId().toString().isEmpty());
                Assert.assertTrue(listOfUnarchivedTagNames.contains(ConfigReader.getProperty("name")));
                Assert.assertEquals(response.getStatusCode(),z);
        }
    }
}
