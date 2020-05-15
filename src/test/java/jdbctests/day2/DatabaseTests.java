package jdbctests.day2;

import org.junit.Test;

import java.sql.*;

public class DatabaseTests {
    final String DB_URL = "jdbc:oracle:thin:@54.85.192.130:1521:xe";
    final String DB_USER = "hr";
    final String DB_PASSWORD = "hr";

    @Test
    public void getEmployeesData() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        String QUERY = "SELECT * FROM employees";
        ResultSet resultSet = statement.executeQuery(QUERY);

    }
}
