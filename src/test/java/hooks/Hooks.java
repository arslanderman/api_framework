package hooks;

import base_url_set_up.CoreBaseURL;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;
import utilities.DBUtils;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static audience_management_test_data.Headers.headersForPost;
import static utilities.DBUtils.*;
import static utilities.DBUtils.getColumnDataAsString;


public class Hooks extends CoreBaseURL {




    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
   static Map<String,String> headers;


    @Before(order = 1,value ="@api_class")
    public static void beforeApiHeaders(){
        headers = headersForPost(ConfigReader.getProperty("ContentType"));

    }

    @Before(order = 2,value ="@api_class")
    public static void connection() throws SQLException {
        //createConnection();

    }

    }





















