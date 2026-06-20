import java.sql.*;

public class DBTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ledger_db"; // change this
        String user = "root"; // your MySQL username
        String password = "Guljar@2003"; // your MySQL password

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Database Connected Successfully!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection Failed: " + e.getMessage());
        }
    }
}
