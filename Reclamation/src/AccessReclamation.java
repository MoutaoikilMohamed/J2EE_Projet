import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AccessReclamation frame = new AccessReclamation();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AccessReclamation() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 872, 675);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(-18, 0, 866, 82);
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
        lblNewLabel_1.setBounds(353, 169, 231, 24);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(AccessReclamation.class.getResource("/image/Gestionnaire.png")));
        lblNewLabel_2.setBounds(411, 93, 86, 66);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_1_1_1 = new JLabel("Liste des reclamations");
        lblNewLabel_1_1_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
        lblNewLabel_1_1_1.setBounds(353, 203, 231, 24);
        contentPane.add(lblNewLabel_1_1_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(23, 272, 812, 171);
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

        // Charger les données initiales dans la table
        loadReclamationData();

        JButton btnNewButton = new JButton("Vérifier la Réclamation");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(255, 128, 128));
        btnNewButton.setFont(new Font("Sylfaen", Font.PLAIN, 13));
        btnNewButton.setBounds(180, 460, 200, 31);
        contentPane.add(btnNewButton);

        JTextField textField = new JTextField();
        textField.setBounds(26, 460, 150, 31);
        contentPane.add(textField);

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
    }

    // Méthode pour charger les données de réclamation dans la table
    private void loadReclamationData() {
        Connection conn = null;
        try {
            conn = ConnectionDB.getConnection();

            String sql = "SELECT * FROM Reclamation";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Effacer les données existantes dans le modèle

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nom = resultSet.getString("nom");
                String type = resultSet.getString("type");
                String localisation = resultSet.getString("localisation");
                Date date_creation = resultSet.getDate("date_creation");
                Date date_resolution = resultSet.getDate("date_resolution");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");
                String CIN = resultSet.getString("CIN");

                model.addRow(new Object[]{id, nom, type, localisation, date_creation, date_resolution, status, CIN});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    // Implémentation de la méthode de l'interface StatusUpdateListener pour réagir aux mises à jour de statut
    public void onStatusUpdated(int reclamationId, String newStatus) {
        // Mettre à jour la colonne "status" dans la table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            int id = (int) model.getValueAt(row, 0);
            if (id == reclamationId) {
                model.setValueAt(newStatus, row, 6); // Mettre à jour la colonne "status"
                break;
            }
        }
    }
}
