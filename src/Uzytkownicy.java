// Packages to import
import com.mysql.cj.protocol.Resultset;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Uzytkownicy {

    JFrame frame;
    private JTextField textFieldUsername;
    private JTextField textFieldPassword;
    private JTextField textFieldEmail;
    private JTable table;
    private JTextField textFieldSearch;
    private JComboBox comboBox;
    private JComboBox comboBoxSearch;

    /**
     * @wbp.parser.entryPoint
     */

    Connection conn = null;
    private JTextField textFielduserID;

    public void refreshTable() {
        try {
            Statement stmt = null;
            String query = "select * from users";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillComboBox() {
        try {
            Statement stmt = null;
            String query = "select * from users";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                comboBox.addItem(rs.getString("user"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Uzytkownicy() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:55555/forum?user=root&serverTimezone=UTC");

            //System.out.println("Jest polaczenie :))");

            //conn.close();

            frame = new JFrame();
            frame.setTitle("Uzytkownicy");
            frame.setSize(713, 440);
            frame.getContentPane().setLayout(null);

            textFieldUsername = new JTextField();
            textFieldUsername.setBounds(132, 125, 148, 25);
            frame.getContentPane().add(textFieldUsername);
            textFieldUsername.setColumns(10);

            textFieldPassword = new JTextField();
            textFieldPassword.setColumns(10);
            textFieldPassword.setBounds(132, 160, 148, 25);
            frame.getContentPane().add(textFieldPassword);

            textFieldEmail = new JTextField();
            textFieldEmail.setColumns(10);
            textFieldEmail.setBounds(132, 195, 148, 25);
            frame.getContentPane().add(textFieldEmail);

            JButton btnUpdateButton = new JButton("Aktualizuj");
            btnUpdateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        PreparedStatement pst = null;
                        String query = "update users set user= '"+textFieldUsername.getText()+"', password = '"+textFieldPassword.getText()+"', email = '"+textFieldEmail.getText()+"' where IDuser= '"+textFielduserID.getText()+"'";
                        pst = conn.prepareStatement(query);


                        JOptionPane.showMessageDialog(null, "Dane zaktualizowane");

                        pst.execute();

                        pst.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    refreshTable();
                }
            });
            btnUpdateButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
            btnUpdateButton.setBounds(10, 310, 270, 25);
            frame.getContentPane().add(btnUpdateButton);

            JButton btnDeleteButton = new JButton("Usun");
            btnDeleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    int action = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunac dane?","Usuwanie",JOptionPane.YES_NO_OPTION);
                    if (action == 0) {
                        try {
                            PreparedStatement pst = null;
                            String query = "delete from users where email='"+textFieldEmail.getText()+"'";
                            pst = conn.prepareStatement(query);


                            JOptionPane.showMessageDialog(null, "Dane usuniete");

                            pst.execute();

                            pst.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        refreshTable();
                    }
                }
            });
            btnDeleteButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
            btnDeleteButton.setBounds(10, 346, 270, 25);
            frame.getContentPane().add(btnDeleteButton);

            JButton btnSaveButton = new JButton("Zapisz");
            btnSaveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        PreparedStatement pst = null;
                        String query = "insert into users (user,password,email,creation_datetime) values (?,?,?,?)";
                        pst = conn.prepareStatement(query);
                        pst.setString(1,textFieldUsername.getText());
                        pst.setString(2,textFieldPassword.getText());
                        pst.setString(3,textFieldEmail.getText());
                        pst.setTimestamp(4,Timestamp.valueOf(LocalDateTime.now()));

                        JOptionPane.showMessageDialog(null, "Dane zapisane");

                        pst.execute();

                        pst.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    refreshTable();
                }
            });
            btnSaveButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
            btnSaveButton.setBounds(10, 275, 270, 25);
            frame.getContentPane().add(btnSaveButton);

            JLabel lblUsername = new JLabel("User:");
            lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblUsername.setBounds(10, 122, 112, 25);
            frame.getContentPane().add(lblUsername);

            JLabel lblPassword = new JLabel("Haslo:");
            lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblPassword.setBounds(10, 160, 112, 25);
            frame.getContentPane().add(lblPassword);

            JLabel lblEmail = new JLabel("E-mail:");
            lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblEmail.setBounds(10, 195, 112, 25);
            frame.getContentPane().add(lblEmail);

            comboBox = new JComboBox();
            comboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        PreparedStatement pst = null;
                        String query = "select * from users where user=?";
                        pst = conn.prepareStatement(query);
                        pst.setString(1,(String)comboBox.getSelectedItem());
                        ResultSet rs = pst.executeQuery();

                        while (rs.next()) {
                            textFieldUsername.setText(rs.getString("user"));
                            textFieldPassword.setText(rs.getString("password"));
                            textFieldEmail.setText(rs.getString("email"));
                            textFielduserID.setText(rs.getString("IDuser"));
                        }
                        pst.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            comboBox.setBounds(10, 37, 270, 35);
            frame.getContentPane().add(comboBox);

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(302, 93, 387, 273);
            frame.getContentPane().add(scrollPane);

            table = new JTable();
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        int row = table.getSelectedRow();
                        String userID_ = (table.getModel().getValueAt(row, 0)).toString();
                        PreparedStatement pst = null;
                        String query = "select * from users where IDuser='"+userID_+"'";
                        pst = conn.prepareStatement(query);
                        ResultSet rs = pst.executeQuery();

                        while (rs.next()) {
                            textFieldUsername.setText(rs.getString("user"));
                            textFieldPassword.setText(rs.getString("password"));
                            textFieldEmail.setText(rs.getString("email"));
                            textFielduserID.setText(rs.getString("IDuser"));
                        }
                        pst.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            scrollPane.setViewportView(table);

            textFieldSearch = new JTextField();
            textFieldSearch.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        String selection = (String)comboBoxSearch.getSelectedItem();
                        PreparedStatement pst = null;
                        String query = "select * from users where "+selection+" like ?";
                        pst = conn.prepareStatement(query);
                        pst.setString(1,"%" + textFieldSearch.getText() + "%");
                        ResultSet rs = pst.executeQuery();

                        table.setModel(DbUtils.resultSetToTableModel(rs));
                        //while (rs.next()) {
                        //
                        //}
                        pst.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            textFieldSearch.setBounds(501, 37, 163, 35);
            frame.getContentPane().add(textFieldSearch);
            textFieldSearch.setColumns(10);

            comboBoxSearch = new JComboBox();
            comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"IDuser", "user", "password", "email"}));
            comboBoxSearch.setBounds(312, 37, 153, 35);
            frame.getContentPane().add(comboBoxSearch);

            JButton btnRefresh = new JButton("Odswiez");
            btnRefresh.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        Statement stmt = null;
                        String query = "select * from users";
                        stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        table.setModel(DbUtils.resultSetToTableModel(rs));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 18));
            btnRefresh.setBounds(10, 240, 270, 25);

            frame.getContentPane().add(btnRefresh);

            JLabel lbluserID = new JLabel("UserID:");
            lbluserID.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lbluserID.setBounds(10, 87, 112, 25);
            frame.getContentPane().add(lbluserID);

            textFielduserID = new JTextField();
            textFielduserID.setColumns(10);
            textFielduserID.setBounds(132, 90, 148, 25);
            frame.getContentPane().add(textFielduserID);
            frame.setVisible(true);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        refreshTable();
        fillComboBox();
    }
}