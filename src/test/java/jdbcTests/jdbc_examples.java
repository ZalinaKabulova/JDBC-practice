package jdbcTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbUrl = "jdbc:oracle:thin:@44.207.1.165:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        // Helps our java project connect to database


        Statement statement = connection.createStatement();
        // Helps to write and execute SQL query


        ResultSet resultSet = statement.executeQuery("select * from departments");
        //A data structure where we can store the data that came from database

        //move to first row
        //resultSet.next();

        //System.out.println(resultSet.getString(2));
        //display departments table in 10- Administration- 200 - 1700 format

        //code for iterating for each row
        while (resultSet.next()) {

            System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2) + " - " + resultSet.getString(3) + " - " + resultSet.getString(4));
        }
//------------------------------------------------------------------------------

        resultSet = statement.executeQuery("select * from regions");

        while (resultSet.next()) {

            System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2));
        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }


    @DisplayName("ResultSet Methods")
    @Test
    public void Test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT  * from departments");

        //resultSet.last();

        //how to find how many rows we have for the query
        //move to last row
        resultSet.last();

        //get the row count
        int rowCount = resultSet.getRow();

        System.out.println(rowCount);

        //to move before first row after we use .last method
        resultSet.beforeFirst();

        //print all second column information
        while (resultSet.next()){

            System.out.println(resultSet.getString(2));
        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void Test3() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT  * from employees");

        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion());

        //get the resultsetMetadata//rsmd
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many columns we have?
        int colCount = rsMetadata.getColumnCount();
        System.out.println(colCount);

        //getting column names
        System.out.println(rsMetadata.getColumnName(1));  //EMPLOYEE_ID
        System.out.println(rsMetadata.getColumnName(2));  //FIRST_NAME


        //rsMetadata.getColumnName(i)----> gets column name
        //rsMetadata.getColumnCount()----> total number of columns
        //print all the column names dynamically
        for (int i = 1; i <= colCount; i++) {
            System.out.println(rsMetadata.getColumnName(i));

        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }
}
