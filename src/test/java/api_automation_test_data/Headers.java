package api_automation_test_data;

import java.util.HashMap;
import java.util.Map;

public class Headers {

   public static Map<String,String> headers;

    public static Map<String,String> headersForPost(String ContentType){

        headers = new HashMap<>();
        headers.put("ContentType",ContentType);
        //headers.put("host",host);
        return headers;
    }


}
