package jdbctests.day1;

import java.sql.*;

public class InsertAndDeleteTest {
    public static void main(String[] args) throws SQLException {
        String oracleDbUrl = "jdbc:oracle:thin:@ec2-18-215-144-143.compute-1.amazonaws.com:1521:xe";
        String oracleDbUsername = "hr";
        String oracleDbPassword = "hr";

        Connection connection = DriverManager.getConnection(oracleDbUrl, oracleDbUsername, oracleDbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
        statement.executeQuery("INSERT INTO cybertek15 VALUES (9876,'Lynne', 'Davis', SYSDATE, 'F');");

        // close connections
        resultSet.close();
        statement.close();
        connection.close();


    }
}
