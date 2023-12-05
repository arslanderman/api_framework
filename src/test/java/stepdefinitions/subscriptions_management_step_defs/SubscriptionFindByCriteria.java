package stepdefinitions.subscriptions_management_step_defs;


import base_url_set_up.CoreBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import pojo.audience_pojo.ResponseTagAndTagGroupPojo;
import pojo.subscription_pojo.*;
import utilities.ConfigReader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static utilities.DBUtils.getColumnDataAsInteger;


public class SubscriptionFindByCriteria extends CoreBaseURL {

SubscriptionFindByCriteriaRequestPojo requestBody;
ResponseTagAndTagGroupPojo responseBody;
ResponseTagAndTagGroupPojo actualData;
SubscriptionFindByCriteriaResponsePojo actualDataWithSubscriptions;
Response response;

    List<Integer> listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"), "id_organizer");
    List<Integer> listOfShops = getColumnDataAsInteger(ConfigReader.getProperty("query_shop_id"), "id_shop");
    List<Integer> listOfShopsByOrg = getColumnDataAsInteger(ConfigReader.getProperty("query_shop_id_related_to_org"), "id_shop");
    ObjectMapper obj = new ObjectMapper();
    int shopId = Integer.parseInt(ConfigReader.getProperty("shopId"));
    int i = Integer.parseInt(ConfigReader.getProperty("org_id"));

    {
        coreSetUp();
        spec.pathParams("first","organizer","second", ConfigReader.getProperty("org_id"),
                "third","setting","fourth","subscription","fifth","find-by-criteria");
    }

