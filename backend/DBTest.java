import java.sql.*;

public class DBTest {
    public static void main(String[] args) {
        try (java.sql.Connection conn = DBConnection.getConnection()) {
            System.out.println("✅ Database Connected Successfully (via DBConnection)!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Connection Failed: " + e.getMessage());
        }
    }
}
