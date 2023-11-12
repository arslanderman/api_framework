package stepdefinitions.audience_management_step_defs;

import base_url_set_up.CoreBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import pojo.audience_pojo.ResponseTagAndTagGroupPojo;
import pojo.audience_pojo.TagAndTagGroupPojo;
import pojo.audience_pojo.TagAndTagGroupResponseDataPositive;
import utilities.ConfigReader;

import java.io.EOFException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static utilities.DBUtils.*;

public class TagUpdateActivateFieldStepDefs extends CoreBaseURL {

    TagAndTagGroupPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    TagAndTagGroupResponseDataPositive actualDataWithTag_or_TagGroup_ID;
    Response response;
    ObjectMapper obj = new ObjectMapper();
    int i = Integer.parseInt(ConfigReader.getProperty("org_id"));
    int y = Integer.parseInt(ConfigReader.getProperty("status_a"));
    int j = Integer.parseInt(ConfigReader.getProperty("status_b"));
    int x = Integer.parseInt(ConfigReader.getProperty("status_d"));


    {
        coreSetUp();
        spec.pathParams("first", "setting", "second", "tag", "third", "update-active-field");

    }


    @Given("user creates request for updating active field of tag")
    public void user_creates_request_for_updating_active_field_of_tag() {
        requestBody = new TagAndTagGroupPojo(i,ConfigReader.getProperty("tag_id"),null,null,
                null,false,null);
        System.out.println(requestBody);
    }
    @When("user sends post request for updating active field of tag")
    public void user_sends_post_request_for_updating_active_field_of_tag() {
        response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}");
        response.prettyPrint();
    }
    @Then("user validates updating active field")
    public void user_validates_updating_active_field() throws IOException, SQLException {
        createConnection();
        List<Integer> listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"),"id_organizer");
        List<Object> listOfTags = getColumnData(ConfigReader.getProperty("query_existing_tags"),"id");
        List<Object> listOfTagsWithOrg = getColumnData(ConfigReader.getProperty("query_tags_org_id"),"id");
        List<Object> listOfArchivedTags = getColumnData(ConfigReader.getProperty("query_archived_tags"), "id");
        if(!listOfOrganizers.contains(Integer.parseInt(ConfigReader.getProperty("org_id")))){
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"), ConfigReader.getProperty("concept"),
                    null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(),y);
            System.out.println("error : organizer does not exist!");
        }else if (!listOfTags.contains(ConfigReader.getProperty("tag_id"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_non_existing"),
                    ConfigReader.getProperty("concept_tag_non_existing"), null,null);
            actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
            Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(),y);
            System.out.println("error : tag group does not exist!");
        }else if (!listOfTagsWithOrg.contains(ConfigReader.getProperty("tag_id"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_not_linked_org"),
                    ConfigReader.getProperty("concept_tag_not_linked_org"),null,null);
            actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
            Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(),j);
            System.out.println("error : tag group does not link to organizer!");
        }else if (listOfArchivedTags.contains(ConfigReader.getProperty("tag_id"))){
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_archived_tag"),
                    ConfigReader.getProperty("concept_archived_tag"), null,null);
            actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
            Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(),j);
            System.out.println("error : tag has been archived!");
        }else {
            try {
                actualDataWithTag_or_TagGroup_ID = obj.readValue(response.asString(), TagAndTagGroupResponseDataPositive.class);
                String id = actualDataWithTag_or_TagGroup_ID.getData().getId();
                Assert.assertTrue(id == null || id.isEmpty());
            } catch (EOFException e) {
                System.out.println("The response has no content, update has been made!");
                Assert.assertEquals(response.getStatusCode(), x);
            }
        }
    }
}
