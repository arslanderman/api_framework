package utilities;

import  org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    // 1 method : convert jSon to java object (de-serialization)

    public static  <T> T convertJsonToJavaObject(String json,Class<T> cls){//generic method

        T javaResult= null;

        try {
            javaResult=  mapper.readValue(json,cls);
        } catch (IOException e) {
            System.out.println("Json couldn't be converted to Java Object " + e.getMessage());
        }
        return javaResult;
    }

    // 2 method : convert java object to jSon (serialization)

    public static String convertJavaObjectToJson(Object obj){

        String jsonResult = null;

        try {
            jsonResult = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            System.out.println("Java Object couldn't be converted to Json " + e.getMessage());
        }

        return jsonResult;
    }


}
