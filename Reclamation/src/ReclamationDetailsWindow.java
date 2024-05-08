import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ReclamationDetailsWindow extends JFrame {
    private JPanel contentPane;
    private int ID;
    private String nom;
    private String type;
    private String localisation;
    private Date date_creation;
    private Date date_resolution;
    private String description;
    private String status;
    private String CIN;
    private JComboBox<String> motifComboBox; // Renommé motif en motifComboBox

    private JTextArea detailsArea;
    private String motif;

    public ReclamationDetailsWindow(int ID, String nom, String type, String localisation, Date date_creation, Date date_resolution, String description, String status, String CIN, String motif) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.ID = ID;
        this.nom = nom;
        this.type = type;
        this.localisation = localisation;
        this.date_creation = date_creation;
        this.date_resolution = date_resolution;
        this.description = description;
        this.status = status;
        this.CIN = CIN;
        this.motif = motif;
        
        retrieveReclamationDetailsFromDatabase();

        setTitle("Mon Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 846, 482);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/image/back.PNG")));
        lblNewLabel.setBounds(0, 0, 866, 82);
        contentPane.add(lblNewLabel);

        JLabel lblDetails = new JLabel("Détails de la réclamation (ID: " + ID + ")");
        lblDetails.setHorizontalAlignment(SwingConstants.CENTER);
        lblDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDetails.setBounds(242, 115, 300, 30);
        contentPane.add(lblDetails);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(219, 155, 360, 200);
        contentPane.add(scrollPane);

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
        updateDetails(); // Met à jour les détails initiaux
        scrollPane.setViewportView(detailsArea);

        JButton btnRefuser = new JButton("Refuser");
        btnRefuser.setForeground(Color.WHITE);
        btnRefuser.setBackground(new Color(255, 128, 128));
        btnRefuser.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRefuser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (motifComboBox.getSelectedIndex() != 0) {
                    String motifSelectionne = (String) motifComboBox.getSelectedItem();
                    updateReclamationStatus(ID, "Refusée", motifSelectionne);
                    btnRefuser.setEnabled(false);
                    updateDetails(); // Actualise les détails après la mise à jour du statut
                    dispose();
                	JOptionPane.showMessageDialog(contentPane, "Réclamation refusée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(contentPane, "Veuillez sélectionner un motif de refus.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnRefuser.setBounds(279, 365, 120, 30);
        contentPane.add(btnRefuser);

        JButton btnAccepter = new JButton("Accepter");
        btnAccepter.setForeground(Color.WHITE);
        btnAccepter.setBackground(new Color(255, 128, 128));
        btnAccepter.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAccepter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateReclamationStatus(ID, "Acceptée", null);
                updateResolutionDate(ID, new Date());
                btnRefuser.setEnabled(false);
                btnAccepter.setEnabled(false);
                updateDetails(); 
                dispose();
                JOptionPane.showMessageDialog(contentPane, "Réclamation acceptée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnAccepter.setBounds(422, 365, 120, 30);
        contentPane.add(btnAccepter);

        motifComboBox = new JComboBox<>();
        motifComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Aucun motif de refus", "Documentation incomplète ou incorrecte", "Fraude ou fausse déclaration", "Responsabilité non prouvée", "Force majeure ou exclusion spécifique", "Responsabilité prouvée ", "Aucune exclusion spécifique ne s'applique", "Permission de démarche judiciaire", "Autre"}));
        motifComboBox.setToolTipText("");
        motifComboBox.setBounds(314, 405, 200, 30);
        contentPane.add(motifComboBox);

        // Sélectionne le motif de la réclamation
        motifComboBox.setSelectedItem(motif);
    }

    private void retrieveReclamationDetailsFromDatabase() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection(); // Assurez-vous d'avoir la classe ConnectionDB pour établir une connexion à la base de données
            String sql = "SELECT nom, type, localisation, date_creation, date_resolution, description, status, CIN, motif FROM Reclamation WHERE ID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, ID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                nom = resultSet.getString("nom");
                type = resultSet.getString("type");
                localisation = resultSet.getString("localisation");
                date_creation = resultSet.getDate("date_creation");
                date_resolution = resultSet.getDate("date_resolution");
                description = resultSet.getString("description");
                status = resultSet.getString("status");
                CIN = resultSet.getString("CIN");
                motif = resultSet.getString("motif");
            } else {
                JOptionPane.showMessageDialog(null, "Aucune réclamation trouvée avec l'ID : " + ID, "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void updateReclamationStatus(int id, String newStatus, String motif) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            String sql = "UPDATE Reclamation SET status = ?, motif = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, newStatus);
            statement.setString(2, motif);
            statement.setInt(3, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Status de la réclamation " + id + " mis à jour à : " + newStatus);
            } else {
                System.out.println("Aucune réclamation trouvée avec l'ID : " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void updateResolutionDate(int id, Date currentDate) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            String sql = "UPDATE Reclamation SET date_resolution = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(currentDate.getTime()));
            statement.setInt(2, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Date de résolution de la réclamation " + id + " mise à jour à : " + currentDate);
            } else {
                System.out.println("Aucune réclamation trouvée avec l'ID : " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void updateDetails() {
        detailsArea.setText(""); 
        detailsArea.append("Nom: " + nom + "\n");
        detailsArea.append("Type: " + type + "\n");
        detailsArea.append("Localisation: " + localisation + "\n");
        detailsArea.append("Date de Réclamation: " + date_creation + "\n");
        detailsArea.append("Description: " + description + "\n");
        detailsArea.append("Status: " + status + "\n");
        detailsArea.append("Motif de refus: " + motif + "\n");
        detailsArea.append("CIN: " + CIN + "\n");
    }
}
