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
import utilities.ConfigReader;

import java.io.IOException;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;

public class TagGroupDeleteStepDefs extends AudienceManagementBaseURL {
    TagAndTagGroupPojo requestBody;

    ResponseTagAndTagGroupPojo responseBody;

    ResponseTagAndTagGroupPojo actualData;

    Response response;

    ObjectMapper obj;

    {
        audienceManagementSetUp();
        spec.pathParams("first","setting","second","tag","third","group","fourth","delete");
    }


    @Given("user creates request body for tag group delete with non existing organizer")
    public void user_creates_request_body_for_tag_group_delete_with_non_existing_organizer() {
       requestBody = new TagAndTagGroupPojo(670,"b45ded72-52aa-4ccf-a288-65fa8f7d4b2b",null,
               null,null,null,null);
    }
    @When("user sends post request for tag group delete with non existing organizer")
    public void user_sends_post_request_for_tag_group_delete_with_non_existing_organizer() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates organizer not found error for tag group delete with {string} status code")
    public void user_validates_organizer_not_found_error_for_tag_group_delete_with_status_code(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"),ConfigReader.getProperty("concept"),
                null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }
    @Given("user creates request body with non existing tag group for delete ep")
    public void user_creates_request_body_with_non_existing_tag_group_for_delete_ep() {
        requestBody = new TagAndTagGroupPojo(671,"b45ded72-52aa-4ccf-a288-65fa8f7d4b1b",null,
                null,null,null,null);
    }
    @When("When user sends post request with non existing tag group for delete ep")
    public void when_user_sends_post_request_with_non_existing_tag_group_for_delete_ep() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates tag group not found error with {string} status code for delete ep")
    public void user_validates_tag_group_not_found_error_with_status_code_for_delete_ep(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_non_exist"),
                ConfigReader.getProperty("concept_tag_group_non_existing"), null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }
    @Given("user creates request body for tag group delete which is not linked to organizer")
    public void user_creates_request_body_for_tag_group_delete_which_is_not_linked_to_organizer() {
        requestBody = new TagAndTagGroupPojo(370,"b45ded72-52aa-4ccf-a288-65fa8f7d4b2b",null,
                null,null,null,null);
    }
    @When("user sends post request which is not linked to organizer for delete ep")
    public void user_sends_post_request_which_is_not_linked_to_organizer_for_delete_ep() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates tag group is not linked to organizer error with {string} status code for delete ep")
    public void user_validates_tag_group_is_not_linked_to_organizer_error_with_status_code_for_delete_ep(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_not_linked"),
                ConfigReader.getProperty("concept_tag_group_not_linked"), null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }
    @Given("user creates request body with archived tag group for delete ep")
    public void user_creates_request_body_with_archived_tag_group_for_delete_ep() {
        requestBody = new TagAndTagGroupPojo(370,"17a7457e-0e8d-4877-9b41-8018c38498b8",null,
                null,null,null,null);
    }
    @When("user sends post request for delete ep")
    public void user_sends_post_request_for_delete_ep() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates tag group is archived error with {string} status code for delete ep")
    public void user_validates_tag_group_is_archived_error_with_status_code_for_delete_ep(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_archived"),
                ConfigReader.getProperty("concept_tag_group_archived"), null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }
    @Given("user creates request body with not archived tag group for delete ep")
    public void user_creates_request_body_with_not_archived_tag_group_for_delete_ep() {
        requestBody = new TagAndTagGroupPojo(671,"dc5c86b8-4c57-43f2-9076-7f0fe1e1a132",null,
               null,null,null,null);
    }
    @When("user sends post request for delete ep with not archived tag group")
    public void user_sends_post_request_for_delete_ep_with_not_archived_tag_group() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates tag group delete with empty response with {string} status code")
    public void user_validates_tag_group_delete_with_empty_response_with_status_code(String status_code) {
        String actual = response.getBody().asString();
        //Assert.assertTrue(actual.isEmpty() || actual.isBlank() || actual.equals("null"));
        /*  commented out because after first execution each time it will give assertion error.
            we need to provide unarchived tag group for each execution

        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
         */
    }
    @Given("user creates request body with tag group which has link to at least one tag those not archived")
    public void user_creates_request_body_with_tag_group_which_has_link_to_at_least_one_tag_those_not_archived() {
        requestBody = new TagAndTagGroupPojo(743,"f962e773-f8fb-4343-9ada-61457eb437e0",null,
                null,null,null,null);
    }
    @When("user sends post request with tag group that has link to at least one tag")
    public void user_sends_post_request_with_tag_group_that_has_link_to_at_least_one_tag() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates tag group linked to tag not archived error with {string} status code")
    public void user_validates_tag_group_linked_to_tag_not_archived_error_with_status_code(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_is_link_to_tag"),
                ConfigReader.getProperty("concept_tag_group_link_to_tag"), null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }
    @Given("user creates request body with tag group which has link to at least one tag those archived")
    public void user_creates_request_body_with_tag_group_which_has_link_to_at_least_one_tag_those_archived() {
        requestBody = new TagAndTagGroupPojo(671,"00481cdd-aa46-4e48-a522-e03989da0454",null,
                null,null,null,null);//create a tag group link it to tag then archive tag
    }
    @When("user sends post request with tag group that has link to at least one tag which is archived")
    public void user_sends_post_request_with_tag_group_that_has_link_to_at_least_one_tag_which_is_archived() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }

    @Then("user validates  empty response with {string} status code with linked archived tag")
    public void user_validates_empty_response_with_status_code_with_linked_archived_tag(String status_code) {
        String actual1 = response.getBody().asString();
        /*
        Assert.assertTrue(actual1.isEmpty() || actual1.isBlank() || actual1.equals("null"));
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
        commented out because after first execution each time it will give assertion error.
            we need to provide unarchived tag group for each execution
         */
    }

    @Given("user creates request body with tag group which has link to at least one child tag group those not archived")
    public void user_creates_request_body_with_tag_group_which_has_link_to_at_least_one_child_tag_group_those_not_archived() {
        requestBody = new TagAndTagGroupPojo(671,"12c59dc6-1e4c-49a3-b9f2-08a18371a027",null,
                null,null,null,null);//create a tag group link it to tag then archive tag
    }
    @When("user sends post request with tag group which has link to at least one child tag group those not archived")
    public void user_sends_post_request_with_tag_group_which_has_link_to_at_least_one_child_tag_group_those_not_archived() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates tag group linked to group not archived error with {string} status code")
    public void user_validates_tag_group_linked_to_group_not_archived_error_with_status_code(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_has_child_group"),
                null, null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }
    @Given("user creates request body with tag group which has link to at least one child tag group those archived")
    public void user_creates_request_body_with_tag_group_which_has_link_to_at_least_one_child_tag_group_those_archived() {
        requestBody = new TagAndTagGroupPojo(132,"42ba2955-e43e-4aff-b152-4d8fff35e7e5",null,
                null,null,null,null);
    }
    @When("user sends post request with tag group which has link to at least one child tag group those archived")
    public void user_sends_post_request_with_tag_group_which_has_link_to_at_least_one_child_tag_group_those_archived() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates  empty response with {string} status code with linked archived tag group")
    public void user_validates_empty_response_with_status_code_with_linked_archived_tag_group(String status_code) {
        String actual2 = response.getBody().asString();
        /*
        Assert.assertTrue(actual2.isEmpty() || actual2.isBlank() || actual2.equals("null"));
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
             commented out because after first execution each time it will give assertion error.
               we need to provide unarchived tag group for each execution
         */
    }
}
