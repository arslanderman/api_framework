package stepdefinitions.audience_management_step_defs;

import audience_management_test_data.Headers;
import base_url_set_up.CoreBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;

public class RegresUserDataStepDefs extends CoreBaseURL {

    Response response;

    {
        coreSetUp();
        spec.pathParams("first","api","second","users","third",2);
    }
    @Given("user creates request for getting user data")
    public void user_creates_request_for_getting_user_data() {


    }
    @When("user sends get request for user data")
    public void user_sends_get_request_for_user_data() {
        response = given().spec(spec).headers(headers).get("/{first}/{second}/{third}");
        response.prettyPrint();
    }
    @Then("user validates user data")
    public void user_validates_user_data() {

    }

}
