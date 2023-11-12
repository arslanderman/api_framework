package stepdefinitions.audience_management_step_defs;

import base_url_set_up.CoreBaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ConfigReader;

import java.util.Arrays;
import java.util.List;

public class TagRuleCreateStepDefs extends CoreBaseURL {

    List<String> tagsAdded = Arrays.asList("");
    List<String> tagsRemoved = Arrays.asList("");
    int i = Integer.parseInt(ConfigReader.getProperty("org_id"));
    int j = Integer.parseInt(ConfigReader.getProperty("sort"));

    {
        coreSetUp();
        spec.pathParams("first","setting","second","tag","third","rule","fourth","create");
    }

    @Given("user creates request body for rule creation")
    public void user_creates_request_body_for_rule_creation() {

    }
    @When("user sends post request for rule creation")
    public void user_sends_post_request_for_rule_creation() {

    }
    @Then("user validates organizer not found error with {string} status code for rule creation")
    public void user_validates_organizer_not_found_error_with_status_code_for_rule_creation(String status_code_a) {

    }

    @Then("user validates name already exists error with {string} status code for rule creation")
    public void user_validates_name_already_exists_error_with_status_code_for_rule_creation(String status_code_b) {

    }

    @Then("user validates tags_added and tags_removed credentials are empty with {string} status code")
    public void user_validates_tags_added_and_tags_removed_credentials_are_empty_with_status_code(String status_code_b) {

    }

    @Then("user validates criteria is invalid with {string} status code")
    public void user_validates_criteria_is_invalid_with_status_code(String status_code_b) {

    }

    @Then("user validates invalid entity type in criteria with {string} status code")
    public void user_validates_invalid_entity_type_in_criteria_with_status_code(String status_code_b) {

    }

    @Then("user validates one of the tag in criteria not found with {string} status code")
    public void user_validates_one_of_the_tag_in_criteria_not_found_with_status_code(String status_code_a) {

    }

    @Then("user validates one of the tag in criteria not link to organizer with {string} status code")
    public void user_validates_one_of_the_tag_in_criteria_not_link_to_organizer_with_status_code(String status_code_b) {

    }

    @Then("user validates one of the tag in criteria is archived with {string} status code")
    public void user_validates_one_of_the_tag_in_criteria_is_archived_with_status_code(String status_code_b) {

    }

    @Then("user validates one of the tag in tags_added and tags_removed is not found with {string} status code")
    public void user_validates_one_of_the_tag_in_tags_added_and_tags_removed_is_not_found_with_status_code(String status_code_a) {

    }

    @Then("user validates one of the tag in tags_added and tags_removed not link to organizer with {string} status code")
    public void user_validates_one_of_the_tag_in_tags_added_and_tags_removed_not_link_to_organizer_with_status_code(String status_code_b) {

    }

    @Then("user validates one of the tag in tags_added and tags_removed is archived with {string} status code")
    public void user_validates_one_of_the_tag_in_tags_added_and_tags_removed_is_archived_with_status_code(String status_code_b) {

    }

    @Then("user validates new row on core_tag_rule with {string} status code")
    public void user_validates_new_row_on_core_tag_rule_with_status_code(String status_code_c) {

    }

}
