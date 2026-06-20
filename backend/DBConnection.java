import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ledger_db";
        String user = "root";       // your MySQL username
        String password = "Guljar@2003";  // replace with your MySQL password

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database connected successfully!");

            // Close connection
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found. Add the JAR file to classpath.");
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }
}
