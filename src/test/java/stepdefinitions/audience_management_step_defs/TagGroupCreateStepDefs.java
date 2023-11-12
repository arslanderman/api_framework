package stepdefinitions.audience_management_step_defs;

import base_url_set_up.CoreBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import utilities.ConfigReader;

import java.util.HashMap;
import java.util.Map;

import static audience_management_test_data.Headers.headers;
import static audience_management_test_data.OrganizerNotFoundResponse.organizerNotFound;
import static audience_management_test_data.ParentGroupNotLinkedToOrg.parentNotLinked;
import static audience_management_test_data.TagAndTagGroupTestData.tagAndTagGroups;

import static io.restassured.RestAssured.*;

public class TagGroupCreateStepDefs extends CoreBaseURL {
    Response response;
    Map<String,Object> requestBody;
    Map<String,Object> actualData;
    //Map<String,String> headers;

        //instance initialization block
     {
        coreSetUp();
        spec.pathParams("first","setting","second","tag","third","group","fourth","create");
     }



    @Given("user creates request body with non existing organizer")
    public void user_creates_request_body_with_non_existing_organizer() {

      requestBody =   tagAndTagGroups(133,null,"Mitrovic"
        ,null,null,null,null);
        System.out.println(requestBody);
        System.out.println(headers);

    }


    @When("user sends post request with non existing organizer")
    public void user_sends_post_request_with_non_existing_organizer() {

        //headers = header(ConfigReader.getProperty("ContentType"),ConfigReader.getProperty("host"));
     response=   given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}");
    response.prettyPrint();

    }

    @Then("user validates organizer not found error with \"{int}\"status code")
    public void user_validates_organizer_not_found_error_with_status_code(Integer int1) {

      Map<String,Object> actualData = response.as(HashMap.class);
        //JsonPath json = response.jsonPath();
        Map<String,String> expectedData = organizerNotFound(ConfigReader.getProperty("error_code"),
                ConfigReader.getProperty("concept"));
        System.out.println("expected data "+expectedData);
        Assert.assertEquals(expectedData.get("error_code"),actualData.get("error_code"));
        Assert.assertEquals(expectedData.get("concept"),actualData.get("concept"));
        Assert.assertEquals(response.getStatusCode(),404);
        //response.then().assertThat().statusCode(404);

    }

    @Given("user creates request body with existing name")
    public void user_creates_request_body_with_existing_name() {
         requestBody =   tagAndTagGroups(671,null,"Marko"
                ,"bd3ab3c8-f48e-40c8-bf16-ad9c02fa6950",null,null,null);

    }
    @When("user sends post request with existing name")
    public void user_sends_post_request_with_existing_name() {
        System.out.println("existinggggg "+requestBody);
        response= given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();

    }
    @Then("user validates name already exist for organizer error with {int} status code")
    public void user_validates_name_already_exist_for_organizer_error_with_status_code(Integer int1) {
        Map<String,Object> actualData = response.as(HashMap.class);
        Assert.assertTrue(actualData.get("message").toString().contains("Name")&&actualData.get("message")
                .toString().contains("already")&&actualData.get("message").toString().contains("exists"));
       // Assert.assertTrue(actualData.get("message"));
        Assert.assertEquals(response.getStatusCode(),404);

    }
    @Given("user creates request body with non existing parent group")
    public void user_creates_request_body_with_non_existing_parent_group() {
        requestBody =   tagAndTagGroups(671,null,"Markos"
                ,"bd3ab3c8-f48e-40c8-bf16-ad9c02fa6951",null,null,null);

    }
    @When("When user sends post request with non existing parent group")
    public void when_user_sends_post_request_with_non_existing_parent_group() {

        response= given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();

    }
    @Then("user validates group not found error with {int} status code")
    public void user_validates_group_not_found_error_with_status_code(Integer int1) {
        actualData = response.as(HashMap.class);
        Assert.assertEquals(ConfigReader.getProperty("error_code_parent_group"),actualData.get("error_code").toString());
        Assert.assertEquals(response.getStatusCode(),404);
    }
    @Given("user creates request body with parent group is not linked to organizer")
    public void user_creates_request_body_with_parent_group_is_not_linked_to_organizer() {
        requestBody =   tagAndTagGroups(132,null,"Marko"
                ,"bd3ab3c8-f48e-40c8-bf16-ad9c02fa6950",null,null,null);

    }
    @When("When user sends post request with parent group is not linked to organizer")
    public void when_user_sends_post_request_with_parent_group_is_not_linked_to_organizer() {

        response= given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();

    }
    @Then("user validates parent group is not linked to organizer error with {int} status code")
    public void user_validates_parent_group_is_not_linked_to_organizer_error_with_status_code(Integer int1) {
      actualData = response.as(HashMap.class);
       Map<String,String> expectedData = parentNotLinked("AUDIENCE_MANAGEMENT_GROUP_PARENT_NOT_LINKED");
        Assert.assertEquals(actualData.get("error_code"),expectedData.get("error_code"));
        Assert.assertEquals(response.getStatusCode(),409);

    }
    @Given("user creates request body when parent group archived")
    public void user_creates_request_body_when_parent_group_archived() {
        requestBody =   tagAndTagGroups(743,null,"Datome"
                ,"bd3ab3c8-f48e-40c8-bf16-ad9c02fa6950",null,null,null);

    }
    @When("When user sends post request when parent group archived")
    public void when_user_sends_post_request_when_parent_group_archived() {

        response= given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();

    }
    @Then("user validates parent group is archived error with {int} status code")
    public void user_validates_parent_group_is_archived_error_with_status_code(Integer int1) {
        actualData = response.as(HashMap.class);
        System.out.println("actual data "+actualData);
       Assert.assertFalse(((Map)actualData.get("data")).get("id").toString().isEmpty());
        System.out.println("id of the tag group: "+((Map)actualData.get("data")).get("id"));
       // System.out.println("actual id "+((Map)actualData.get("data")).get("id"));

    }
    @Given("user creates request body for tag group creation")
    public void user_creates_request_body_for_tag_group_creation() {
        requestBody = tagAndTagGroups(370,null,"Doncic","436c364a-526a-4000-a367-088acae76711"
        ,null,null,null);

    }
    @When("When user sends post request for tag group creation")
    public void when_user_sends_post_request_for_tag_group_creation() {

      Response response =  given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}");
        System.out.println("donciccccc "+requestBody);
    }
    @Then("user validates tag group creation")
    public void user_validates_tag_group_creation() {
        actualData = response.as(HashMap.class);
        Assert.assertFalse(((Map)actualData.get("data")).get("id").toString().isEmpty());
        System.out.println("id of the tag group: "+((Map)actualData.get("data")).get("id"));
    }
    @Given("user creates request body without parent id")
    public void user_creates_request_body_without_parent_id() {
        requestBody = tagAndTagGroups(132,null,"Belinelli",null
                ,null,null,null);

    }
    @When("When user sends post request without parent id")
    public void when_user_sends_post_request_without_parent_id() {

        Response response =  given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}");
        System.out.println("olympiakos "+requestBody);
        actualData= response.as(HashMap.class);

    }
    @Then("user validates tag group creation without parent id")
    public void user_validates_tag_group_creation_without_parent_id() {
        System.out.println("actual data "+actualData);
        Assert.assertFalse(((Map)actualData.get("data")).get("id").toString().isEmpty());
        System.out.println("id of the tag group: "+((Map)actualData.get("data")).get("id"));
    }

}
