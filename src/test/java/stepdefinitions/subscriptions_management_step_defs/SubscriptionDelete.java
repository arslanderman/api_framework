package stepdefinitions.subscriptions_management_step_defs;

import audience_management_test_data.Headers;
import base_url_set_up.CoreBaseURL;
import hooks.Hooks;
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

public class SubscriptionDelete extends CoreBaseURL {


    SubscriptionDeleteRequestPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    SubscriptionCreatePojo actualDataWithSubscriptionId;
    Response response;

    ObjectMapper obj = new ObjectMapper();

    List<Integer> listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"), "id_organizer");
    List<String > listOfSubscriptionId = Hooks.listOfSubscriptionId;
    List<String > listOfSubscriptionIdA = getColumnDataAsString(ConfigReader.getProperty("query_subscription_setting_id"), "id");
    List<String > listOfSubscriptionIdWithOrg = Hooks.listOfSubscriptionIdWithOrg;
    List<Integer> listOfIsActive = Hooks.listOfIsActive;
    List<Object> listOfEvent = Hooks.listOfEvent;
    //List<Integer> listOfEvent = getColumnDataAsInteger(ConfigReader.getProperty("query_subscription_event"), "event_id");
    List<Object> listOfSession = Hooks.listOfSession;
    List<Object> listOfSalesPhase = Hooks.listOfSalesPhase;
    List<String> listOfBoughtSubscriptions = Hooks.listOfBoughtSubscriptions;
    List<String> listOfSubscriptionSettingLang = getColumnDataAsString(ConfigReader.getProperty("query_subscription_setting_lang"), "subscription_setting_id");
    List<String> listOfSubscriptionSettingResellingRule = getColumnDataAsString(ConfigReader.getProperty("query_subscription_reselling_rule"), "subscription_setting_id");
    List<String> listOfSubscriptionSettingResellingRuleEvent = getColumnDataAsString(ConfigReader.getProperty("query_subscription_reselling_rule_event"), "subscription_setting_id");
    List<String> listOfSubscriptionSettingThChangeRule = getColumnDataAsString(ConfigReader.getProperty("query_subscription_th_change_rule"), "subscription_setting_id");
    List<String> listOfSubscriptionSettingThChangeRuleEvent = getColumnDataAsString(ConfigReader.getProperty("query_subscription_th_change_rule_event"), "subscription_setting_id");

    int i = Integer.parseInt(ConfigReader.getProperty("org_id"));
    int y = 1;

    {
        coreSetUp();
        spec.pathParams("first","organizer","second",ConfigReader.getProperty("org_id"),"third","setting","fourth","subscription"
        ,"fifth","delete");
    }

