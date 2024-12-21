/* Updating users password */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class Update {
    public void updateMethod(Connection conn, String user, String password) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet uprs = stmt.executeQuery("SELECT * FROM users WHERE user LIKE '" + user +"'");
            while (uprs.next()) {
                uprs.updateString("password", password);
                uprs.updateRow();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
