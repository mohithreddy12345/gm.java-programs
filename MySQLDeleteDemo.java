import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLDeleteDemo {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        String deleteSQL = "DELETE FROM users WHERE id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                 PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {

                int targetId = 2;
                pstmt.setInt(1, targetId);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Row deleted successfully.");
                } else {
                    System.out.println("No rows were deleted.");
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Add the MySQL Connector/J dependency to your project.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error during database deletion: " + e.getMessage());
            e.printStackTrace();
        }
    }
}