import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CitoyensHomePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String CIN;
    private String nom;
    private String prenom;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CitoyensHomePage frame = new CitoyensHomePage("CIN");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void accesNomPrenom(String CIN) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String sql = "SELECT nom, prenom FROM users WHERE cin=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, CIN);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        nom = resultSet.getString("nom");
                        prenom = resultSet.getString("prenom");
                    } else {
                        System.out.println("Aucun utilisateur trouvé pour le CIN : " + CIN);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CitoyensHomePage(String CIN) {
        this.CIN = CIN;
        setTitle("Espace principale");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 874, 531);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(CitoyensHomePage.class.getResource("/image/back.PNG")));
        backgroundLabel.setBounds(0, -109, 868, 293);
        contentPane.add(backgroundLabel);
        accesNomPrenom(CIN);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(CitoyensHomePage.class.getResource("/image/MAROC-MAP-low.png")));
        lblNewLabel.setBounds(420, 85, 622, 396);
        contentPane.add(lblNewLabel);

        String texteLabel = "Bienvenue dans l'espace Citoyen\n " + prenom + " " + nom;


        JLabel lblNewLabel_1 = new JLabel(texteLabel);
        lblNewLabel_1.setForeground(new Color(0, 128, 0));
        lblNewLabel_1.setFont(new Font("Urdu Typesetting", Font.PLAIN, 30));
        lblNewLabel_1.setBounds(10, 161, 586, 55);
        contentPane.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("Suivant");
        btnNewButton.setBackground(new Color(255, 128, 128));
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBounds(139, 377, 224, 37);
        contentPane.add(btnNewButton);
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Citoyens cito = new Citoyens(CIN);
                cito.setVisible(true);
            }
        });

        JLabel lblCommencerLtapeDauthentification = new JLabel("Accéder à votre espace numérique");
        lblCommencerLtapeDauthentification.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
        lblCommencerLtapeDauthentification.setBounds(134, 348, 256, 18);
        contentPane.add(lblCommencerLtapeDauthentification);
    }

}
