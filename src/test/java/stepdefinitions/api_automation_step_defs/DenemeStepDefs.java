package stepdefinitions.api_automation_step_defs;

import api_automation_test_data.Headers;
import base_url_set_up.BaseURLForApiAutomation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import pojo.api_automation_pojo.DenemeOuterPojo;
import utilities.ConfigReader;

import java.io.IOException;

import static api_automation_test_data.Headers.headers;
import static io.restassured.RestAssured.*;

public class DenemeStepDefs extends BaseURLForApiAutomation {

    DenemeOuterPojo actualData;
    Response response;
    ObjectMapper obj = new ObjectMapper();

    {
        automationSetUp();
        spec.pathParams("first","booking","second","1");

    }



    @Given("user creates request for fetching users")
    public void user_creates_request_for_fetching_users() {

    }
    @When("user sends get request for these new users")
    public void user_sends_get_request_for_these_new_users() {
        response = given().spec(spec).headers(headers).get("/{first}/{second}");
        response.prettyPrint();
    }
    @Then("user validates user data for users with a {string} status code")
    public void user_validates_user_data_for_users_with_a_status_code(String status_code) throws IOException {

        actualData = obj.readValue(response.asString(), DenemeOuterPojo.class);
        System.out.println("actual data "+actualData);
        Assert.assertEquals(response.getStatusCode(),Integer.parseInt(status_code));
        Assert.assertTrue(actualData.getFirstname().contains("Su"));
        //Assert.assertEquals(actualData.getFirstname(), ConfigReader.getProperty("firstname"));
        System.out.println("check in "+actualData.getBookingdates().getCheckin());
        System.out.println("checkout "+actualData.getBookingdates().getCheckout());

    }

}
