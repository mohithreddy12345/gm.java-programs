import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLJDBCDemo {

    // Update these values to match your local MySQL setup.
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    private static final String QUERY = "SELECT id, username, email FROM users";

    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver explicitly.
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(QUERY)) {

                System.out.println("Connected to MySQL database.");

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String email = resultSet.getString("email");

                    System.out.println("User ID: " + id + ", Username: " + username + ", Email: " + email);
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Add the MySQL Connector/J dependency to your project.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection or query failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}