package stepdefinitions.audience_management_step_defs;

import audience_management_test_data.Headers;
import base_url_set_up.AudienceManagementBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import pojo.FindAllListPojo;
import pojo.ResponseTagAndTagGroupPojo;
import pojo.TagAndTagGroupPojo;
import utilities.ConfigReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static audience_management_test_data.Headers.headers;
import static audience_management_test_data.TagAndTagGroupTestData.tagAndTagGroups;
import static io.restassured.RestAssured.*;
import static utilities.TxtWriter.saveDataTagGroups;

public class TagGroupFindByOrganizerId extends AudienceManagementBaseURL {

    {
        audienceManagementSetUp();
        spec.pathParams("first","setting","second","tag","third","group","fourth","find-by-organizer-id");
    }
    TagAndTagGroupPojo requestBody;
    Response response;
    //TagAndTagGroupPojo responseData;
    ResponseTagAndTagGroupPojo responseBody;
    Map<String,Object> responseBody1;
    ResponseTagAndTagGroupPojo actualData;
    FindAllListPojo actual;
    FindAllListPojo expected;
    FindAllListPojo expected1;
    TagAndTagGroupPojo expectedpojo;
    List<Object> name;

    //FindAllListPojo actual1;

    ObjectMapper obj;


    @Given("user creates request body for listing all tag groups")
    public void user_creates_request_body_for_listing_all_tag_groups() {
        requestBody = new TagAndTagGroupPojo(670,null,null,null,null,null,null);

    }
    @When("user sends post request for listing tag groups with non existing organizer")
    public void user_sends_post_request_for_listing_tag_groups_with_non_existing_organizer() {
     response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");
     response.prettyPrint(); //tag needs to be changed from hooks for header

    }
    @Then("user validates organizer not found error for listing tag groups")
    public void user_validates_organizer_not_found_error_for_listing_tag_groups() throws IOException {
        responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"),ConfigReader.getProperty("concept"),null,null);

        System.out.println("response body for assertion "+responseBody);
        obj = new ObjectMapper();
        actualData =  obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
        System.out.println("actual Data "+actualData);
        Assert.assertEquals(actualData.getError_code(),responseBody.getError_code());
        Assert.assertEquals(actualData.getConcept(),responseBody.getConcept());
    }

    @Then("user verifies http status code {string} for non existing organizer")
    public void user_verifies_http_status_code_for_non_existing_organizer(String status_code) {
        int i = Integer.parseInt(status_code);
       Assert.assertEquals(response.getStatusCode(),i);

    }

    @Given("user creates request body for checking archived groups")
    public void user_creates_request_body_for_checking_archived_groups() {
        requestBody = new TagAndTagGroupPojo(743,null,null,null,null,null,null);
    }
    @When("user send post request to list all tag groups")
    public void user_send_post_request_to_list_all_tag_groups() {
       response = given().spec(spec).headers(headers).body(requestBody).when().post("/{first}/{second}/{third}/{fourth}");

    }
    @Then("user validates all tag group those not archived")
    public void user_validates_all_tag_group_those_not_archived() throws IOException {
        //responseData = new TagAndTagGroupPojo(null,"99735555-695a-4f26-9bf5-2f2402afd226","ivkovic",
            //  "c5d0e57e-8dd4-425e-81b4-ef67a01dc7f5",null,null,null);
        responseBody1 = tagAndTagGroups(null,"f962e773-f8fb-4343-9ada-61457eb437e0","Podolski",
               "35fe62f5-c7f4-4b28-9db9-dbf4cbfc3133",null,null,null);
        List<Object> actualList = new ArrayList<>();
        actualList.add(responseBody1);
        expected = new FindAllListPojo(actualList);
        expectedpojo = new TagAndTagGroupPojo(null,"99735555-695a-4f26-9bf5-2f2402afd226",
                "ivkovic","c5d0e57e-8dd4-425e-81b4-ef67a01dc7f5",null,null,null);
        List<Object> objectList = new ArrayList<>();
        objectList.add(expectedpojo);
        expected1 = new FindAllListPojo(objectList);
        System.out.println("expected data "+expected);
        System.out.println("expected dataaaaa "+expected1);

        obj = new ObjectMapper();
        actual = obj.readValue(response.asString(), FindAllListPojo.class); // ask here

        System.out.println("actual data "+actual);
       // System.out.println(expected.getData().get(0));
        //System.out.println(actual.getData().get(2));
        boolean flag;
        for(int i = 0; i < actual.getData().size(); i++ ){
            Map<String,String> map = new HashMap<>((Map) actual.getData().get(i));
            Map<String,String> map1 = new HashMap<>((Map) expected.getData().get(0));
            name = new ArrayList<>();
            /*
            for(int j = 0; j < actual.getData().size(); j++){
                name.add(map.get("name"));
            }

             */

            System.out.println("name " + (i+1) +" : "+map.get("name"));
            if(map.get("name").equals(map1.get("name"))){
                flag = true;
                Assert.assertTrue(flag);
                //Assert.assertTrue(map.get("name"));
            }

        }
        for(Object w : actual.getData()){
            Map<String,String> map2 = new HashMap<>((Map) w);
            name.add(map2.get("name"));
        }
        System.out.println(name);


       // saveDataTagGroups(actual);

    }
    @Then("user validates all tag groups those archived")
    public void user_validates_all_tag_groups_those_archived() {

    }

    @Then("user verifies http status code {string}")
    public void user_verifies_http_status_code(String string) {

    }



}
