package stepdefinitions;

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

import java.io.EOFException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static utilities.DBUtils.*;

public class TagDeleteStepDefs extends AudienceManagementBaseURL {

    TagAndTagGroupPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    TagAndTagGroupResponseDataPositive actualDataWithTag_or_TagGroup_ID;
    Response response;
    ObjectMapper obj = new ObjectMapper();
    int i = Integer.parseInt(ConfigReader.getProperty("org_id"));
    int y = Integer.parseInt(ConfigReader.getProperty("status_a"));
    int j = Integer.parseInt(ConfigReader.getProperty("status_b"));
    int z = Integer.parseInt(ConfigReader.getProperty("status_d"));

    {
       audienceManagementSetUp();
        spec.pathParams("first", "setting", "second", "tag", "third", "delete");
    }

    @Given("user creates request body for tag delete")
    public void user_creates_request_body_for_tag_delete() {
        requestBody = new TagAndTagGroupPojo(i,ConfigReader.getProperty("tag_id"),null,null,null,
                null,null);
    }
    @When("user sends post request for tag delete")
    public void user_sends_post_request_for_tag_delete() {
        //response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}");
    }
    @Then("user validates tag deletion")
    public void user_validates_tag_deletion() throws SQLException, IOException {
        createConnection();
        List<Integer> listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"), "id_organizer");
        List<Object> listOfTags = getColumnData(ConfigReader.getProperty("query_existing_tags"), "id");
        List<Object> listOfTagsForOrg = getColumnData(ConfigReader.getProperty("query_existing_tags_for_organizer"), "id");
        List<Object> listOfArchivedTags = getColumnData(ConfigReader.getProperty("query_archived_tags"), "id");
        List<Object> listOfTagsLinkToRuleWithTagsAdded = getColumnData(ConfigReader.getProperty("query_tag_link_to_unarchived_rule"), "tags_added");
        List<Object> listOfTagsLinkToRuleWithTagsRemoved = getColumnData(ConfigReader.getProperty("query_tag_link_to_unarchived_rule"), "tags_removed");
        List<Object> listOfTagsLinkToUnarchivedEntity = getColumnData(ConfigReader.getProperty("query_tag_link_to_unarchived_entity"), "tag_id");
        response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}");
        List<String> activeColumn = getColumnDataAsString(ConfigReader.getProperty("query_assert_deletion"), "active");
        List<String> archivedColumn = getColumnDataAsString(ConfigReader.getProperty("query_assert_deletion1"), "archived_at");
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
        } else if (listOfTagsLinkToRuleWithTagsAdded.contains(ConfigReader.getProperty("tag_id"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_link_to_rule"),
                    null, null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(), j);
            System.out.println("error : tag has active link to a rule!");
        } else if (listOfTagsLinkToRuleWithTagsRemoved.contains(ConfigReader.getProperty("tag_id"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_link_to_rule"),
                    null, null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(), j);
            System.out.println("error : tag has active link to a rule!");
        } else if (listOfTagsLinkToUnarchivedEntity.contains(ConfigReader.getProperty("tag_id"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_link_to_entity"),
                    null, null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(), j);
            System.out.println("error : tag has active link to a entity!");
        }else {
            try {
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                String id = actualDataWithTag_or_TagGroup_ID.getData().getId();
                Assert.assertTrue(id == null || id.isEmpty());
            } catch (EOFException e) {
                Assert.assertTrue(activeColumn.contains(ConfigReader.getProperty("activee")));
                Assert.assertFalse(archivedColumn.isEmpty());
                Assert.assertEquals(response.getStatusCode(), z);
                System.out.println("tag has been succesfully deleted");
            }
        }
    }
}
