package hooks;

import base_url_set_up.BaseURLForApiAutomation;
import io.cucumber.java.Before;
import utilities.ConfigReader;

import java.sql.*;
import java.util.Map;

import static api_automation_test_data.Headers.headersForPost;


public class Hooks extends BaseURLForApiAutomation {

    //private static Connection connection;
    //private static Statement statement;
    //private static ResultSet resultSet;
   static Map<String,String> headers;

    @Before(order = 1,value ="@dummy_json")
    public static void beforeApiHeaders(){
        headers = headersForPost(ConfigReader.getProperty("ContentType"));

    }

    @Before(order = 2,value ="@api_class")
    public static void connection() throws SQLException {
        //createConnection();

    }

    }





















