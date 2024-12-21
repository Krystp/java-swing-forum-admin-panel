import javax.swing.*;
import java.sql.*;

public class LoginFrame {

	 JPanel panel = new JPanel();
     JLabel label = new JLabel("Podaj dane do logowania");

     JLabel lblLogin = new JLabel("Login:");
     JTextField txfLogin = new JTextField();

     JLabel lblPassword = new JLabel("Haslo:");
     JPasswordField txfPassword = new JPasswordField();


		public void displayGUI() {
        try {
		Connection conn = null;
        String password = null;

        int result = JOptionPane.showConfirmDialog(null,
                    getPanel(),
                    "Forum",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.CANCEL_OPTION)
            System.exit(0);
        
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:55555/forum?user=root&serverTimezone=UTC");

        Statement stmt = null;
        String query = "select password from users where user='"+txfLogin.getText()+"'";

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                password = rs.getString("password");
            }


        while (result == JOptionPane.OK_OPTION & !txfPassword.getText().equals(password)) {
            JOptionPane.showMessageDialog(null,"Zle haslo");
            txfPassword.setText("");
            txfLogin.setText("");
            result = JOptionPane.showConfirmDialog(null,
                    getPanel(),
                    "Forum",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.CANCEL_OPTION)
                System.exit(0);
            stmt = null;
            query = "select password from users where user='"+txfLogin.getText()+"'";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                password = rs.getString("password");
            }
        }
        conn.close();
        } catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    private JPanel getPanel() {

        txfLogin.setColumns(15);

        txfPassword.setColumns(15);

        panel.add(label);
        panel.add(lblLogin);
        panel.add(txfLogin);
        panel.add(lblPassword);
        panel.add(txfPassword);

        return panel;
    }

}