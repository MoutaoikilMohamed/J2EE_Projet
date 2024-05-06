import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mon_profile extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nom;
    private JTextField prenom;
    private JTextField dateNaissance;
    private JTextField lieuNaissance;
    private JTextField province;
    private JTextField email;
    private JTextField nTel;
    private String CIN;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Mon_profile frame = new Mon_profile("JHG772");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @param CIN 
     */
    public Mon_profile(String CIN) {
        setTitle("Mon Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 846, 482);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/image/back.PNG")));
        lblNewLabel.setBounds(-20, 0, 866, 82);
        contentPane.add(lblNewLabel);
        setContentPane(contentPane);

        this.CIN = CIN;

        initialize();
        afficherDonnees();
    }

    private void initialize() {
        JLabel lblNewLabel_1 = new JLabel("Nom");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1.setBounds(108, 125, 105, 24);
        contentPane.add(lblNewLabel_1);

        nom = new JTextField();
        nom.setBounds(287, 122, 207, 30);
        contentPane.add(nom);
        nom.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("Prénom");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_1.setBounds(108, 164, 105, 24);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Date de naissance");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_2.setBounds(108, 203, 126, 24);
        contentPane.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel("Lieu de naissance");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_3.setBounds(108, 238, 126, 24);
        contentPane.add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_3_1 = new JLabel("Numéro de téléphone");
        lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_3_1.setBounds(108, 270, 141, 24);
        contentPane.add(lblNewLabel_1_3_1);

        prenom = new JTextField();
        prenom.setColumns(10);
        prenom.setBounds(287, 162, 207, 30);
        contentPane.add(prenom);

        dateNaissance = new JTextField();
        dateNaissance.setColumns(10);
        dateNaissance.setBounds(287, 200, 207, 30);
        contentPane.add(dateNaissance);



        province = new JTextField();
        province.setColumns(10);
        province.setBounds(287, 240, 207, 30);
        contentPane.add(province);

        nTel = new JTextField();
        nTel.setColumns(10);
        nTel.setBounds(287, 274, 207, 30);
        contentPane.add(nTel);

        JButton btnNewButton = new JButton("Valider");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    updateUserProfile();
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs correctement.", "Erreur de validation", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnNewButton.setForeground(Color.BLUE);
        btnNewButton.setBounds(414, 409, 91, 23);
        contentPane.add(btnNewButton);

    }

    public void afficherDonnees() {
        String fetchUser = "SELECT nom, prenom, date_naissance,  province, Ntel FROM users WHERE CIN=?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmtUser = conn.prepareStatement(fetchUser)) {

            pstmtUser.setString(1, CIN);
            ResultSet rsUser = pstmtUser.executeQuery();
            if (rsUser.next()) {
                nom.setText(rsUser.getString("nom"));
                prenom.setText(rsUser.getString("prenom"));
                dateNaissance.setText(rsUser.getDate("date_naissance").toString());
               
                province.setText(rsUser.getString("province"));
                nTel.setText(rsUser.getString("Ntel"));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'accès à la base de données : " + ex.getMessage(), "Erreur de la base de données", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {

        if (nom.getText().trim().isEmpty() ||
            prenom.getText().trim().isEmpty() ||
            dateNaissance.getText().trim().isEmpty() ||
            province.getText().trim().isEmpty() ||
            nTel.getText().trim().isEmpty() 
            ) {
            return false;
        }
        return true;
    }

    private void updateUserProfile() {

        String updateSql = "UPDATE users SET nom=?, prenom=?, date_naissance=?,  province=?, Ntel=?  WHERE cin=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSql)) {

            pstmt.setString(1, nom.getText());
            pstmt.setString(2, prenom.getText());
            pstmt.setDate(3, Date.valueOf(dateNaissance.getText()));  // Assuming the format is correct
          
            pstmt.setString(4, province.getText());
            pstmt.setString(5, nTel.getText());
   
            pstmt.setString(6, CIN);

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "Profil mis à jour avec succès !", "Mise à jour réussie", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Échec de la mise à jour du profil. Veuillez réessayer.", "Échec de la mise à jour", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de la base de données : " + ex.getMessage(), "Erreur de la base de données", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Format de date invalide. Veuillez utiliser YYYY-MM-DD.", "Erreur de format", JOptionPane.ERROR_MESSAGE);
        }
    }  
}
