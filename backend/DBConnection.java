import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Get DB connection using environment variables with sensible defaults
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || url.isEmpty()) {
            url = "jdbc:mysql://localhost:3306/ledger_db";
        }
        if (user == null || user.isEmpty()) {
            user = "root";
        }
        if (password == null) {
            password = "Guljar@2003";
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            System.out.println("✅ Database connected successfully (using environment config)!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found. Add the JAR file to classpath.");
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }
}
