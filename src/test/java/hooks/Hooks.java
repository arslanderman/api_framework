package hooks;

import base_url_set_up.AudienceManagBaseUrlStatic;
import base_url_set_up.AudienceManagementBaseURL;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Map;

import static audience_management_test_data.Headers.headersForPost;
import static audience_management_test_data.HeadersAudienceManagment.header;



public class Hooks extends AudienceManagementBaseURL {

   static Map<String,String> headers;
/*
    @Before(order=1,value ="@tag_group_create")
    public void beforeApi(){
        //audienceManagementSetUp();
        //spec.pathParams("first","setting","second","tag","third","group","fourth","create");
        //headers = header(ConfigReader.getProperty("ContentType"),ConfigReader.getProperty("host"));
        audienceManagementSetUp();
        spec.pathParams("first","setting","second","tag","third","group","fourth","create");

    }

 */
    /*
    @Before(value ="@tag_create")
    public void beforeApiTagCreate(){
        audienceManagementSetUp();
        spec.pathParams("first","setting","second","tag","third","create");
    }

     */

    @Before(value ="@tag_find_all_by_org")
    public static void beforeApiHeaders(){
        headers = headersForPost(ConfigReader.getProperty("ContentType"),ConfigReader.getProperty("host"));
    }

    /*
    @After
    public void tearDown(Scenario scenario) {

        System.out.println("After Hooks");
        //adding reports that is generated when a scenrio fails
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "failed scenerio");
            Driver.closeDriver();

        }
    }

     */

}
