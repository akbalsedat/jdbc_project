package jdbctests;

import java.sql.*;

public class  Main {
    public static void main(String[] args) throws SQLException {
        String oracleDbUrl = "jdbc:oracle:thin:@ec2-18-215-144-143.compute-1.amazonaws.com:1521:xe";
        String oracleDbUsername = "hr";
        String oracleDbPassword = "hr";

        Connection connection = DriverManager.getConnection(oracleDbUrl, oracleDbUsername, oracleDbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM COUNTRIES");
        while (resultSet.next()) {
            System.out.println(resultSet.getString( 1) + " " +resultSet.getString(2) + " "
                    + resultSet.getString(3));
        }

            // close connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
