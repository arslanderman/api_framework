package stepdefinitions.api_automation_step_defs;


import base_url_set_up.BaseURLForApiAutomation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import pojo.api_automation_pojo.DummyJsonRequestForTokenPojo;
import pojo.api_automation_pojo.DummyJsonResponsePojo;
import utilities.ConfigReader;

import java.io.IOException;

import static api_automation_test_data.Headers.headers;
import static io.restassured.RestAssured.*;

public class DummyJsonPostRequestForToken extends BaseURLForApiAutomation {
    DummyJsonRequestForTokenPojo requestBody;
    DummyJsonResponsePojo actualData;
    Response response;
    ObjectMapper obj = new ObjectMapper();

    {
        automationSetUp();
        spec.pathParams("first","auth","second","login");
    }

    @Given("user creates request body for getting token")
    public void user_creates_request_body_for_getting_token() {
        requestBody = new DummyJsonRequestForTokenPojo(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));
        System.out.println("request body "+requestBody);

    }
    @When("user sends get request for getting dummy json token")
    public void user_sends_get_request_for_getting_dummy_json_token() {
        response =  given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}");
        response.prettyPrint();
    }
    @Then("user validates token and other data with a {string} status code")
    public void user_validates_token_and_other_data_with_a_status_code(String status_code) throws IOException {

        actualData = obj.readValue(response.asString(), DummyJsonResponsePojo.class);
        System.out.println("actual data "+actualData);
        Assert.assertEquals(response.getStatusCode(),Integer.parseInt(status_code));
        String token = actualData.getToken();
        System.out.println("token "+token);
        Assert.assertEquals(actualData.getEmail(),ConfigReader.getProperty("email"));

    }

}
