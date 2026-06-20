import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
public class InsertUser {

    public static boolean registerUser(String name, String email, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean isRegistered = false;

        try {
            // Get connection from DBConnection class
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ledger_db",
                "root",
                "Guljar@2003"
            );

            // SQL query to insert data
            String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            // Execute update
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ User registered successfully!");
                isRegistered = true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error while inserting user data!");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isRegistered;
    }

    // You can test this file independently
    public static void main(String[] args) {
        registerUser("Shaik Guljar", "guljar@example.com", "password123");
    }
}
