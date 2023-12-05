package stepdefinitions.subscriptions_management_step_defs;

import audience_management_test_data.Headers;
import base_url_set_up.CoreBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import pojo.audience_pojo.ResponseTagAndTagGroupPojo;
import pojo.subscription_pojo.SubscriptionCreatePojo;
import pojo.subscription_pojo.SubscriptionDeleteRequestPojo;
import utilities.ConfigReader;

import java.io.IOException;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static utilities.DBUtils.*;

public class SubscriptionUnarchiveStepDefs extends CoreBaseURL {

    SubscriptionDeleteRequestPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    SubscriptionCreatePojo actualDataWithSubscriptionId;
    Response response;
    ObjectMapper obj = new ObjectMapper();
    List<Integer> listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"), "id_organizer");
    List<String > listOfSubscriptionId = getColumnDataAsString(ConfigReader.getProperty("query_subscription_setting_id"), "id");
    List<String > listOfSubscriptionIdWithOrg = getColumnDataAsString(ConfigReader.getProperty("query_subscription_id_link_to_org"), "id");
    List<Object > listOfArchivedAtField = getColumnData(ConfigReader.getProperty("query_subscription_archived"), "archived_at");
    int i = Integer.parseInt(ConfigReader.getProperty("org_id"));

    {
        coreSetUp();
        spec.pathParams("first","organizer","second", ConfigReader.getProperty("org_id"),"third","setting",
                "fourth","subscription","fifth","unarchive");
    }

    @Given("user creates request body for subscription setting unarchive")
    public void user_creates_request_body_for_subscription_setting_unarchive() {
        requestBody = new SubscriptionDeleteRequestPojo(ConfigReader.getProperty("subscription_setting_id"));

    }
    @When("user sends post request for subscription setting unarchive")
    public void user_sends_post_request_for_subscription_setting_unarchive() {
        response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}/{fifth}");
    }

    @Then("user validates organizer not found error with {string} status code for subscription unarchive")
    public void user_validates_organizer_not_found_error_with_status_code_for_subscription_unarchive(String status_code_a) throws IOException {
       if(!listOfOrganizers.contains(i)) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"), ConfigReader.getProperty("concept")
                    , null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_a));
            System.out.println("error : organizer does not exist!");
            actualDataWithSubscriptionId = obj.readValue(response.asString(), SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }
    @Then("user validates subscription setting not found error with {string} status code for subscription setting unarchive")
    public void user_validates_subscription_setting_not_found_error_with_status_code_for_subscription_setting_unarchive(String status_code_a) throws IOException {
        if (!listOfSubscriptionId.contains(ConfigReader.getProperty("subscription_setting_id"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_subscription_not_found"),
                    ConfigReader.getProperty("concept_subscription"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_a));
            System.out.println("error : subscription setting does not exist!");
            actualDataWithSubscriptionId = obj.readValue(response.asString(), SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }
        @Then("user validates subscription setting not link to organizer error with {string} status code for subscription setting unarchive")
        public void user_validates_subscription_setting_not_link_to_organizer_error_with_status_code_for_subscription_setting_unarchive(String status_code_b) throws IOException {
            if(!listOfSubscriptionIdWithOrg.contains(ConfigReader.getProperty("subscription_setting_id"))) {
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_subscription_not_linked"),
                        ConfigReader.getProperty("concept_subscription_not_linked"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
                System.out.println("error : subscription setting does not link to organizer!");
                actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
                Assert.assertNotNull(actualDataWithSubscriptionId.getData());
            }
        }
        @Then("user validates subscription setting is not archived error with {string} status code for subscription setting unarchive")
        public void user_validates_subscription_setting_is_not_archived_error_with_status_code_for_subscription_setting_unarchive(String status_code_b) throws IOException {
            if(listOfArchivedAtField.contains(null)){
                System.out.println("list for date archived_at "+listOfArchivedAtField);
                responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("concept_subscription_not_archived"),
                        ConfigReader.getProperty("concept_subscription"), null, null);
                actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
                Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
                Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
                Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
                System.out.println("error : subscription setting is not archived!");
                actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
                Assert.assertNotNull(actualDataWithSubscriptionId.getData());
            }
        }
        @Then("user validates archive_at to null on core_subscription_settings with {string} status code")
        public void user_validates_archive_at_to_null_on_core_subscription_settings_with_status_code (String status_code_c){
            System.out.println(listOfArchivedAtField);
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_c));
            //Assert.assertTrue(listOfArchivedAtField.contains(null), "Expected null to be present in listOfArchivedAtField. Actual contents: " + listOfArchivedAtField);

        }

}
