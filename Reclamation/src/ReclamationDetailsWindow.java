import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public ReclamationDetailsWindow(int ID, String nom, String type, String localisation, Date date_creation, Date date_resolution, String description, String status, String CIN) {
        this.ID = ID;
        this.nom = nom;
        this.type = type;
        this.localisation = localisation;
        this.date_creation = date_creation;
        this.date_resolution = date_resolution;
        this.description = description;
        this.status = status;
        this.CIN = CIN;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblDetails = new JLabel("Détails de la réclamation (ID: " + ID + ")");
        lblDetails.setHorizontalAlignment(SwingConstants.CENTER);
        lblDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDetails.setBounds(60, 11, 300, 30);
        contentPane.add(lblDetails);

        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
        detailsArea.append("Nom: " + nom + "\n");
        detailsArea.append("Type: " + type + "\n");
        detailsArea.append("Localisation: " + localisation + "\n");
        detailsArea.append("Date de Réclamation: " + date_creation + "\n");
        detailsArea.append("Description: " + description + "\n");
        detailsArea.append("Status: " + status + "\n");
        detailsArea.append("CIN: " + CIN + "\n");
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setBounds(40, 60, 360, 200);
        contentPane.add(scrollPane);

        JButton btnRefuser = new JButton("Refuser");
        btnRefuser.setForeground(Color.WHITE);
        btnRefuser.setBackground(new Color(255, 128, 128));
        btnRefuser.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRefuser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateReclamationStatus(ID, "Refusée");
                btnRefuser.setEnabled(false);
                dispose();
            }
        });
        btnRefuser.setBounds(60, 280, 120, 30);
        contentPane.add(btnRefuser);

        JButton btnAccepter = new JButton("Accepter");
        btnAccepter.setForeground(Color.WHITE);
        btnAccepter.setBackground(new Color(255, 128, 128));
        btnAccepter.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAccepter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateReclamationStatus(ID, "Acceptée");
                updateResolutionDate(ID, new Date());
                btnRefuser.setEnabled(false);
                btnAccepter.setEnabled(false);
                dispose();
            }
        });
        btnAccepter.setBounds(240, 280, 120, 30);
        contentPane.add(btnAccepter);
    }

    private void updateReclamationStatus(int id, String newStatus) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            String sql = "UPDATE Reclamation SET status = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, newStatus);
            statement.setInt(2, id);
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
}
