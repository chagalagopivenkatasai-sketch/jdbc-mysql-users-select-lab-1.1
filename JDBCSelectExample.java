import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCSelectExample {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/xyzdatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        
        // try-with-resources for auto resource management
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            System.out.println("Connected to database successfully!");

            String sqlQuery = "SELECT * FROM users";

            try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {

                System.out.println("User List:");
                
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String email = resultSet.getString("email");

                    System.out.println("User ID: " + id + ", Username: " + username + ", Email: " + email);
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error occurred!");
            e.printStackTrace();
        }
    }
}
