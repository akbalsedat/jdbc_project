package jdbctests;

import java.sql.*;

public class  Main {
    public static void main(String[] args) throws SQLException {
        String oracleDbUrl = "jdbc:oracle:thin:@ec2-18-215-144-143.compute-1.amazonaws.com:1521:xe";
        String oracleDbUsername = "hr";
        String oracleDbPassword = "hr";

        Connection connection = DriverManager.getConnection(oracleDbUrl, oracleDbUsername, oracleDbPassword);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM COUNTRIES");
        while (resultSet.next() != false) {
            System.out.println(resultSet.getString("COUNTRY_ID"));
            System.out.println(resultSet.getString("REGION_ID"));
            System.out.println(resultSet.getString("COUNTRY_NAME"));
        }

            // close connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
