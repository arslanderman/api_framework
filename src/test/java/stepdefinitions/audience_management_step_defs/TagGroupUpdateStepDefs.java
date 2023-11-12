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
import utilities.ConfigReader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasValue;
import static utilities.DBUtils.createConnection;
import static utilities.DBUtils.getColumnData;

public class TagGroupUpdateStepDefs extends CoreBaseURL {

    TagAndTagGroupPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    Response response;
    ObjectMapper obj;


    {
        coreSetUp();
        spec.pathParams("first","setting","second","tag","third","group","fourth","update");
    }


    @Given("user creates request body for tag group update with non existing organizer")
    public void user_creates_request_body_for_tag_group_update_with_non_existing_organizer() {
        requestBody = new TagAndTagGroupPojo(670,"05dd3509-e225-4e2a-92d8-605f841353cc","Ahmedi",
                "0b9cfb35-1aba-4e0a-abdd-2a51bc944567",null,null,null);
    }
    @When("user sends post request for tag group update with non existing organizer")
    public void user_sends_post_request_for_tag_group_update_with_non_existing_organizer() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        //we need to change tag from hooks class
        response.prettyPrint();
    }
    @Then("user validates organizer not found error for tag group update with {string} status code")
    public void user_validates_organizer_not_found_error_for_tag_group_update_with_status_code(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"),ConfigReader.getProperty("concept"),
                null,null);

        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        System.out.println(actualData);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body with non existing tag group")
    public void user_creates_request_body_with_non_existing_tag_group() {
        requestBody = new TagAndTagGroupPojo(671,"05dd3509-e225-4e2a-92d8-605f841354cc","blossom",
                "0b9cfb35-1aba-4e0a-abdd-2a51bc944567",null,null,null);
    }
    @When("When user sends post request with non existing tag group")
    public void when_user_sends_post_request_with_non_existing_tag_group() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();
    }
    @Then("user validates tag group not found error with {string} status code")
    public void user_validates_tag_group_not_found_error_with_status_code(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_non_exist"),
                ConfigReader.getProperty("concept_tag_group_non_existing"), null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        System.out.println(actualData);
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body for tag group update which is not linked to organizer")
    public void user_creates_request_body_for_tag_group_update_which_is_not_linked_to_organizer() throws SQLException {
        createConnection();
        String query = "select * from core_tag_group where organizer_id = '132'";
        List<Object> data = getColumnData(query, "name");
        System.out.println("names for 132 "+data);
        String name = "Lucas";
        if (data.contains(name)) {
            System.out.println("name already exist for this organizer please provide another name");
        } else {
            requestBody = new TagAndTagGroupPojo(132, "05dd3509-e225-4e2a-92d8-605f841353cc", name,
                    "0b9cfb35-1aba-4e0a-abdd-2a51bc944567", null, null, null);
        }
    }
    @When("When user sends post request with tag group update request body is not linked to organizer")
    public void when_user_sends_post_request_with_tag_group_update_request_body_is_not_linked_to_organizer() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();
    }
    @Then("user validates tag group is not linked to organizer error with {string} status code for update ep")
    public void user_validates_tag_group_is_not_linked_to_organizer_error_with_status_code_for_update_ep(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_not_linked"),
                ConfigReader.getProperty("concept_tag_group_not_linked"), null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body for with archived tag group")
    public void user_creates_request_body_for_with_archived_tag_group() {
        requestBody = new TagAndTagGroupPojo(370,"17a7457e-0e8d-4877-9b41-8018c38498b8","blossom",
                null,null,null,null);
    }
    @When("user sends post request with archived tag group")
    public void user_sends_post_request_with_archived_tag_group() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates tag group is archived error with {string} status code for update ep")
    public void user_validates_tag_group_is_archived_error_with_status_code_for_update_ep(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_archived_group"),
                ConfigReader.getProperty("concept_tag_group_non_existing"), null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body with existing name for organizer")
    public void user_creates_request_body_with_existing_name_for_organizer() {
        requestBody = new TagAndTagGroupPojo(671,"99735555-695a-4f26-9bf5-2f2402afd226","Pheobie",
                "c5d0e57e-8dd4-425e-81b4-ef67a01dc7f5",null,null,null);
    }
    @When("user sends post request for tag group update with existing name")
    public void user_sends_post_request_for_tag_group_update_with_existing_name() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();
    }
    @Then("user validates name already exist error for update ep with {string} status code")
    public void user_validates_name_already_exist_error_for_update_ep_with_status_code(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_name_exists_group"),
                ConfigReader.getProperty("concept"), ConfigReader.getProperty("message"),null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getMessage(),responseBody.getMessage());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body with archived name for organizer")
    public void user_creates_request_body_with_archived_name_for_organizer() {
        requestBody = new TagAndTagGroupPojo(671,"05dd3509-e225-4e2a-92d8-605f841353cc","Bogdanovic",
                "0b9cfb35-1aba-4e0a-abdd-2a51bc944567",null,null,null);
    }
    @When("user sends post request for tag group update with archived name")
    public void user_sends_post_request_for_tag_group_update_with_archived_name() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        response.prettyPrint();
    }
    @Then("user validates tag group update with empty response with {string} status code")
    public void user_validates_tag_group_update_with_empty_response_with_status_code(String status_code) throws IOException {
        requestBody = new TagAndTagGroupPojo(671,"05dd3509-e225-4e2a-92d8-605f841353cc","Bogdanovic",
               "0b9cfb35-1aba-4e0a-abdd-2a51bc944567",null,null,null);
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        String rspnseBody = response.getBody().asString();
        Assert.assertTrue(rspnseBody.isEmpty() || rspnseBody.isBlank() || rspnseBody.equals("null"));
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body for tag group update without parent_id")
    public void user_creates_request_body_for_tag_group_update_without_parent_id() {
        requestBody = new TagAndTagGroupPojo(671,"99735555-695a-4f26-9bf5-2f2402afd226","Ivko",
              null ,null,null,null);
    }
    @When("user sends post request for tag group update without parent_id")
    public void user_sends_post_request_for_tag_group_update_without_parent_id() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }

    @Given("user creates request body with same parent_id and tag_group_id")
    public void user_creates_request_body_with_same_parent_id_and_tag_group_id() {
        requestBody = new TagAndTagGroupPojo(671,"99735555-695a-4f26-9bf5-2f2402afd226","Ivko",
                "99735555-695a-4f26-9bf5-2f2402afd226" ,null,null,null);
    }
    @When("user sends post request for tag group update with same parent_id and tag_group_id")
    public void user_sends_post_request_for_tag_group_update_with_same_parent_id_and_tag_group_id() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates parent_id and id can not be same error with {string} status code")
    public void user_validates_parent_id_and_id_can_not_be_same_error_with_status_code(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_same_parent_id&id"),null,
                null,null);
        obj = new ObjectMapper();
        actualData = obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body with non existing parent_id for uppate ep")
    public void user_creates_request_body_with_non_existing_parent_id_for_uppate_ep() {
        requestBody = new TagAndTagGroupPojo(671,"99735555-695a-4f26-9bf5-2f2402afd226","Ivko",
                "d147fed3-3de5-46c4-b03e-a6303ac06ed1" ,null,null,null);
    }
    @When("user sends post request for tag group update with non existing parent_id")
    public void user_sends_post_request_for_tag_group_update_with_non_existing_parent_id() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates parent group not found error with {string} status code")
    public void user_validates_parent_group_not_found_error_with_status_code(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_non_exist"),
                ConfigReader.getProperty("concept_tag_group_non_existing"),null,null);
        obj = new ObjectMapper();
        actualData= obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body with parent_id which has not link to organizer for uppate ep")
    public void user_creates_request_body_with_parent_id_which_has_not_link_to_organizer_for_uppate_ep() {
        requestBody = new TagAndTagGroupPojo(671,"99735555-695a-4f26-9bf5-2f2402afd226","Ivko",
                "f962e773-f8fb-4343-9ada-61457eb437e0" ,null,null,null);
    }
    @When("user sends post request for tag group update with parent_id which has not link to organizer")
    public void user_sends_post_request_for_tag_group_update_with_parent_id_which_has_not_link_to_organizer() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates parent group is not link to organizer error with {string} status code for update ep")
    public void user_validates_parent_group_is_not_link_to_organizer_error_with_status_code_for_update_ep(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_parent_id_not_linked"),null,
                null,null);
        obj = new ObjectMapper();
        actualData= obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body for with archived parent group for update ep")
    public void user_creates_request_body_for_with_archived_parent_group_for_update_ep() {
        requestBody = new TagAndTagGroupPojo(671,"99735555-695a-4f26-9bf5-2f2402afd226","Ivko",
                "9c9e1bf6-fe89-4ea7-8e3c-e4beb89e86a5" ,null,null,null);
    }
    @When("user sends post request with archived parent group for update ep")
    public void user_sends_post_request_with_archived_parent_group_for_update_ep() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
    @Then("user validates parent group is archived error with {string} status code for update ep")
    public void user_validates_parent_group_is_archived_error_with_status_code_for_update_ep(String status_code) throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_tag_group_archived"),
                ConfigReader.getProperty("concept_tag_group_archived"),null,null);
        obj = new ObjectMapper();
        actualData= obj.readValue(response.asString(),ResponseTagAndTagGroupPojo.class);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
        int i = Integer.parseInt(status_code);
        Assert.assertEquals(response.getStatusCode(),i);
    }

    @Given("user creates request body for update ep")
    public void user_creates_request_body_for_update_ep() {
        requestBody = new TagAndTagGroupPojo(132," eb190de8-fd3a-4e77-94d3-9e723fac2fa5","Shane Larkin",
                " 8073b6dc-862b-40e0-99a9-f37094126d3b" ,null,null,null);
    }
    @When("user sends post request for tag group update")
    public void user_sends_post_request_for_tag_group_update() {
        response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
    }
}
