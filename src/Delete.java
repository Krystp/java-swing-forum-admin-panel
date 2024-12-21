/* Deleting users from database */

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public void deleteMethod(Connection conn, String user, String password) {
        Statement stmt = null;
        String query = "DELETE FROM users WHERE user='" + user +"' AND password='" + password + "'";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
