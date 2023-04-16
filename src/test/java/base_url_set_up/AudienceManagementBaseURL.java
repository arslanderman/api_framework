package base_url_set_up;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AudienceManagementBaseURL {

    protected RequestSpecification spec;

    public void audienceManagementSetUp(){

        spec = new RequestSpecBuilder().setBaseUri("http://151.80.82.81").build();



    }


}
