package audience_management_test_data;

import java.util.HashMap;
import java.util.Map;

public class OrganizerNotFoundResponse {

    public static Map<String,String> organizerNotFound(String error_code,String concept){

        Map<String,String> organizernotfound = new HashMap<>();

        organizernotfound.put("error_code",error_code);
        organizernotfound.put("concept",concept);

        return organizernotfound;

    }
}