    @Given("user creates request body for finding all subscriptions by criteria")
    public void user_creates_request_body_for_finding_all_subscriptions_by_criteria() {
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(ConfigReader.getProperty("created_at"), formatter);
        Date createdAt = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("xxxxxx "+formatter.format(localDateTime));

        // Display the parsed date
        LocalDateTime localDateTime1 = LocalDateTime.parse(ConfigReader.getProperty("created_to"), formatter);
        Date createdTo = Date.from(localDateTime1.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("created at: " + createdAt);
        System.out.println("created to: " + createdTo);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Formatted Date: " + outputFormat.format(createdAt));
        requestBody = new SubscriptionFindByCriteriaRequestPojo(shopId,true,ConfigReader.getProperty("subscription_setting_id"),
                ConfigReader.getProperty("name"),seasonId,false,createdAt,createdTo);
        System.out.println("request body "+requestBody);



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(ConfigReader.getProperty("created_at"), formatter);
        Date createdAtFrom = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        // Format the Date object for display
        //SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println("created at: " + outputFormat.format(createdAtFrom));

        LocalDateTime localDateTime1 = LocalDateTime.parse(ConfigReader.getProperty("created_to"), formatter);
        Date createdAtTo = Date.from(localDateTime1.atZone(ZoneId.systemDefault()).toInstant());
       // System.out.println("created to: " + outputFormat.format(createdAtTo));0dcab776-43bd-4416-b7ea-b2551c1d27aa
*/
        requestBody = new SubscriptionFindByCriteriaRequestPojo(shopId, false, ConfigReader.getProperty("subscription_setting_id"),
                "", null, false, null,
                ConfigReader.getProperty("created_to"));
        System.out.println("request body " + requestBody);





        //fgh = new SubscriptionFindByCriteriaResponseHrefPojo(ConfigReader.getProperty("href"));
        //responseBody = new SubscriptionFindByCriteriaResponsePojo(new SubscriptionFindByCriteriaResponse_XPojo(fgh,fgh,fgh,fgh,fgh),
          //      23,67,new SubscriptionFindByCriteriaResponseDataPojo());
        //System.out.println(responseBody);

        //System.out.println("fgh "+fgh);
        //jjj = new SubscriptionFindByCriteriaResponse_XPojo(fgh,fgh,fgh,fgh,fgh);
        //System.out.println("jjj"+jjj);

    }
    @When("user sends post request for finding all subscriptions by criteria")
    public void user_sends_post_request_for_finding_all_subscriptions_by_criteria() {
        response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}/{fourth}/{fifth}");
        response.prettyPrint();
    }
    @Then("user validates organizer not found error with {string} status code for finding all subscriptions by criteria")
    public void user_validates_organizer_not_found_error_with_status_code_for_finding_all_subscriptions_by_criteria(String status_code_a) throws IOException {
        if(!listOfOrganizers.contains(i)) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code"), ConfigReader.getProperty("concept")
                    , null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_a));
            System.out.println("error : organizer does not exist!");
            actualDataWithSubscriptions = obj.readValue(response.asString(), SubscriptionFindByCriteriaResponsePojo.class);
            for (int j = 0; j < actualDataWithSubscriptions.getData().size(); j++) {
                Assert.assertFalse(actualDataWithSubscriptions.getData().isEmpty());
                Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getName().isEmpty());
                Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getSeasonId().toString().isEmpty());
                Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getSessionId().toString().isEmpty());
                Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getCreatedAt().isEmpty());
            }

           // Assert.assertNotNull(actualDataWithSubscriptionId.getData());
        }
    }
    @Then("user validates shop not found error with {string} status code for finding all subscriptions by criteria")
    public void user_validates_shop_not_found_error_with_status_code_for_finding_all_subscriptions_by_criteria(String status_code_a) throws IOException {
        if(!listOfShops.contains(shopId)) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_shop_not_found"), null, null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_a));
            System.out.println("error : shop does not exist!");
            actualDataWithSubscriptions = obj.readValue(response.asString(), SubscriptionFindByCriteriaResponsePojo.class);
            for (int j = 0; j < actualDataWithSubscriptions.getData().size(); j++) {
                Assert.assertFalse(actualDataWithSubscriptions.getData().isEmpty());
                Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getName().isEmpty());
                Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getSeasonId().toString().isEmpty());
                Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getSessionId().toString().isEmpty());
                Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getCreatedAt().isEmpty());
            }
        }
    }
    @Then("user validates shop not link to organizer with {string} status code for finding all subscriptions by criteria")
    public void user_validates_shop_not_link_to_organizer_with_status_code_for_finding_all_subscriptions_by_criteria(String status_code_b) throws IOException {
        if(!listOfShopsByOrg.contains(shopId)) {
            responseBody = new ResponseTagAndTagGroupPojo(ConfigReader.getProperty("error_code_shop_not_link_org"),
                    ConfigReader.getProperty("concept_shop"), null, null);
            actualData = obj.readValue(response.asString(), ResponseTagAndTagGroupPojo.class);
            Assert.assertEquals(actualData.getError_code(), responseBody.getError_code());
            Assert.assertEquals(actualData.getConcept(), responseBody.getConcept());
            Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_b));
            System.out.println("error : shop does not link to organizer!");
            for (int j = 0; j < actualDataWithSubscriptions.getData().size(); j++) {
                Assert.assertFalse(actualDataWithSubscriptions.getData().isEmpty());
                Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getName().isEmpty());
                Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getSeasonId().toString().isEmpty());
                Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getSessionId().toString().isEmpty());
                Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getCreatedAt().isEmpty());
            }
        }
    }
    @Then("user validates all subscriptions displayed linked to criteria with {string} status code")
    public void user_validates_all_subscriptions_displayed_linked_to_criteria_with_status_code(String status_code_d) throws IOException {
        actualDataWithSubscriptions = obj.readValue(response.asString(), SubscriptionFindByCriteriaResponsePojo.class);
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(status_code_d));
        System.out.println("number of the subscriptions related to given filters "+actualDataWithSubscriptions.getData().size());
        for (int j = 0; j < actualDataWithSubscriptions.getData().size(); j++) {
            Assert.assertFalse(actualDataWithSubscriptions.getData().isEmpty());
            Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getName().isEmpty());
            Assert.assertFalse(String.valueOf(actualDataWithSubscriptions.getData().get(j).getSeasonId()).isEmpty());
            Assert.assertFalse(String.valueOf(actualDataWithSubscriptions.getData().get(j).getSessionId()).isEmpty());
            Assert.assertNotNull(actualDataWithSubscriptions.getData().get(j).getIsActive());
            Assert.assertFalse(actualDataWithSubscriptions.getData().get(j).getCreatedAt().isEmpty());
        }
    }
}
