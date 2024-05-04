import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileGestionnaire extends JFrame {
    private JPanel contentPane;
    private JLabel lblNom;
    private JLabel lblPrenom;
    private JLabel lblUsername;
    private JLabel lblCIN;
    private JLabel lblDateNaissance;
    private JLabel lblProvince;
    private JLabel lblNtel;
    private String idusers;
    private String nom;
    private String prenom;
    private String username;
    private String CIN;
    private Date date_naissance;
    private String province;
    private String Ntel;
    

    public ProfileGestionnaire(String idusers) {
    	this.idusers = idusers;
    	this.nom = nom;
    	this.prenom = prenom;
    	this.username = username;
    	this.CIN = CIN;
    	this.date_naissance = date_naissance;
    	this.province = province;
    	this.Ntel = Ntel;
    	
        setTitle("Profil Gestionnaire");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(8, 2, 10, 10)); // Modifié le nombre de lignes pour inclure le titre

        // Titre "Mon Profil" centré en haut
        JLabel lblTitle = new JLabel("Mon Profil");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitle);
        contentPane.add(new JLabel()); // Colonne vide pour maintenir la grille

        lblNom = new JLabel("Nom:");
        lblNom.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblNom);

        JLabel lblNomValue = new JLabel();
        lblNomValue.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblNomValue);

        lblPrenom = new JLabel("Prénom:");
        lblPrenom.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblPrenom);

        JLabel lblPrenomValue = new JLabel();
        lblPrenomValue.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblPrenomValue);

        lblUsername = new JLabel("Nom d'utilisateur:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblUsername);

        JLabel lblUsernameValue = new JLabel();
        lblUsernameValue.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblUsernameValue);

        lblCIN = new JLabel("CIN:");
        lblCIN.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblCIN);

        JLabel lblCINValue = new JLabel();
        lblCINValue.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblCINValue);

        lblDateNaissance = new JLabel("Date de Naissance:");
        lblDateNaissance.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblDateNaissance);

        JLabel lblDateNaissanceValue = new JLabel();
        lblDateNaissanceValue.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblDateNaissanceValue);

        lblProvince = new JLabel("Province:");
        lblProvince.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblProvince);

        JLabel lblProvinceValue = new JLabel();
        lblProvinceValue.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblProvinceValue);

        lblNtel = new JLabel("Numéro de Téléphone:");
        lblNtel.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblNtel);

        JLabel lblNtelValue = new JLabel();
        lblNtelValue.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(lblNtelValue);

        fetchGestionnaireInfo(username, lblNomValue, lblPrenomValue, lblUsernameValue, lblCINValue, lblDateNaissanceValue, lblProvinceValue, lblNtelValue);
    }

    private void fetchGestionnaireInfo(String username, JLabel lblNomValue, JLabel lblPrenomValue, JLabel lblUsernameValue, JLabel lblCINValue, JLabel lblDateNaissanceValue, JLabel lblProvinceValue, JLabel lblNtelValue) {
        String query = "SELECT nom, prenom, username, CIN, date_naissance, province, Ntel FROM users WHERE idusers=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, idusers);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lblNomValue.setText(rs.getString("nom"));
                lblPrenomValue.setText(rs.getString("prenom"));
                lblUsernameValue.setText(rs.getString("username"));
                lblCINValue.setText(rs.getString("CIN"));
                lblDateNaissanceValue.setText(rs.getString("date_naissance"));
                lblProvinceValue.setText(rs.getString("province"));
                lblNtelValue.setText(rs.getString("Ntel"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ProfileGestionnaire frame = new ProfileGestionnaire("votre nom d'utilisateur");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
