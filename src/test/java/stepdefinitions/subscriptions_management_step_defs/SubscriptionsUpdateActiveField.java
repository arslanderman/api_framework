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
import pojo.audience_pojo.TagAndTagGroupResponseDataPositive;
import pojo.subscription_pojo.SubscriptionCreatePojo;
import pojo.subscription_pojo.UpdateActiveFieldRequestPojo;
import utilities.ConfigReader;

import java.io.EOFException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static utilities.DBUtils.*;

public class SubscriptionsUpdateActiveField extends CoreBaseURL {

    UpdateActiveFieldRequestPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    SubscriptionCreatePojo actualDataWithSubscriptionId;
    Response response;
    ObjectMapper obj = new ObjectMapper();
    List<Integer> listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"), "id_organizer");
    List<String > listOfSubscriptionId = getColumnDataAsString(ConfigReader.getProperty("query_subscription_setting_id"), "id");
    List<String > listOfSubscriptionIdWithOrg = getColumnDataAsString(ConfigReader.getProperty("query_subscription_id_link_to_org"), "id");
    List<Object> listOfArchivedSubscription = getColumnData(ConfigReader.getProperty("query_archived_subscription"), "id");
    List<Date> listOfDates = getColumnDataAsDate(ConfigReader.getProperty("query_subscription_setting"), "updated_at");
    int i = Integer.parseInt(ConfigReader.getProperty("org_id"));

    {
        coreSetUp();
        spec.pathParams("first","organizer","second","7877","third","setting","fourth","subscription",
                "fifth","update-active-field");
    }

    public SubscriptionsUpdateActiveField() throws ParseException {
    }

    @Given("user creates request body for subscription setting update active field")
    public void user_creates_request_body_for_subscription_setting_update_active_field() {
       requestBody = new UpdateActiveFieldRequestPojo(ConfigReader.getProperty("subscription_setting_id"),true);
    }
    @When("user sends post request for subscription setting update active field")
    public void user_sends_post_request_for_subscription_setting_update_active_field() {
        response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}/{fifth}");
        response.prettyPrint();
    }
    @Then("user validates organizer not found error with {string} status code for updating active field")
    public void user_validates_organizer_not_found_error_with_status_code_for_updating_active_field(String status_code_a) throws IOException {
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
    @Then("user validates subscription setting not found error with {string} status code")
    public void user_validates_subscription_setting_not_found_error_with_status_code(String status_code_a) throws IOException {
        if(!listOfSubscriptionId.contains(ConfigReader.getProperty("subscription_setting_id"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_subscription_not_found"),
                    ConfigReader.getProperty("concept_subscription"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_a));
            System.out.println("error : subscription setting does not exist!");
            actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }
    @Then("user validates subscription setting not link to organizer error with {string} status code")
    public void user_validates_subscription_setting_not_link_to_organizer_error_with_status_code(String status_code_b) throws IOException {
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
    @Then("user validates subscription setting is archived error with {string} status code")
    public void user_validates_subscription_setting_is_archived_error_with_status_code(String status_code_b) throws IOException {
        if(listOfArchivedSubscription.contains(ConfigReader.getProperty("subscription_setting_id"))) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_archived_subscription"),
                    ConfigReader.getProperty("concept_subscription"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
            System.out.println("error : subscription setting is archived!");
            actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }
    @Then("user validates subscription setting table is_active field is updated with {string} status code")
    public void user_validates_subscription_setting_table_is_active_field_is_updated_with_status_code(String status_code_c) throws IOException {
           try{ // i need to close try catch block for all negative path otherwise it is ignoring the codes and test passes
                actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
            }catch (EOFException e){
                System.out.println("status code "+response.getStatusCode());
                Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_c));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                String a = simpleDateFormat.getCalendar().getTime().toString();
                String b = listOfDates.get(0).toString();
                Assert.assertEquals(a.substring(4, 10), b.substring(4, 10));
                if (actualDataWithSubscriptionId != null) {
                    Assert.assertNull(actualDataWithSubscriptionId.getData());
                } else {
                    System.out.println("actual data is null!");
                }
            System.out.println("The response has no content, update has been made!");
                /*
                actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
            if ( actualDataWithSubscriptionId.getData()== null) {
                Assert.assertTrue(true);
            } else {
                String id = actualDataWithSubscriptionId.getData().getId();
                Assert.assertTrue(id == null || id.isEmpty());
            }
                 */
    }
  }
}
