import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reclammation extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField localisationField;
    private JTextField titleField;
    private JComboBox<String> sectorComboBox;
    private TextArea descriptionTextArea;
    private JButton sendButton;
    private String userCIN;

    public Reclammation(String CIN) {
        setTitle("Réclamation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 781, 600);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        initializeComponents();
        fetchUserCIN(CIN);
        loadSectorsFromDatabase();
    }

    private void initializeComponents() {
        JLabel backgroundLabel = new JLabel(new ImageIcon(Reclammation.class.getResource("/image/back.PNG")));
        backgroundLabel.setBounds(10, 0, 854, 89);
        contentPane.add(backgroundLabel);

        JLabel citizenLabel = new JLabel(new ImageIcon(Reclammation.class.getResource("/image/Citoyen.PNG")));
        citizenLabel.setBounds(432, 116, 68, 59);
        contentPane.add(citizenLabel);

        JLabel spaceLabel = new JLabel("Espace Citoyen");
        spaceLabel.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 22));
        spaceLabel.setBounds(356, 186, 250, 30);
        contentPane.add(spaceLabel);

        JLabel localisationLabel = new JLabel("Votre localisation");
        localisationLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        localisationLabel.setBounds(115, 245, 168, 14);
        contentPane.add(localisationLabel);

        localisationField = new JTextField();
        localisationField.setBounds(324, 245, 250, 20);
        contentPane.add(localisationField);
        localisationField.setColumns(10);

        JLabel sectorLabel = new JLabel("Secteur de réclamation");
        sectorLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        sectorLabel.setBounds(115, 297, 168, 14);
        contentPane.add(sectorLabel);

        sectorComboBox = new JComboBox<>();
        sectorComboBox.setBounds(324, 293, 250, 22);
        contentPane.add(sectorComboBox);

        JLabel titleLabel = new JLabel("Titre de réclamation");
        titleLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        titleLabel.setBounds(115, 272, 168, 14);
        contentPane.add(titleLabel);

        titleField = new JTextField(); // Fixed variable declaration
        titleField.setBounds(324, 270, 250, 20);
        contentPane.add(titleField);
        titleField.setColumns(10);

        JLabel descriptionLabel = new JLabel("Sujet de réclamation");
        descriptionLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        descriptionLabel.setBounds(115, 322, 168, 14);
        contentPane.add(descriptionLabel);

        descriptionTextArea = new TextArea();
        descriptionTextArea.setBounds(125, 342, 447, 160);
        contentPane.add(descriptionTextArea);

        sendButton = new JButton("Envoyer");
        sendButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        sendButton.setBounds(324, 510, 148, 25);
        contentPane.add(sendButton);
        sendButton.addActionListener(e -> insertReclamation());
    }

    private void fetchUserCIN(String CIN) {
        if (CIN == null || CIN.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nom d'utilisateur non fourni.", "Erreur de Paramètre", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String fetchCIN = "SELECT CIN FROM users WHERE CIN=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmtCIN = conn.prepareStatement(fetchCIN)) {
            pstmtCIN.setString(1, CIN);
            try (ResultSet rsCIN = pstmtCIN.executeQuery()) {
                if (rsCIN.next()) {
                    this.userCIN = rsCIN.getString("CIN");
                } else {
                    JOptionPane.showMessageDialog(this, "Aucun utilisateur trouvé avec ce nom d'utilisateur.", "Utilisateur non trouvé", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur de base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void loadSectorsFromDatabase() {
        String query = "SELECT secteur FROM secteur_rec";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                sectorComboBox.addItem(rs.getString("secteur"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur de base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void insertReclamation() {
        if (sectorComboBox.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un secteur avant de soumettre.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String titre = titleField.getText();
        int idSec = sectorComboBox.getSelectedIndex() + 1;
        String sujet = descriptionTextArea.getText();
        String status = "En cours de traitement";

        String query = "INSERT INTO Reclamation (nom, date_creation, idsec, Description, CIN, status, localisation) VALUES (?, NOW(), ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, titre);
            pstmt.setInt(2, idSec);
            pstmt.setString(3, sujet);
            pstmt.setString(4, userCIN);
            pstmt.setString(5, status);
            pstmt.setString(6, localisationField.getText());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Réclamation ajoutée avec succès !");
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de la réclamation.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur de base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Reclammation frame = new Reclammation("Test");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
