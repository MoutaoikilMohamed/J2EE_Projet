import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AccessReclamation extends JFrame implements StatusUpdateListener {
    private JPanel contentPane;
    private JTable table;
    private JLabel NBAccept;
    private JLabel NRrefus;
    private JLabel NCours;
    private static String CIN;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AccessReclamation frame = new AccessReclamation(CIN);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    
                }
            }
        });
    }

    public AccessReclamation(String CIN) {
        this.CIN = CIN;  

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1064, 727);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(70, 0, 866, 76);
        lblNewLabel.setIcon(new ImageIcon(AccessReclamation.class.getResource("/image/back.PNG")));
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1_1 = new JLabel("Interface d'exploration");
        lblNewLabel_1_1.setForeground(new Color(0, 147, 0));
        lblNewLabel_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(23, 103, 231, 24);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1 = new JLabel("Espace Gestionnaire");
        lblNewLabel_1.setForeground(new Color(0, 196, 0));
        lblNewLabel_1.setFont(new Font("Sitka Small", Font.BOLD, 17));
        lblNewLabel_1.setBounds(427, 169, 231, 24);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(AccessReclamation.class.getResource("/image/Gestionnaire.png")));
        lblNewLabel_2.setBounds(498, 92, 86, 66);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_1_1_1 = new JLabel("Liste des reclamations");
        lblNewLabel_1_1_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
        lblNewLabel_1_1_1.setBounds(437, 204, 231, 24);
        contentPane.add(lblNewLabel_1_1_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(11, 271, 1020, 171);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "ID", "Nom", "Type", "Localisation", "date création", "date résolution", "Status", "CIN"
                }
        ));
        table.setRowHeight(table.getRowHeight() + 10);
        JTableHeader header = table.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14)); 
        loadReclamationData(CIN);
        
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(AccessReclamation.class.getResource("/image/back - Copie.PNG")));
        lblNewLabel_3.setBounds(529, 0, 866, 76);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_3_1 = new JLabel("");
        lblNewLabel_3_1.setIcon(new ImageIcon(AccessReclamation.class.getResource("/image/back - Copie.PNG")));
        lblNewLabel_3_1.setBounds(-14, -25, 150, 124);
        contentPane.add(lblNewLabel_3_1);
        
        JPanel borderPanel = new JPanel();
        borderPanel.setBorder(BorderFactory.createTitledBorder("Statistiques"));
        borderPanel.setBounds(10, 465, 316, 212); 
        contentPane.add(borderPanel);
        borderPanel.setLayout(null);

  
        
        JLabel lblRclamationRefuse = new JLabel("Réclamation Refusée :");
        lblRclamationRefuse.setForeground(Color.BLACK);
        lblRclamationRefuse.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
        lblRclamationRefuse.setBounds(6, 74, 159, 46);
        borderPanel.add(lblRclamationRefuse);
        
        JLabel lblRclamationEnCours = new JLabel("Réclamation en cours :");
        lblRclamationEnCours.setForeground(Color.BLACK);
        lblRclamationEnCours.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
        lblRclamationEnCours.setBounds(6, 142, 159, 46);
        borderPanel.add(lblRclamationEnCours);
        
        JLabel lblRclamationAccept = new JLabel("Réclamation Accepté :");
        lblRclamationAccept.setForeground(Color.BLACK);
        lblRclamationAccept.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
        lblRclamationAccept.setBounds(6, 11, 152, 46);
        borderPanel.add(lblRclamationAccept);
        
        NBAccept = new JLabel("1");
        NBAccept.setBounds(180, 11, 33, 46);
        borderPanel.add(NBAccept);
        NBAccept.setForeground(new Color(0, 255, 0));
        NBAccept.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
        
        NRrefus = new JLabel(); 
        NRrefus.setForeground(new Color(255, 0, 0));
        NRrefus.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
        NRrefus.setBounds(180, 74, 33, 46);
        borderPanel.add(NRrefus);
        
        NCours = new JLabel("1");
        NCours.setForeground(new Color(0, 0, 0));
        NCours.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
        NCours.setBounds(180, 142, 33, 46);
        borderPanel.add(NCours);
        
        JPanel borderPanel_1 = new JPanel();
        borderPanel_1.setBorder(BorderFactory.createTitledBorder("Intervention"));
        borderPanel_1.setBounds(707, 465, 324, 212);
        contentPane.add(borderPanel_1);
        borderPanel_1.setLayout(null);
        
        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("ID Reclamation");
        lblNewLabel_1_1_1_1_1.setBounds(100, 21, 184, 47);
        borderPanel_1.add(lblNewLabel_1_1_1_1_1);
        lblNewLabel_1_1_1_1_1.setForeground(Color.BLACK);
        lblNewLabel_1_1_1_1_1.setFont(new Font("Segoe UI Light", Font.BOLD, 12));
                        
                        
                                JTextField textField = new JTextField();
                                textField.setBounds(33, 79, 223, 40);
                                borderPanel_1.add(textField);
                        
                        JLabel lblNewLabel_1_1_1_1 = new JLabel("s");
                        lblNewLabel_1_1_1_1.setBounds(6, 150, 278, 67);
                        borderPanel_1.add(lblNewLabel_1_1_1_1);
                        lblNewLabel_1_1_1_1.setForeground(Color.BLACK);
                        lblNewLabel_1_1_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 6));
                        
                                JButton btnNewButton = new JButton("Traiter la réclamation");
                                btnNewButton.setBounds(66, 150, 157, 41);
                                borderPanel_1.add(btnNewButton);
                                btnNewButton.setForeground(new Color(255, 255, 255));
                                btnNewButton.setBackground(new Color(255, 128, 128));
                                btnNewButton.setFont(new Font("Sylfaen", Font.PLAIN, 13));
                        
                        

                            btnNewButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        String textFieldText = textField.getText();
                                        if (textFieldText.isEmpty()) {
                                            JOptionPane.showMessageDialog(contentPane, "Veuillez saisir un ID.");
                                            return;
                                        }

                                        int ID = Integer.parseInt(textFieldText);

                                        Connection conn = null;
                                        PreparedStatement statement = null;
                                        ResultSet resultSet = null;
                                        try {
                                            conn = ConnectionDB.getConnection();
                                            String sql = "SELECT * FROM reclamation WHERE ID = ?";
                                            statement = conn.prepareStatement(sql);
                                            statement.setInt(1, ID);
                                            resultSet = statement.executeQuery();

                                            if (resultSet.next()) {
                                                String nom = resultSet.getString("nom");
                                                String type = resultSet.getString("type");
                                                String localisation = resultSet.getString("localisation");
                                                Date dateCreation = resultSet.getDate("date_creation");
                                                Date dateResolution = resultSet.getDate("date_resolution");
                                                String description = resultSet.getString("Description");
                                                String status = resultSet.getString("status");
                                                String cin = resultSet.getString("CIN");

                                                String motif = null;
                                                ReclamationDetailsWindow detailsWindow = new ReclamationDetailsWindow(ID, nom, type, localisation, dateCreation, dateResolution, description, status, cin, motif);
                                                detailsWindow.setStatusUpdateListener(AccessReclamation.this); // Set the listener
                                                detailsWindow.setVisible(true);
                                            } else {
                                                JOptionPane.showMessageDialog(contentPane, "Aucune réclamation trouvée pour l'ID spécifié.");
                                            }
                                        } catch (NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(contentPane, "Veuillez saisir un ID valide.");
                                        } finally {
                                            if (resultSet != null) resultSet.close();
                                            if (statement != null) statement.close();
                                            if (conn != null) conn.close();
                                        }
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });

        setVisible(true);
        Count();

    }
    private String fetchUserServiceType(String CIN) {
        String serviceType = null;
        String fetchServiceTypeQuery = "SELECT service FROM users WHERE CIN=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmtServiceType = conn.prepareStatement(fetchServiceTypeQuery)) {
            pstmtServiceType.setString(1, CIN);
            try (ResultSet rsServiceType = pstmtServiceType.executeQuery()) {
                if (rsServiceType.next()) {
                    serviceType = rsServiceType.getString("service");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceType;
    }

    public void Count() {
        try {
            Connection connection = ConnectionDB.getConnection();
            String sqlA = "SELECT count(*) FROM  reclamation WHERE status='Acceptée' ";
            String sqlR = "SELECT count(*) FROM  reclamation WHERE status='Refusée' ";
            String sqlC = "SELECT count(*) FROM  reclamation WHERE status='En cours' ";
            try (PreparedStatement statement = connection.prepareStatement(sqlA)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1); 
                        NRrefus.setText(String.valueOf(count));
                    } else {
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                }
                
            }
            try (PreparedStatement statement = connection.prepareStatement(sqlR)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1); 
                        NBAccept.setText(String.valueOf(count));
                    } else {
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                }
                
            }
            try (PreparedStatement statement = connection.prepareStatement(sqlC)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1); 
                        NCours.setText(String.valueOf(count));
                    } else {
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        
        
    }
    
    private void loadReclamationData(String CIN) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = ConnectionDB.getConnection();
            String serviceType = fetchUserServiceType(CIN);

            String sql = "SELECT * FROM Reclamation WHERE type=?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, serviceType);
            resultSet = statement.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nom = resultSet.getString("nom");
                String type = resultSet.getString("type");
                String localisation = resultSet.getString("localisation");
                Date date_creation = resultSet.getDate("date_creation");
                Date date_resolution = resultSet.getDate("date_resolution");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");
                String CIN1 = resultSet.getString("CIN");

                model.addRow(new Object[]{id, nom, type, localisation, date_creation, date_resolution, status, CIN1});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ConnectionDB.closeConnection(conn);
        }
    }
    public void onStatusUpdated(int reclamationId, String newStatus) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            int id = (int) model.getValueAt(row, 0);
            if (id == reclamationId) {
                model.setValueAt(newStatus, row, 6); 
                break;
            }
        }
    }

