import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLInsertDemo {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "email VARCHAR(100) NOT NULL)";

        String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                 PreparedStatement createTableStmt = connection.prepareStatement(createTableSQL);
                 PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {

                createTableStmt.execute();

                insertUsers(insertStmt, "John Doe", "johndoe@example.com");
                insertUsers(insertStmt, "Jane Doe", "janedoe@example.com");

                System.out.println("Data inserted successfully.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Add the MySQL Connector/J dependency to your project.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error during database operation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertUsers(PreparedStatement insertStmt, String name, String email) throws SQLException {
        insertStmt.setString(1, name);
        insertStmt.setString(2, email);
        insertStmt.executeUpdate();
    }
}