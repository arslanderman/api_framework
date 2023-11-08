package stepdefinitions.audience_management_step_defs;

import base_url_set_up.AudienceManagementBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import pojo.FindAllListPojo;
import pojo.TagAndTagGroupPojo;
import utilities.ConfigReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;

public class TagEntityTypeFindAll extends AudienceManagementBaseURL {
    TagAndTagGroupPojo requestBody;
    Response response;
    FindAllListPojo responseBody;
    ObjectMapper obj;




    {
        audienceManagementSetUp();
        spec.pathParams("first","setting","second","tag","third","entity","fourth","find-all-types");
    }

    @Given("user enters empty data")
    public void user_enters_empty_data() {
       requestBody = new TagAndTagGroupPojo(null,null,null,null,null,null,null);
    }
    @When("user sends post request for tag entity type find all end point")
    public void user_sends_post_request_for_tag_entity_type_find_all_end_point() {
       response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
        //response.prettyPrint();
    }
    @Then("user validates entity listed types")
    public void user_validates_entity_listed_types() throws IOException {
        List<Object> expectedresponse = new ArrayList<>();
        expectedresponse.add(ConfigReader.getProperty("entity_1"));
        expectedresponse.add(ConfigReader.getProperty("entity_2"));
        expectedresponse.add(ConfigReader.getProperty("entity_3"));
        expectedresponse.add(ConfigReader.getProperty("entity_4"));

        responseBody = new FindAllListPojo(expectedresponse);
        System.out.println("expected response "+responseBody);
        //System.out.println(responseBody.getData().get(0));
        for(int i =0; i<expectedresponse.size(); i++ ){
            System.out.println(responseBody.getData().get(i));
        }
        obj = new ObjectMapper();
        FindAllListPojo actualData = obj.readValue(response.asString(), FindAllListPojo.class);
        System.out.println("actual data "+actualData);
        Assert.assertTrue(actualData.getData().containsAll(expectedresponse));


    }
}


