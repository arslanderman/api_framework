package base_url_set_up;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class CoreBaseURL {

    protected RequestSpecification spec;

    public void coreSetUp(){

        spec = new RequestSpecBuilder().setBaseUri("http://178.32.169.19").build();

    }


}
