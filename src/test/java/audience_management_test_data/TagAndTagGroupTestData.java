package audience_management_test_data;

import java.util.HashMap;
import java.util.Map;

public class TagAndTagGroupTestData {

    public static Map<String,Object> tagAndTagGroups(Integer organizerId, String id, String name,
                                                     String parentId, String color, Boolean active, String tagGroupId ){

        Map<String,Object> expectedDataForAllTagsAndTagGroup = new HashMap<>();

        if(organizerId!= null){
            expectedDataForAllTagsAndTagGroup.put("organizerId",organizerId);
        }

        if(id!= null){
            expectedDataForAllTagsAndTagGroup.put("id",id);
        }
        if(name!= null){
            expectedDataForAllTagsAndTagGroup.put("name",name);
        }
        if(parentId!= null){
            expectedDataForAllTagsAndTagGroup.put("parentId",parentId);
        }
        if(color!= null){
            expectedDataForAllTagsAndTagGroup.put("color",color);
        }
        if(active!= null){
            expectedDataForAllTagsAndTagGroup.put("active",active);
        }
        if(tagGroupId!= null){
            expectedDataForAllTagsAndTagGroup.put("tagGroupId",tagGroupId);
        }
        return expectedDataForAllTagsAndTagGroup;
    }

    //public static void expectedArray(){

    //  Object array[] = {};
    //}

    public  static Map<Object,Object> response(Object data){

        Map<Object,Object> response = new HashMap<>();
        //Object[] array = {};
        //Object[] expectedArray = {};
        response.put("data",data);

        return response;
    }

}
