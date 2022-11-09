package jdbcTests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {

    String dbUrl = "jdbc:oracle:thin:@44.207.1.165:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void tests2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        // Helps our java project connect to database


        Statement statement = connection.createStatement();
        // Helps to write and execute SQL query


        ResultSet resultSet = statement.executeQuery("select first_name, " +
                "last_name, salary, job_id from employees where rownum <6");
        //A data structure where we can store the data that came from database


        //in order to get column names we need resultsetmetadata
        ResultSetMetaData rsmd = resultSet.getMetaData();


        //list of maps to keep all information
        List<Map<String,Object>> queryData = new ArrayList<>();


        //number of columns
        int colCount = rsmd.getColumnCount();


        //loop through each row
        while(resultSet.next()) {


            Map<String, Object> row = new HashMap<>();

            //some code to fill the dynamically
            for (int i = 1; i <= colCount; i++) {

                row.put(rsmd.getColumnName(i), resultSet.getObject(i));

            }


            //add ready map row to the list
            queryData.add(row);



        }

        for (Map<String, Object> row: queryData) {

            System.out.println(row.toString());
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

}
