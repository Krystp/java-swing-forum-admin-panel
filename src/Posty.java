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

public class Posty {

    JFrame frame;
    private JTextField textFieldContent;
    private JTable table;
    private JTextField textFieldSearch;
    private JComboBox comboBox;
    private JComboBox comboBoxSearch;

    /**
     * @wbp.parser.entryPoint
     */

    Connection conn = null;
    private JTextField textFieldpostID;

    public void refreshTable() {
        try {
            Statement stmt = null;
            String query = "select IDpost,content,datetime from forumposts";
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
            String query = "select IDpost,content,datetime from forumposts";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                comboBox.addItem(rs.getString("IDpost"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Posty() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:55555/forum?user=root&serverTimezone=UTC");

            //System.out.println("Jest polaczenie :))");

            //conn.close();

            frame = new JFrame();
            frame.setTitle("Posty");
            frame.setSize(713, 440);
            frame.getContentPane().setLayout(null);

            textFieldContent = new JTextField();
            textFieldContent.setBounds(132, 124, 148, 106);
            frame.getContentPane().add(textFieldContent);
            textFieldContent.setColumns(10);

            JButton btnUpdateButton = new JButton("Aktualizuj");
            btnUpdateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        PreparedStatement pst = null;
                        String query = "update forumposts set content= '"+textFieldContent.getText()+"' where IDpost= '"+textFieldpostID.getText()+"'";
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
                            String query = "delete from forumposts where IDpost='"+textFieldpostID.getText()+"'";
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
                        String query = "insert into forumposts (content,datetime,IDuser,IDthread,IDcategory) values (?,?,3,1,1)";
                        pst = conn.prepareStatement(query);
                        pst.setString(1,textFieldContent.getText());
                        pst.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));

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

            JLabel lblContent = new JLabel("Content:");
            lblContent.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblContent.setBounds(10, 121, 112, 25);
            frame.getContentPane().add(lblContent);

            comboBox = new JComboBox();
            comboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        PreparedStatement pst = null;
                        String query = "select IDpost,content,datetime from forumposts where IDpost=?";
                        pst = conn.prepareStatement(query);
                        pst.setString(1,(String)comboBox.getSelectedItem());
                        ResultSet rs = pst.executeQuery();

                        while (rs.next()) {
                            textFieldpostID.setText(rs.getString("IDpost"));
                            textFieldContent.setText(rs.getString("content"));
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
                        String postID_ = (table.getModel().getValueAt(row, 0)).toString();
                        PreparedStatement pst = null;
                        String query = "select IDpost,content,datetime from forumposts where IDpost='"+postID_+"'";
                        pst = conn.prepareStatement(query);
                        ResultSet rs = pst.executeQuery();

                        while (rs.next()) {
                            textFieldpostID.setText(rs.getString("IDpost"));
                            textFieldContent.setText(rs.getString("content"));
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
                        String query = "select IDpost,content,datetime from forumposts where "+selection+" like ?";
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
            comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"IDpost", "content"}));
            comboBoxSearch.setBounds(312, 37, 153, 35);
            frame.getContentPane().add(comboBoxSearch);

            JButton btnRefresh = new JButton("Odswiez");
            btnRefresh.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        Statement stmt = null;
                        String query = "select IDpost,content,datetime from forumposts";
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

            JLabel lblpostID = new JLabel("PostID:");
            lblpostID.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblpostID.setBounds(10, 86, 112, 25);
            frame.getContentPane().add(lblpostID);

            textFieldpostID = new JTextField();
            textFieldpostID.setColumns(10);
            textFieldpostID.setBounds(132, 89, 148, 25);
            frame.getContentPane().add(textFieldpostID);
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