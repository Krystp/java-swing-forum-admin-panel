/* Show all users */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {
    String user;
    public void selectMethod(Connection conn) {
        Statement stmt = null;
        String query = "select * from users";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user = rs.getString("user");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String creation_datetime = rs.getString("creation_datetime");


                System.out.println("|" + user + " | " + password + " | " + email + " | " + creation_datetime +"|");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    public String getUser() {
        return user;
    }
}