    @Given("user creates request body for subscription setting delete with existing organizer")
    public void user_creates_request_body_for_subscription_setting_delete_with_existing_organizer() {
        requestBody = new SubscriptionDeleteRequestPojo(ConfigReader.getProperty("subscription_setting_id"));
        System.out.println(requestBody);
    }
    @Given("user creates request body for subscription setting delete with existing subscription")
    public void user_creates_request_body_for_subscription_setting_delete_with_existing_subscription() {
        requestBody = new SubscriptionDeleteRequestPojo(ConfigReader.getProperty("subscription_setting_id"));
    }
    @Given("user creates request body for subscription setting delete with linked organizer")
    public void user_creates_request_body_for_subscription_setting_delete_with_linked_organizer() {
        requestBody = new SubscriptionDeleteRequestPojo(ConfigReader.getProperty("subscription_setting_id"));
    }
    @Given("user creates request body for subscription setting delete with inactive subscription")
    public void user_creates_request_body_for_subscription_setting_delete_with_inactive_subscription() {
        requestBody = new SubscriptionDeleteRequestPojo(ConfigReader.getProperty("subscription_setting_id"));
    }
    @Given("user creates request body for subscription setting delete with unlinked event")
    public void user_creates_request_body_for_subscription_setting_delete_with_unlinked_event() {
        requestBody = new SubscriptionDeleteRequestPojo(ConfigReader.getProperty("subscription_setting_id"));
    }
    @Given("user creates request body for subscription setting delete with unlinked session")
    public void user_creates_request_body_for_subscription_setting_delete_with_unlinked_session() {
        requestBody = new SubscriptionDeleteRequestPojo(ConfigReader.getProperty("subscription_setting_id"));
    }
    @Given("user creates request body for subscription setting delete with unlinked sales phase")
    public void user_creates_request_body_for_subscription_setting_delete_with_unlinked_sales_phase() {
        requestBody = new SubscriptionDeleteRequestPojo(ConfigReader.getProperty("subscription_setting_id"));
    }
    @Given("user creates request body for subscription setting delete with unbought subscription")
    public void user_creates_request_body_for_subscription_setting_delete_with_unbought_subscription() {
        requestBody = new SubscriptionDeleteRequestPojo(ConfigReader.getProperty("subscription_setting_id"));
    }
    @Given("user creates request body for subscription setting delete")
    public void user_creates_request_body_for_subscription_setting_delete() {
        requestBody = new SubscriptionDeleteRequestPojo(ConfigReader.getProperty("subscription_setting_id"));
    }
    @When("user sends post request for subscription setting delete")
    public void user_sends_post_request_for_subscription_setting_delete() {
        response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}/{fifth}");
        response.prettyPrint();

    }
    @Then("user validates organizer not found error with {string} status code for subscription delete")
    public void user_validates_organizer_not_found_error_with_status_code_for_subscription_delete(String status_code_a) throws IOException {

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
    @Then("user validates subscription setting not found error with {string} status code for subscription setting delete")
    public void user_validates_subscription_setting_not_found_error_with_status_code_for_subscription_setting_delete(String status_code_a) throws IOException {
       // System.out.println(listOfSubscriptionId);
        if(!Hooks.listOfSubscriptionId.contains(ConfigReader.getProperty("subscription_setting_id"))) {
            System.out.println("ssssssssss "+listOfSubscriptionIdA);
            System.out.println("sssseeeeee "+Hooks.listOfSubscriptionId);
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
    @Then("user validates subscription setting not link to organizer error with {string} status code for subscription setting delete")
    public void user_validates_subscription_setting_not_link_to_organizer_error_with_status_code_for_subscription_setting_delete(String status_code_b) throws IOException {
        if(!Hooks.listOfSubscriptionIdWithOrg.contains(ConfigReader.getProperty("subscription_setting_id"))) {
            System.out.println("list ababa "+listOfSubscriptionIdWithOrg);
            //System.out.println(!listOfSubscriptionIdWithOrg.contains(ConfigReader.getProperty("subscription_setting_id")));
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
    @Then("user validates subscription setting is active error with {string} status code")
    public void user_validates_subscription_setting_is_active_error_with_status_code(String status_code_b) throws IOException {
        if(Hooks.listOfIsActive.contains(y)){
            System.out.println(listOfIsActive);
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
    }   //list returns object so condition becomes true but assertion fails
    @Then("user validates subscription setting is link to an event error with {string} status code")
    public void user_validates_subscription_setting_is_link_to_an_event_error_with_status_code(String status_code_b) throws IOException {
        if(!Hooks.listOfEvent.contains(null)){
            System.out.println(listOfEvent);
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_subscription"),
                    ConfigReader.getProperty("concept_subscription_event"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
           // System.out.println(" ffffffff  "+listOfEvent);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
            System.out.println("error : subscription setting is link to event!");
            actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }
   @Then("user validates subscription setting is link to a session error with {string} status code")
   public void user_validates_subscription_setting_is_link_to_a_session_error_with_status_code(String status_code_b) throws IOException {
       if(!Hooks.listOfSession.contains(null)){
           System.out.println(listOfSession);
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
   @Then("user validates subscription setting is link to a sales phase error with {string} status code")
   public void user_validates_subscription_setting_is_link_to_a_sales_phase_error_with_status_code(String status_code_b) throws IOException {
      if(!Hooks.listOfSalesPhase.isEmpty()){
         // System.out.println("condition"+!listOfSalesPhase.contains(null));
           responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_subscription"),
                   ConfigReader.getProperty("concept_subscription_sales_phase"), null, null);
           actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
          System.out.println("listttt "+listOfSalesPhase);
           Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
           Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
           Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
           System.out.println("error : subscription setting is link to sales phase!");
           actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
           Assert.assertNotNull(actualDataWithSubscriptionId.getData());
       }
   }
    @Then("user validates subscription setting has already bought error with {string} status code")
    public void user_validates_subscription_setting_has_already_bought_error_with_status_code(String status_code_b) throws IOException {
        if(Hooks.listOfBoughtSubscriptions.contains(ConfigReader.getProperty("subscription_setting_id"))){
            System.out.println(listOfBoughtSubscriptions);
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_subscription_bought"),
                    ConfigReader.getProperty("concept_subconcept_subscription_boughtscription_sales_phase"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
            System.out.println("error : subscription setting bought!");
            actualDataWithSubscriptionId = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }

    @Then("user validates row deletions on core_subscription_setting, core_subscription_setting_lang, core_reselling_rule, core_reselling_rule_event, core_th_change_rule, core_th_change_rule_event with {string} status code")
    public void user_validates_row_deletions_on_core_subscription_setting_core_subscription_setting_lang_core_reselling_rule_core_reselling_rule_event_core_th_change_rule_core_th_change_rule_event_with_status_code(String status_code_c) {

        //Assert.assertFalse(listOfSubscriptionId.contains(ConfigReader.getProperty("subscription_setting_id")));
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_c));
        Assert.assertFalse(listOfSubscriptionSettingLang.contains(ConfigReader.getProperty("subscription_setting_id")));
        Assert.assertFalse(listOfSubscriptionSettingResellingRule.contains(ConfigReader.getProperty("subscription_setting_id")));
        Assert.assertFalse(listOfSubscriptionSettingResellingRuleEvent.contains(ConfigReader.getProperty("subscription_setting_id")));
        Assert.assertFalse(listOfSubscriptionSettingThChangeRule.contains(ConfigReader.getProperty("subscription_setting_id")));
        Assert.assertFalse(listOfSubscriptionSettingThChangeRuleEvent.contains(ConfigReader.getProperty("subscription_setting_id")));


    }
}
