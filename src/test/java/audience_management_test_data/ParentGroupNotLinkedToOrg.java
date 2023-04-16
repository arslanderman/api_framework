package audience_management_test_data;

import java.util.HashMap;
import java.util.Map;

public class ParentGroupNotLinkedToOrg {

    public static Map<String,String> parentNotLinked(String error_code){

        Map<String,String> parentNotLinked = new HashMap<>();

        parentNotLinked.put("error_code",error_code);


        return parentNotLinked;

    }
}
