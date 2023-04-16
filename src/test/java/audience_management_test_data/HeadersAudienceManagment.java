package audience_management_test_data;

import java.util.HashMap;
import java.util.Map;

public class HeadersAudienceManagment {

    public static Map<String,String> header(String ContentType, String host){

        Map<String,String> headers = new HashMap<>();
        headers.put("ContentType",ContentType);
        headers.put("host",host);

        return headers;

    }
}
