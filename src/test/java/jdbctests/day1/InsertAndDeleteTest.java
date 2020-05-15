package jdbctests.day1;

import java.sql.*;

public class InsertAndDeleteTest {

    static String oracleDbUrl = "jdbc:oracle:thin:@ec2-18-215-144-143.compute-1.amazonaws.com:1521:xe";
    static String oracleDbUsername = "hr";
    static String oracleDbPassword = "hr";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(oracleDbUrl, oracleDbUsername, oracleDbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery(
                "UPDATE cybertek15 SET START_DATE = '23-NOV-55' WHERE STUDENT_ID = 9988");


        // close connections
        resultSet.close();
        statement.close();
        connection.close();


    }
}
