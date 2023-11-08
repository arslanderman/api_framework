package stepdefinitions.audience_management_step_defs;

import base_url_set_up.AudienceManagementBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import pojo.ResponseTagAndTagGroupPojo;
import pojo.TagAndTagGroupPojo;
import pojo.TagAndTagGroupResponseDataPositive;
import pojo.TagAndTagGroupsResponseId;
import utilities.ConfigReader;

import java.util.Map;

import static audience_management_test_data.Headers.headers;
import static audience_management_test_data.HeadersAudienceManagment.header;

import static io.restassured.RestAssured.*;

public class TagCreateStepDefs extends AudienceManagementBaseURL {
    Response response;
   // Map<String,String> headers;
    TagAndTagGroupPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    TagAndTagGroupsResponseId responseId;
    TagAndTagGroupResponseDataPositive responseData;
    TagAndTagGroupResponseDataPositive actualDataPositive;

    {
        audienceManagementSetUp();
        spec.pathParams("first","setting","second","tag","third","create");
    }


    @Given("user creates request body for tag creation with non existing organizer")
    public void user_creates_request_body_for_tag_creation_with_non_existing_organizer() {
        requestBody = new TagAndTagGroupPojo(670,null,"Luis",
                null,"navy",null,"fe265de8-63bb-46b9-952f-f9b18343732f");

    }
    @When("user sends post request for tag creation with non existing organizer")
    public void user_sends_post_request_for_tag_creation_with_non_existing_organizer() {
        response =  given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}");
        response.prettyPrint();
    }
    @Then("user validates organizer not found error for tag creation with {string} status code")
    public void user_validates_organizer_not_found_error_for_tag_creation_with_status_code(String string) {

        actualData = response.as(ResponseTagAndTagGroupPojo.class);
         responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"),
                 ConfigReader.getProperty("concept"),null,null);
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        response.then().assertThat().statusCode(404);
        Assert.assertEquals(response.getStatusCode(),404);


    }
    @Given("user creates request body for tag creation  with non existing tag group")
    public void user_creates_request_body_for_tag_creation_with_non_existing_tag_group() {
        requestBody = new TagAndTagGroupPojo(671,null,"Luis",
                null,"lime",null,"fe265de8-63bb-46b9-952f-f9b18343731f");
    }
    @When("user sends post request with non existing tag group")
    public void user_sends_post_request_with_non_existing_tag_group() {
        response =  given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}");
        response.prettyPrint();

    }
    @Then("user validates tag group not found error for tag creation with {string} status code")
    public void user_validates_tag_group_not_found_error_for_tag_creation_with_status_code(String string) {
        actualData = response.as(ResponseTagAndTagGroupPojo.class);
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_non_exist")
        ,ConfigReader.getProperty("concept_tag_group_non_existing"),null,null);
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());


    }
    @Given("user creates request body with tag group which is not linked to organizer")
    public void user_creates_request_body_with_tag_group_which_is_not_linked_to_organizer() {
        requestBody = new TagAndTagGroupPojo(743,null,"Luis",
                null,"lime",null,"fe265de8-63bb-46b9-952f-f9b18343732f");

    }
    @When("When user sends post request with tag group which is not linked to organizer")
    public void when_user_sends_post_request_with_tag_group_which_is_not_linked_to_organizer() {

        response =  given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}");
        response.prettyPrint();

    }
    @Then("user validates tag group is not linked to organizer error with {string} status code")
    public void user_validates_tag_group_is_not_linked_to_organizer_error_with_status_code(String string) {
        actualData = response.as(ResponseTagAndTagGroupPojo.class);
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_not_linked"),
                ConfigReader.getProperty("concept_tag_group_not_linked"),null,null);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());

    }
    @Given("user creates request body when tag group archived")
    public void user_creates_request_body_when_tag_group_archived() {
        requestBody = new TagAndTagGroupPojo(671,null,"sheila",
                null,"violet",null,"225a7306-6dbf-4a18-8869-f5779174e9cd");

    }
    @When("When user sends post request when tag group archived")
    public void when_user_sends_post_request_when_tag_group_archived() {

        response =  given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}");
        response.prettyPrint();
    }
    @Then("user validates tag group is archived error with {string} status code")
    public void user_validates_tag_group_is_archived_error_with_status_code(String string) {
        actualData = response.as(ResponseTagAndTagGroupPojo.class);
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_archived"),
                ConfigReader.getProperty("concept_tag_group_archived"),null,null);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());


    }
    @Given("user creates request body for tag creation with existing name")
    public void user_creates_request_body_for_tag_creation_with_existing_name() {
        requestBody = new TagAndTagGroupPojo(370,null,"core",
                null,"coral",null,"20663e28-423e-4865-9c5f-783e193e6d11");
    }
    @When("user sends post request for tag creation with existing name")
    public void user_sends_post_request_for_tag_creation_with_existing_name() {
     response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}");
     response.prettyPrint();
    }
    @Then("user validates name already exist for organizer error for tag creation with {string} status code")
    public void user_validates_name_already_exist_for_organizer_error_for_tag_creation_with_status_code(String string) {
        actualData = response.as(ResponseTagAndTagGroupPojo.class);
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_name_exists"),
                ConfigReader.getProperty("concept"),null,null);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());

    }
    @Given("user creates request body with archived name")
    public void user_creates_request_body_with_archived_name() {
        requestBody = new TagAndTagGroupPojo(671,null,"beaulieu",
                null,"coral",null,"823b60ae-08dd-4f42-adb1-3c8cfef751c0");
    }
    @When("user sends post request with archived name")
    public void user_sends_post_request_with_archived_name() {
      response =  given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}");
      response.prettyPrint();

    }
    @Then("user validates tag creation")
    public void user_validates_tag_creation() {

        actualDataPositive = response.as(TagAndTagGroupResponseDataPositive.class);
        responseId = new TagAndTagGroupsResponseId("76af7262-cd7d-4c39-97cd-85ea6f3eedea");
        responseData = new TagAndTagGroupResponseDataPositive(responseId);
        Assert.assertNotEquals(actualDataPositive.getData().getId(),responseData.getData().getId());
        Assert.assertFalse(actualDataPositive.getData().getId().toString().isEmpty());
        /*
        responseId = new TagAndTagGroupsResponseId("12345555");
        responseData = new TagAndTagGroupResponseDataPositive(responseId);
        Assert.assertFalse(actualDataPositive.getData().getId().toString().isEmpty());
         */

    }
    @Given("user creates request body for tag creation")
    public void user_creates_request_body_for_tag_creation() {
        requestBody = new TagAndTagGroupPojo(743,null,"france",
                null,"coral",null,"edcd9714-0d96-4dc9-8e27-d9654cdcf4e7");
    }
    @When("When user sends post request for tag creation")
    public void when_user_sends_post_request_for_tag_creation() {
        response =  given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}");
        response.prettyPrint();

    }

}


