package base_url_set_up;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseURLForApiAutomation {

    protected RequestSpecification spec;

    public void automationSetUp(){

        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

    }

    //spec = new RequestSpecBuilder().setBaseUri("https://reqres.in").build();

}
