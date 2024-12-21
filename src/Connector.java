/* Establishing connection to database */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    Connection conn = null;
    String host,port,db,user;
    public void DbConnect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port +"/" + db + "?user=" + user +"&serverTimezone=UTC");

            System.out.println("Jest połączenie :)");

            //conn.close();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    public Connection getConn() {
        return conn;
    }

    public void setProperties(String host, String port, String db, String user) {
        this.host = host;
        this.port = port;
        this.db = db;
        this.user = user;
    }
}
