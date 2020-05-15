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

        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
        while (resultSet.next()) {
            System.out.println(resultSet.getString( 1) + " " +resultSet.getString(2) + " "
                    + resultSet.getString(3));
        }

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        System.out.println("*****************************");
        System.out.println("JDBC driver: "+databaseMetaData.getDriverName());
        System.out.println("JDBC driver version: "+databaseMetaData.getDriverVersion());
        System.out.println("Database name: "+databaseMetaData.getDatabaseProductName());
        System.out.println("Database version: "+databaseMetaData.getDatabaseProductVersion());

        System.out.println("*****************************");
        System.out.println("Number of column: "+resultSetMetaData.getColumnCount());
        System.out.println("Label of 1st column: "+resultSetMetaData.getColumnName(1));

        resultSet.beforeFirst();

        for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {
            System.out.printf("%-15s", resultSetMetaData.getColumnName(columnIndex));
        }

        while(resultSet.next()) {
            for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {
                System.out.printf("%-15s", resultSet.getString(columnIndex));
            }
            System.out.println(" ");
        }


        // close connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
