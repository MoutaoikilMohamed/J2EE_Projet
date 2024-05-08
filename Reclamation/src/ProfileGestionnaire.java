import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.EventQueue;
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

public class ProfileGestionnaire extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nomField;
    private JTextField prenomField;
    private JDateChooser dateNaissanceChooser;
    private JTextField cinField;
    private JTextField provinceField;
    private JTextField ntelField;
    private String CIN;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProfileGestionnaire frame = new ProfileGestionnaire("exampleCIN");
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
    public ProfileGestionnaire(String CIN) {
        setTitle("Mon Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 846, 482);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        this.CIN = CIN;

        JLabel backgroundLabel = new JLabel("");
        backgroundLabel.setIcon(new ImageIcon(Login.class.getResource("/image/back.PNG")));
        backgroundLabel.setBounds(-20, 0, 866, 82);
        contentPane.add(backgroundLabel);

        initialize();
        afficher();
    }

    private void initialize() {
        JLabel lblNom = new JLabel("Nom");
        lblNom.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNom.setBounds(108, 125, 105, 24);
        contentPane.add(lblNom);

        nomField = new JTextField();
        nomField.setBounds(287, 122, 207, 30);
        contentPane.add(nomField);
        nomField.setColumns(10);

        JLabel lblPrenom = new JLabel("Prenom");
        lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPrenom.setBounds(108, 164, 105, 24);
        contentPane.add(lblPrenom);

        JLabel lblDateNaissance = new JLabel("Date de naissance");
        lblDateNaissance.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDateNaissance.setBounds(108, 203, 126, 24);
        contentPane.add(lblDateNaissance);

        dateNaissanceChooser = new JDateChooser();
        dateNaissanceChooser.setBounds(287, 200, 207, 30);
        contentPane.add(dateNaissanceChooser);

        JLabel lblCIN = new JLabel("CIN");
        lblCIN.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCIN.setBounds(108, 238, 126, 24);
        contentPane.add(lblCIN);

        JLabel lblProvince = new JLabel("Province");
        lblProvince.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblProvince.setBounds(108, 281, 105, 24);
        contentPane.add(lblProvince);

        JLabel lblNtel = new JLabel("Numéro de telephone");
        lblNtel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNtel.setBounds(108, 316, 158, 24);
        contentPane.add(lblNtel);

        prenomField = new JTextField();
        prenomField.setColumns(10);
        prenomField.setBounds(287, 162, 207, 30);
        contentPane.add(prenomField);

        cinField = new JTextField();
        cinField.setColumns(10);
        cinField.setBounds(287, 236, 207, 30);
        contentPane.add(cinField);

        provinceField = new JTextField();
        provinceField.setColumns(10);
        provinceField.setBounds(287, 270, 207, 30);
        contentPane.add(provinceField);

        ntelField = new JTextField();
        ntelField.setColumns(10);
        ntelField.setBounds(287, 311, 207, 30);
        contentPane.add(ntelField);

        JButton btnValider = new JButton("Valider");
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    updateUserProfile();
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs correctement.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnValider.setForeground(Color.BLUE);
        btnValider.setBounds(371, 412, 91, 23);
        contentPane.add(btnValider);
    }

    public void afficher() {
        String fetchUser = "SELECT nom, prenom, date_naissance, CIN, province, Ntel FROM users WHERE CIN = ?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmtUser = conn.prepareStatement(fetchUser)) {

            pstmtUser.setString(1, CIN);
            ResultSet rsUser = pstmtUser.executeQuery();
            if (rsUser.next()) {
                nomField.setText(rsUser.getString("nom"));
                prenomField.setText(rsUser.getString("prenom"));
                dateNaissanceChooser.setDate(rsUser.getDate("date_naissance"));
                cinField.setText(rsUser.getString("CIN"));
                provinceField.setText(rsUser.getString("province"));
                ntelField.setText(rsUser.getString("Ntel"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'accès à la base de données: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        return !(
            nomField.getText().trim().isEmpty() ||
            prenomField.getText().trim().isEmpty() ||
            dateNaissanceChooser.getDate() == null ||
            cinField.getText().trim().isEmpty() ||
            provinceField.getText().trim().isEmpty() ||
            ntelField.getText().trim().isEmpty()
        );
    }

    private void updateUserProfile() {
        String updateSql = "UPDATE users SET nom=?, prenom=?, date_naissance=?, CIN=?, province=?, Ntel=? WHERE CIN=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSql)) {

            pstmt.setString(1, nomField.getText());
            pstmt.setString(2, prenomField.getText());
            pstmt.setDate(3, new java.sql.Date(dateNaissanceChooser.getDate().getTime()));
            pstmt.setString(4, cinField.getText());
            pstmt.setString(5, provinceField.getText());
            pstmt.setString(6, ntelField.getText());
            pstmt.setString(7, CIN);

            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                JOptionPane.showMessageDialog(null, "Mise à jour du profil réussie!", "Update Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Échec de la mise à jour du profil. Veuillez réessayer.", "Update Failure", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de la base de données: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
