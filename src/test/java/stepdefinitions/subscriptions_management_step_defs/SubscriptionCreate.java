package stepdefinitions.subscriptions_management_step_defs;

import base_url_set_up.CoreBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import pojo.audience_pojo.ResponseTagAndTagGroupPojo;
import pojo.subscription_pojo.SubscriptionCreatePojo;
import pojo.subscription_pojo.SubscriptionCreateRequestPojo;
import utilities.ConfigReader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static utilities.DBUtils.*;

public class SubscriptionCreate extends CoreBaseURL {

    SubscriptionCreateRequestPojo requestBody;
    ResponseTagAndTagGroupPojo responseBody;
    ResponseTagAndTagGroupPojo actualData;
    SubscriptionCreatePojo actualDataWithSubscriptionCreation;
    Response response;
    ObjectMapper obj = new ObjectMapper();
    List<Integer> listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"), "id_organizer");
    List<Integer> listOfShops = getColumnDataAsInteger(ConfigReader.getProperty("query_shop_id"), "id_shop");
    List<Integer> listOfShopsByOrg = getColumnDataAsInteger(ConfigReader.getProperty("query_shop_id_related_to_org"), "id_shop");
    List<String> listOfNames = getColumnDataAsString(ConfigReader.getProperty("query_subscription_setting_lang"), "name");
    List<Date> listOfDates = getColumnDataAsDate(ConfigReader.getProperty("query_subscription_setting"), "created_at");
    int shopId = Integer.parseInt(ConfigReader.getProperty("shopId"));
    int i = Integer.parseInt(ConfigReader.getProperty("org_id"));

    {
        coreSetUp();
        spec.pathParams("first","organizer","second","611","third",
                "setting","fourth","subscription","fifth","create");
    }

    public SubscriptionCreate() throws ParseException {
    }

    @Given("user creates request body for subscription creation")
    public void user_creates_request_body_for_subscription_creation() {
        requestBody = new SubscriptionCreateRequestPojo(shopId,ConfigReader.getProperty("name"));
        System.out.println("REQUEST BODY "+requestBody);
    }
    @When("user sends post request for subscription creation")
    public void user_sends_post_request_for_subscription_creation() {
        response = given().spec(spec).headers(headers).body(requestBody).
                post("/{first}/{second}/{third}/{fourth}/{fifth}");
        response.prettyPrint();
    }
    @Then("user validates organizer not found error with {string} status code for subscription creation")
    public void user_validates_organizer_not_found_error_with_status_code_for_subscription_creation(String status_code_a) throws IOException {
        if(!listOfOrganizers.contains(i)) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"), ConfigReader.getProperty("concept")
                    , null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_a));
            System.out.println("error : organizer does not exist!");
            actualDataWithSubscriptionCreation = obj.readValue(response.asString(), SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionCreation.getData());
        }
    }
    @Then("user validates shop not found error with {string} status code for subscription creation")
    public void user_validates_shop_not_found_error_with_status_code_for_subscription_creation(String status_code_a) throws IOException {
        if(!listOfShops.contains(shopId)) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_shop_not_found"), null, null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_a));
            System.out.println("error : shop does not exist!");
            actualDataWithSubscriptionCreation = obj.readValue(response.asString(), SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionCreation.getData());
        }
    }
    @Then("user validates shop not link to organizer with {string} status code for subscription creation")
    public void user_validates_shop_not_link_to_organizer_with_status_code_for_subscription_creation(String status_code_b) throws IOException {
        if(!listOfShopsByOrg.contains(shopId)) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_shop_not_link_org"),
                    ConfigReader.getProperty("concept_shop"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
            System.out.println("error : shop does not link to organizer!");
            actualDataWithSubscriptionCreation = obj.readValue(response.asString(), SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionCreation.getData());
        }
    }
    @Then("user validates name is empty or filled with spaces with {string} status code")
    public void user_validates_name_is_empty_or_filled_with_spaces_with_status_code(String status_code_b) throws IOException {
        if(ConfigReader.getProperty("name").isEmpty()||ConfigReader.getProperty("name").trim().isEmpty()){
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_name_with_space"),
                    null, null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            System.out.println("ERROR CODE FOR NAME "+actualData.getError_code());
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
            System.out.println("error : name must not be empty or must not be defined with just spaces!");
            actualDataWithSubscriptionCreation = obj.readValue(response.asString(), SubscriptionCreatePojo.class);
            Assert.assertNotNull(actualDataWithSubscriptionCreation.getData());
        }
    }
    @Then("user validates new rows on core_subscription_settings and core_subscription_setting_lang with {string} status code")
    public void user_validates_new_rows_on_core_subscription_settings_and_core_subscription_setting_lang_with_status_code(String status_code_c) throws IOException {
        actualDataWithSubscriptionCreation = obj.readValue(response.asString(),SubscriptionCreatePojo.class);
        System.out.println("actual data "+actualDataWithSubscriptionCreation);
        Assert.assertNotNull(actualDataWithSubscriptionCreation.getData());
        Assert.assertFalse(actualDataWithSubscriptionCreation.getData().toString().isEmpty());
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_c));
        Assert.assertTrue(listOfNames.contains(ConfigReader.getProperty("name")));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String a = simpleDateFormat.getCalendar().getTime().toString();
        String b = listOfDates.get(0).toString();
        Assert.assertEquals(a.substring(4,9),b.substring(4,9));
    }
}
