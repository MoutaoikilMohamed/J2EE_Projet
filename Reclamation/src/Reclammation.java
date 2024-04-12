import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reclammation extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JComboBox<String> comboBox;
    private TextArea textArea;
    private JButton btn;
    private JButton btn2;
    private String userCIN;
    private String username;

    public Reclammation(String username) {
    	this.username = username;
        setTitle("Réclamation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 600); // Increased height to fit all components
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        initializeComponents();
        fetchUserCIN(this.username);
        loadSectorsFromDatabase();
    }

   

	private void initializeComponents() {
        JLabel lblNewLabel = new JLabel(new ImageIcon(Reclammation.class.getResource("/image/back.PNG")));
        lblNewLabel.setBounds(10, 11, 664, 89);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(new ImageIcon(Reclammation.class.getResource("/image/Citoyen.PNG")));
        lblNewLabel_1.setBounds(357, 111, 68, 59);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Espace Citoyen");
        lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel_2.setBounds(295, 186, 250, 30);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Titre de réclamation");
        lblNewLabel_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        lblNewLabel_3.setBounds(115, 272, 168, 14);
        contentPane.add(lblNewLabel_3);

        textField = new JTextField();
        textField.setBounds(324, 270, 148, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Secteur de réclamation");
        lblNewLabel_4.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        lblNewLabel_4.setBounds(115, 297, 168, 14);
        contentPane.add(lblNewLabel_4);

        comboBox = new JComboBox<>(); // Initialized here
        comboBox.setBounds(324, 293, 148, 22);
        contentPane.add(comboBox);

        JLabel lblNewLabel_5 = new JLabel("Sujet de réclamation");
        lblNewLabel_5.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        lblNewLabel_5.setBounds(115, 322, 168, 14);
        contentPane.add(lblNewLabel_5);

        textArea = new TextArea(); // Corrected to use the class-level variable
        textArea.setBounds(115, 342, 357, 160);
        contentPane.add(textArea);

        btn = new JButton("Envoyer");
        btn.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        btn.setBounds(324, 510, 148, 25);
        contentPane.add(btn);
        btn.addActionListener(e -> insertReclamation());

        btn2 = new JButton("Mes réclamations");
        btn2.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        btn2.setBounds(156, 510, 148, 25);
        contentPane.add(btn2);
        btn2.addActionListener(e -> {
            Mes_Reclammation mesReclammationWindow = new Mes_Reclammation(username);
            mesReclammationWindow.setVisible(true);
        });
        
    }


    private void fetchUserCIN(String username1) {
        if (username == null || username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username is not provided.", "Parameter Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String fetchCIN = "SELECT CIN FROM users WHERE username=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmtCIN = conn.prepareStatement(fetchCIN)) {  
             
            pstmtCIN.setString(1, username1); 
            try (ResultSet rsCIN = pstmtCIN.executeQuery()) { 
                if (rsCIN.next()) {
                    this.userCIN = rsCIN.getString("CIN"); 
                } else {
                    JOptionPane.showMessageDialog(this, "No user found with this username.", "User Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

          

    private void loadSectorsFromDatabase() {  	
        String query = "SELECT secteur FROM secteur_rec";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                comboBox.addItem(rs.getString("secteur"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void insertReclamation() {
        if (comboBox.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un secteur avant de soumettre.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String titre = textField.getText();
        int idSec = comboBox.getSelectedIndex() + 1; // Assuming comboBox indexes align with database indexes
        String sujet = textArea.getText();

        String query = "INSERT INTO reclammation (titre_rec, date_rec, id_sec, sujet_rec,CIN) VALUES (?, NOW(), ?, ?, ?)";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, titre);
            pstmt.setInt(2, idSec);
            pstmt.setString(3, sujet);
            pstmt.setString(4, userCIN); 

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
