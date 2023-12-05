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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static utilities.DBUtils.*;

public class SubscriptionArchiveStepDefs extends CoreBaseURL {

    SubscriptionDeleteRequestPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    SubscriptionCreatePojo actualDataWithSubscriptionId;
    Response response;
    ObjectMapper obj = new ObjectMapper();
    List<Integer> listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"), "id_organizer");
    List<String > listOfSubscriptionId = getColumnDataAsString(ConfigReader.getProperty("query_subscription_setting_id"), "id");
    List<String > listOfSubscriptionIdWithOrg = getColumnDataAsString(ConfigReader.getProperty("query_subscription_id_link_to_org"), "id");
    List<Integer> listOfIsActive = getColumnDataAsInteger(ConfigReader.getProperty("query_subscription_is_active"), "is_active");
    List<Object> listOfActive = getColumnData(ConfigReader.getProperty("query_subscription_active_on_core_subscription"), "active");
    List<Object> listOfEvent = getColumnData(ConfigReader.getProperty("query_subscription_event"), "event_id");
    List<Object> listOfSession = getColumnData(ConfigReader.getProperty("query_subscription_session"), "session_id");
    List<Object> listOfSalesPhase = getColumnData(ConfigReader.getProperty("query_subscription_sales_phase"), "sales_phase_id");
    List<String> listOfBoughtSubscriptions = getColumnDataAsString(ConfigReader.getProperty("query_list_of_bought_subscriptions"), "subscription_setting_id");
    List<Date> listArchived_At = getColumnDataAsDate(ConfigReader.getProperty("query_subscription_archived"), "archived_at");
    int i = Integer.parseInt(ConfigReader.getProperty("org_id"));
    int y = 1;
    {
        coreSetUp();
        spec.pathParams("first","organizer","second", ConfigReader.getProperty("org_id"),"third","setting",
                "fourth","subscription","fifth","archive");
    }

    public SubscriptionArchiveStepDefs() throws ParseException {
    }

    @Given("user creates request body for subscription setting archive")
    public void user_creates_request_body_for_subscription_setting_archive() {
        requestBody = new SubscriptionDeleteRequestPojo(ConfigReader.getProperty("subscription_setting_id"));
        System.out.println(requestBody);
    }
    @When("user sends post request for subscription setting archive")
    public void user_sends_post_request_for_subscription_setting_archive() {
        response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}/{fifth}");
        response.prettyPrint();
        System.out.println("is active  ?   "+listOfActive);
    }
    @Then("user validates organizer not found error with {string} status code for subscription archive")
    public void user_validates_organizer_not_found_error_with_status_code_for_subscription_archive(String status_code_a) throws IOException {
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
    @Then("user validates subscription setting not found error with {string} status code for subscription setting archive")
    public void user_validates_subscription_setting_not_found_error_with_status_code_for_subscription_setting_archive(String status_code_a) throws IOException {
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
    @Then("user validates subscription setting not link to organizer error with {string} status code for subscription setting archive")
    public void user_validates_subscription_setting_not_link_to_organizer_error_with_status_code_for_subscription_setting_archive(String status_code_b) throws IOException {
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
    @Then("user validates subscription setting is active error with {string} status code for subscription setting archive")
    public void user_validates_subscription_setting_is_active_error_with_status_code_for_subscription_setting_archive(String status_code_b) throws IOException {
        if(listOfIsActive.contains(y)){
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_active_subscription"),
                    ConfigReader.getProperty("concept_subscription"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
            System.out.println("error : subscription setting is active!");
            actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }
    @Then("user validates subscription setting is link to an event error with {string} status code for subscription setting archive")
    public void user_validates_subscription_setting_is_link_to_an_event_error_with_status_code_for_subscription_setting_archive(String status_code_b) throws IOException {
        if(!listOfEvent.contains(null)){
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_subscription"),
                    ConfigReader.getProperty("concept_subscription_event"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
            System.out.println("error : subscription setting is link to event!");
            actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }
    @Then("user validates subscription setting is link to a session error with {string} status code for subscription setting archive")
    public void user_validates_subscription_setting_is_link_to_a_session_error_with_status_code_for_subscription_setting_archive(String status_code_b) throws IOException {
        if(!listOfSession.contains(null)){
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_subscription"),
                    ConfigReader.getProperty("concept_subscription_session"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
            System.out.println("error : subscription setting is link to session!");
            actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }
    @Then("user validates subscription setting is link to a sales phase error with {string} status code for subscription setting archive")
    public void user_validates_subscription_setting_is_link_to_a_sales_phase_error_with_status_code_for_subscription_setting_archive(String status_code_b) throws IOException {
        if(!listOfSalesPhase.isEmpty()){
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_subscription"),
                    ConfigReader.getProperty("concept_subscription_sales_phase"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
            System.out.println("error : subscription setting is link to sales phase!");
            actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }
    @Then("user validates subscription setting has not bought error with {string} status code for subscription setting archive")
    public void user_validates_subscription_setting_has_not_bought_error_with_status_code_for_subscription_setting_archive(String status_code_b) throws IOException {
        if(!listOfBoughtSubscriptions.contains(ConfigReader.getProperty("subscription_setting_id"))){
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_subscription_bought"),
                    ConfigReader.getProperty("concept_subconcept_subscription_boughtscription_sales_phase"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
            System.out.println("error : subscription setting not bought!");
            actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }

    @Then("user validates subscription is not active on core_subscription error with {string} status code for subscription setting archive")
    public void user_validates_subscription_is_not_active_on_core_subscription_error_with_status_code_for_subscription_setting_archive(String status_code_b) throws IOException {
        if(listOfActive.contains(false)){
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_subscription_active"),
                    ConfigReader.getProperty("concept_active"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
            System.out.println("error : subscription is not active!");
            actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }


    @Then("user validates archive_at to now update on core_subscription_settings with {string} status code")
    public void user_validates_archive_at_to_now_update_on_core_subscription_settings_with_status_code(String status_code_c) {
        Assert.assertFalse(listArchived_At.contains(null));
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_c));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        String a = simpleDateFormat.format(new Date());
        String b = listArchived_At.get(0).toString();
        Assert.assertEquals(a.substring(0,10) + a.substring(23),b.substring(0,10) + b.substring(23));
    }
}
