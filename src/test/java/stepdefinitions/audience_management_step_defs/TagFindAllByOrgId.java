package stepdefinitions.audience_management_step_defs;

import base_url_set_up.CoreBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import pojo.audience_pojo.ResponseTagFindAllByOrganizerPojo;
import pojo.audience_pojo.TagAndTagGroupPojo;
import utilities.ConfigReader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static audience_management_test_data.Headers.headers;
import static io.restassured.RestAssured.*;
import static utilities.DBUtils.*;

public class TagFindAllByOrgId extends CoreBaseURL {

    TagAndTagGroupPojo requestBody;
    Response response;
    ResponseTagFindAllByOrganizerPojo actualData;
    ObjectMapper obj = new ObjectMapper();


    {
        coreSetUp();
        spec.pathParams("first", "setting", "second", "tag", "third", "find-by-organizer-id");
    }


    @Given("user generates requset body with organizer id")
    public void user_generates_requset_body_with_organizer_id() {
        requestBody = new TagAndTagGroupPojo(301,null,null,null,null,null,null);
    }
    @When("user sends post request for finding all tags related to organizer")
    public void user_sends_post_request_for_finding_all_tags_related_to_organizer() {
        response = given().spec(spec).headers(headers).body(requestBody).post("/{first}/{second}/{third}");
    }
    @Then("user verifies all tags info listed consecutively")
    public void user_verifies_all_tags_info_listed_consecutively() throws IOException, SQLException {
        createConnection();
        List<String> listOfUnarchivedTagNames = getColumnDataAsString(ConfigReader.getProperty("query_unarchived_tag_names_with_organizer"),"name");
        actualData = obj.readValue(response.asString(), ResponseTagFindAllByOrganizerPojo.class);
        List<String> list = new ArrayList<>();
        for(int i =0; i<actualData.getData().size(); i++ ){
            list.add(actualData.getData().get(i).getName());
        }
        Assert.assertTrue(listOfUnarchivedTagNames.containsAll(list));
    }
}
