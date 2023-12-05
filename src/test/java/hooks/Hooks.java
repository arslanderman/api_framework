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

    public  static List<Integer> listOfOrganizers;
    public static List<String> listOfSubscriptionId;
    public static List<String> listOfSubscriptionIdWithOrg;
    public static List<Integer> listOfIsActive;
    public static List<Object> listOfEvent;
    public static List<Object> listOfSession;
    public static List<Object> listOfSalesPhase;
    public static List<String> listOfBoughtSubscriptions;


    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
   static Map<String,String> headers;
    private static boolean isLastScenario = false;
   private static AtomicInteger scenarioCounter = new AtomicInteger(0);
   // private static int numberOfScenarios;





/*
    @Before(order=1,value ="@tag_group_create")
    public void beforeApi(){
        //audienceManagementSetUp();
        //spec.pathParams("first","setting","second","tag","third","group","fourth","create");
        //headers = header(ConfigReader.getProperty("ContentType"),ConfigReader.getProperty("host"));
        audienceManagementSetUp();
        spec.pathParams("first","setting","second","tag","third","group","fourth","create");

    }

 */
    /*
    @Before(value ="@tag_create")
    public void beforeApiTagCreate(){
        audienceManagementSetUp();
        spec.pathParams("first","setting","second","tag","third","create");
    }

     */


    @Before(order = 0, value = "@subscriptions_delete")
    public static void setUpTestData() throws SQLException {
        createConnection();
        initializeTestData();
    }




    @Before(order = 1,value ="@subscriptions_unarchive")
    public static void beforeApiHeaders(){
        headers = headersForPost(ConfigReader.getProperty("ContentType"),ConfigReader.getProperty("host"));

    }

    @Before(order = 2,value ="@subscriptions_unarchive")
    public static void connection() throws SQLException {
        createConnection();

    }
    private static int numberOfScenarios = 4;
    @After(order = 0, value = "@subscriptions_unarchive")
    public static void cleanup(Scenario scenario) throws SQLException {
        // Update the database only if it's not the last scenario
        if (!isLastScenario) {
            DBUtils.executeUpdate(ConfigReader.getProperty("query_scenarios"));
        }
        System.out.println("Incrementing scenario counter: " + scenarioCounter.get());
        // Set isLastScenario to true when the last scenario is encountered
        if (scenario.isFailed() || scenarioCounter.incrementAndGet() == numberOfScenarios) {
            isLastScenario = true;
        }
    }
    private static void initializeTestData() {
        listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"), "id_organizer");
        listOfSubscriptionId = getColumnDataAsString(ConfigReader.getProperty("query_subscription_setting_id"), "id");
        listOfSubscriptionIdWithOrg = getColumnDataAsString(ConfigReader.getProperty("query_subscription_id_link_to_org"), "id");
        listOfIsActive = getColumnDataAsInteger(ConfigReader.getProperty("query_subscription_is_active"), "is_active");
        listOfEvent = getColumnData(ConfigReader.getProperty("query_subscription_event"), "event_id");
        listOfSession = getColumnData(ConfigReader.getProperty("query_subscription_session"), "session_id");
        listOfSalesPhase = getColumnData(ConfigReader.getProperty("query_subscription_sales_phase"), "sales_phase_id");
        listOfBoughtSubscriptions = getColumnDataAsString(ConfigReader.getProperty("query_list_of_bought_subscriptions"), "subscription_setting_id");
        System.out.println("eeeeeeeeeee"+listOfSubscriptionId);
    }


/*
    static {
        System.out.println("Initializing Hooks class...");
        try {
            initializeTestData();
        } catch (Exception e) {
            System.err.println("Failed to initialize test data: " + e.getMessage());
            e.printStackTrace();
            throw e; // Rethrow the exception to cause ExceptionInInitializerError
        }
    }

 */

/*
    @Before(order = 0, value = "@subscriptions_delete")
    public static void setUpTestData() throws SQLException {
        createConnection();
        initializeTestData();
    }
    private static void initializeTestData() {
        // Retrieve necessary data from the database or other sources
        listOfOrganizers = getColumnDataAsInteger(ConfigReader.getProperty("query_org_id"), "id_organizer");
        listOfSubscriptionId = getColumnDataAsString(ConfigReader.getProperty("query_subscription_setting_id"), "id");
        listOfSubscriptionIdWithOrg = getColumnDataAsString(ConfigReader.getProperty("query_subscription_id_link_to_org"), "id");
        listOfIsActive = getColumnDataAsInteger(ConfigReader.getProperty("query_subscription_is_active"), "is_active");
        listOfEvent = getColumnData(ConfigReader.getProperty("query_subscription_event"), "event_id");
        listOfSession = getColumnData(ConfigReader.getProperty("query_subscription_session"), "session_id");
        listOfSalesPhase = getColumnData(ConfigReader.getProperty("query_subscription_sales_phase"), "sales_phase_id");
        listOfBoughtSubscriptions = getColumnDataAsString(ConfigReader.getProperty("query_list_of_bought_subscriptions"), "subscription_setting_id");

        // ... (initialize other lists)
    }

 */

/*
    @Before(order = 3,value ="@tags_linked_to_entity")
    public static void beforeExecuteQuery( String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Before(order = 4,value ="@tags_linked_to_entity")
    public static void beforeCloseConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



 */



















    /*
    @After
    public void tearDown(Scenario scenario) {

        System.out.println("After Hooks");
        //adding reports that is generated when a scenrio fails
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "failed scenerio");
            Driver.closeDriver();

        }
    }

     */

}
