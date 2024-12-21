/* Creating new users */

import java.sql.*;
import java.time.LocalDateTime;

public class Insert {
    public void insertMethod(Connection conn, String user, String password, String email) {
        Statement stmt = null;
        String query = "select * from users";
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet uprs = stmt.executeQuery(query);
            uprs.moveToInsertRow();
            uprs.updateString("user", user);
            uprs.updateString("password", password);
            uprs.updateString("email", email);
            uprs.updateTimestamp("creation_datetime", Timestamp.valueOf(LocalDateTime.now()));

            uprs.insertRow();
            uprs.beforeFirst();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
