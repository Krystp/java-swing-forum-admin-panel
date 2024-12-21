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

public class Kategorie {

    JFrame frame;
    private JTextField textFieldCategory;
    private JTextField textFieldDescription;
    private JTable table;
    private JTextField textFieldSearch;
    private JComboBox comboBox;
    private JComboBox comboBoxSearch;

    /**
     * @wbp.parser.entryPoint
     */

    Connection conn = null;
    private JTextField textFieldcategoryID;

    public void refreshTable() {
        try {
            Statement stmt = null;
            String query = "select * from forumcategory";
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
            String query = "select * from forumcategory";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                comboBox.addItem(rs.getString("categoryName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Kategorie() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:55555/forum?user=root&serverTimezone=UTC");

            //System.out.println("Jest polaczenie :))");

            //conn.close();

            frame = new JFrame();
            frame.setTitle("Kategorie");
            frame.setSize(713, 440);
            frame.getContentPane().setLayout(null);

            textFieldCategory = new JTextField();
            textFieldCategory.setBounds(132, 146, 148, 25);
            frame.getContentPane().add(textFieldCategory);
            textFieldCategory.setColumns(10);

            textFieldDescription = new JTextField();
            textFieldDescription.setColumns(10);
            textFieldDescription.setBounds(132, 192, 148, 25);
            frame.getContentPane().add(textFieldDescription);

            JButton btnUpdateButton = new JButton("Zaktualizuj");
            btnUpdateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        PreparedStatement pst = null;
                        String query = "update forumcategory set categoryName= '"+textFieldCategory.getText()+"', description = '"+textFieldDescription.getText()+"' where IDcategory= '"+textFieldcategoryID.getText()+"'";
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
                            String query = "delete from forumcategory where IDcategory='"+textFieldcategoryID.getText()+"'";
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

            JButton btnSaveButton = new JButton("Stworz");
            btnSaveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        PreparedStatement pst = null;
                        String query = "insert into forumcategory (categoryName,description,timestamp) values (?,?,?)";
                        pst = conn.prepareStatement(query);
                        pst.setString(1,textFieldCategory.getText());
                        pst.setString(2,textFieldDescription.getText());
                        pst.setTimestamp(3,Timestamp.valueOf(LocalDateTime.now()));

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

            JLabel lblCategoryName = new JLabel("Kategoria:");
            lblCategoryName.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblCategoryName.setBounds(10, 143, 112, 25);
            frame.getContentPane().add(lblCategoryName);

            JLabel lblDescription = new JLabel("Opis:");
            lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblDescription.setBounds(10, 192, 112, 25);
            frame.getContentPane().add(lblDescription);

            comboBox = new JComboBox();
            comboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        PreparedStatement pst = null;
                        String query = "select * from forumcategory where categoryName=?";
                        pst = conn.prepareStatement(query);
                        pst.setString(1,(String)comboBox.getSelectedItem());
                        ResultSet rs = pst.executeQuery();

                        while (rs.next()) {
                            textFieldCategory.setText(rs.getString("categoryName"));
                            textFieldDescription.setText(rs.getString("description"));
                            textFieldcategoryID.setText(rs.getString("IDcategory"));
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
                        String categoryID_ = (table.getModel().getValueAt(row, 0)).toString();
                        PreparedStatement pst = null;
                        String query = "select * from forumcategory where IDcategory='"+categoryID_+"'";
                        pst = conn.prepareStatement(query);
                        ResultSet rs = pst.executeQuery();

                        while (rs.next()) {
                            textFieldCategory.setText(rs.getString("categoryName"));
                            textFieldDescription.setText(rs.getString("description"));
                            textFieldcategoryID.setText(rs.getString("IDcategory"));
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
                        String query = "select * from forumcategory where "+selection+" like ?";
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
            comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"IDcategory", "categoryName", "description"}));
            comboBoxSearch.setBounds(312, 37, 153, 35);
            frame.getContentPane().add(comboBoxSearch);

            JButton btnRefresh = new JButton("Odswiez");
            btnRefresh.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        Statement stmt = null;
                        String query = "select * from forumcategory";
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

            JLabel lblcategoryID = new JLabel("IDKategorii:");
            lblcategoryID.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblcategoryID.setBounds(10, 93, 112, 25);
            frame.getContentPane().add(lblcategoryID);

            textFieldcategoryID = new JTextField();
            textFieldcategoryID.setColumns(10);
            textFieldcategoryID.setBounds(132, 96, 148, 25);
            frame.getContentPane().add(textFieldcategoryID);
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