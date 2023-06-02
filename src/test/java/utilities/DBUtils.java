package utilities;




import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class  DBUtils {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    /**
     * DBUtils.createConnection(); -> to connect to teh database
     */
    public static void createConnection() throws  SQLException {

        //    connection : used to connect to DB
        //    statement : used to write queries
        //    resultSet : used to perform DB actions such as going to specific rows, get data as string, get data as object
        String url = "jdbc:mysql://localhost:8181/prestashop";
        String username="prestashop";
        String password="prestashop";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }
    /**
     * DBUtils.executeQuery(String query); -> Execute the query and store is the result set object
     */
    public static void executeQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //    used to close the connectivity
    public static void closeConnection() {
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

    // This method returns the number fo row in a table in the database
    public static int getRowCount() throws Exception {
        resultSet.last();
        int rowCount = resultSet.getRow();
        return rowCount;
    }
    /**
     * @return returns a single cell value. If the results in multiple rows and/or
     *         columns of data, only first column of the first row will be returned.
     *         The rest of the data will be ignored
     */
    public static Object getCellValue(String query) {
        return getQueryResultList(query).get(0).get(0);
    }
    /**
     * @return returns a list of Strings which represent a row of data. If the query
     *         results in multiple rows and/or columns of data, only first row will
     *         be returned. The rest of the data will be ignored
     */
    public static List<Object> getRowList(String query)  {
        return getQueryResultList(query).get(0);
    }
    /**
     * @return returns a map which represent a row of data where key is the column
     *         name. If the query results in multiple rows and/or columns of data,
     *         only first row will be returned. The rest of the data will be ignored
     */
    public static Map<String, Object> getRowMap(String query)   {
        return getQueryResultMap(query).get(0);
    }
    /**
     * @return returns query result in a list of lists where outer list represents
     *         collection of rows and inner lists represent a single row
     */
    public static List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }
    /**
     * @return list of values of a single column from the result set
     */
    public static List<Object> getColumnData(String query, String column) {
        executeQuery(query);
        List<Object> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                rowList.add(resultSet.getObject(column));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }
    /**
     * @return returns query result in a list of maps where the list represents
     *         collection of rows and a map represents represent a single row with
     *         key being the column name
     */
    public static List<Map<String, Object>> getQueryResultMap(String query) {
        executeQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }
    /*
     * @return List of columns returned in result set
     */
    public static List<String> getColumnNames(String query) {
        executeQuery(query);
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    public static List<String> getColumnDataAsString(String query,String columnname){

        List<String> extratedData = new ArrayList<>();
        List<Object> columnData = getColumnData(query,columnname);
        for (int i = 0; i < columnData.size(); i++) {
            extratedData.add(columnData.get(i).toString());

        }
        return extratedData;
    }

    public static List<Integer> getColumnDataAsInteger(String query,String columnname){

        List<Integer> extratedData = new ArrayList<>();
        List<Object> columnData = getColumnData(query,columnname);
        for (int i = 0; i < columnData.size(); i++) {
            extratedData.add(Integer.parseInt(columnData.get(i).toString()));

        }
        return extratedData;
    }

    public static void main(String[] args) throws SQLException {
        createConnection();
        String query = "select * from core_tag";
        List<Object> data = getColumnData(query,"name");
        System.out.println(data);

    }



}
