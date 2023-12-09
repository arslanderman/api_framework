package stepdefinitions.api_automation_step_defs;

import base_url_set_up.BaseURLForApiAutomation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import pojo.api_automation_pojo.ReqresSingleUserResponsePojo;
import utilities.ConfigReader;

import java.io.IOException;

import static api_automation_test_data.Headers.headers;
import static io.restassured.RestAssured.*;

public class RegresUserDataStepDefs extends BaseURLForApiAutomation {

    ReqresSingleUserResponsePojo actualData;
    Response response;
    ObjectMapper obj =new ObjectMapper();

    {
        automationSetUp();
        spec.pathParams("first","api","second","users","third",2);
    }
    @Given("user creates request for getting user data")
    public void user_creates_request_for_getting_user_data() {


    }
    @When("user sends get request for user data")
    public void user_sends_get_request_for_user_data() {
        response = given().spec(spec).headers(headers).get("/{first}/{second}/{third}");
        //response.prettyPrint();
    }

    @Then("user validates user data with a {string} status code")
    public void user_validates_user_data_with_a_status_code(String statuscode) throws IOException {
        actualData = obj.readValue(response.asString(), ReqresSingleUserResponsePojo.class);
        System.out.println("actual data  "+actualData);
        Assert.assertEquals(response.getStatusCode(),Integer.parseInt(statuscode));
        Assert.assertEquals(actualData.getData().getId().toString(), ConfigReader.getProperty("id"));
    }

}
