package jdbctests.day3;

import org.junit.Test;

import java.sql.*;

public class JDBCPractice {
    final String DB_URL = "jdbc:oracle:thin:@54.196.47.224:1521:xe";
    final String DB_USER = "hr";
    final String DB_PASSWORD = "hr";

    @Test
    public void connectToDB() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        //connection.setAutoCommit(false); to disable auto commit
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        // resultSet.next() returns boolean value and move pointer to the next row
        // in resultset if it rps
        resultSet.next();

        String query = "UPDATE employees SET first_name = 'Nazim', last_name = 'Hikmet' WHERE employee_id = 100";
        statement.executeUpdate(query);
        //statement.executeUpdate("UPDATE employees SET first_name = 'Nazim', last_name = 'Hikmet' WHERE employee_id IN (103,104,105)");

        while (resultSet.next()) {
            System.out.println(resultSet.getString( 1) + " " +resultSet.getString(2) + " "
                    + resultSet.getString(3));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void preparedStatementTest() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String query = "SELECT * FROM employees WHERE last_name = ? AND first_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "King");
        preparedStatement.setString(2, "Steven");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.println(resultSet.getString("first_name") + " " +
                    resultSet.getString("last_name"));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void metaDataTest() throws SQLException {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees"))
        {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData(); // rest set properties, no data itself
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            while (resultSet.next()) {
                for (int columnIndex = 1; columnIndex < resultSetMetaData.getColumnCount(); columnIndex++) {
                    System.out.print(resultSet.getObject(columnIndex) + " ");
                }
                System.out.println();
            }
            System.out.println("databaseMetaData = " + databaseMetaData);

        }
    }
}
