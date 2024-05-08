import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Citoyens extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String CIN;

    public Citoyens(String CIN) {
        this.CIN = CIN;  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 872, 675);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        initializeComponents();
        accesNomPrenom(CIN);
    }
    private void initializeComponents() {
        JLabel lblNewLabel = new JLabel(new ImageIcon(Citoyens.class.getResource("/image/back.PNG")));
        lblNewLabel.setBounds(0, 0, 881, 82);
        contentPane.add(lblNewLabel);

        JLabel lblEspaceCitoyens = new JLabel("Espace Citoyens");
        lblEspaceCitoyens.setForeground(new Color(0, 196, 0));
        lblEspaceCitoyens.setFont(new Font("Sitka Small", Font.BOLD, 17));
        lblEspaceCitoyens.setBounds(358, 153, 247, 30);
        contentPane.add(lblEspaceCitoyens);
        
        
       

        JButton btnReclamations = new JButton(new ImageIcon(Citoyens.class.getResource("/image/ADD.png")));
        btnReclamations.setBackground(new Color(255, 183, 183));
        btnReclamations.addActionListener(e -> {
            Reclammation reclammation = new Reclammation(CIN);
            reclammation.setVisible(true);
        });
        btnReclamations.setBounds(48, 302, 213, 216);
        contentPane.add(btnReclamations);

        JButton btnProfile = new JButton(new ImageIcon(Citoyens.class.getResource("/image/MyRec.png")));
        btnProfile.setBackground(new Color(255, 183, 183));
        btnProfile.addActionListener(e -> {
        	
        	 Mes_Reclammation reclammation = new Mes_Reclammation(CIN);
             reclammation.setVisible(true);
          
        });
        btnProfile.setBounds(311, 302, 208, 216);
        contentPane.add(btnProfile);

        JButton btnSettings = new JButton(new ImageIcon(Citoyens.class.getResource("/image/profil.png")));
        btnSettings.setBackground(new Color(255, 183, 183));
        btnSettings.addActionListener(e -> {
        	 Mon_profile reclammation = new Mon_profile(CIN);
             reclammation.setVisible(true);
         
        });
        btnSettings.setBounds(579, 302, 208, 216);
        contentPane.add(btnSettings);

        JButton btnAddReclamation = new JButton("Ajout Reclamations");
        btnAddReclamation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reclammation rec = new Reclammation(CIN);
                rec.setVisible(true);
            }
        });
        btnAddReclamation.setForeground(Color.WHITE);
        btnAddReclamation.setBackground(new Color(255, 183, 183));
        btnAddReclamation.setFont(new Font("Sylfaen", Font.BOLD, 17));
        btnAddReclamation.setBounds(48, 521, 213, 29);
        contentPane.add(btnAddReclamation);

        JButton btnMyReclamations = new JButton("Mes Reclamations");
        btnMyReclamations.addActionListener(e -> {
            Mes_Reclammation mesReclammationWindow = new Mes_Reclammation(CIN);
            mesReclammationWindow.setVisible(true);
        });

        btnMyReclamations.setBackground(new Color(255, 183, 183));
        btnMyReclamations.setForeground(Color.WHITE);
        btnMyReclamations.setFont(new Font("Sylfaen", Font.BOLD, 16));
        btnMyReclamations.setBounds(311, 522, 208, 29);
        contentPane.add(btnMyReclamations);

        JButton btnUserProfile = new JButton("Profile");
        btnUserProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mon_profile monprofile = new Mon_profile(CIN);
                monprofile.setVisible(true);
            }
        });
        btnUserProfile.setForeground(Color.WHITE);
        btnUserProfile.setBackground(new Color(255, 183, 183));
        btnUserProfile.setFont(new Font("Sylfaen", Font.BOLD, 16));
        btnUserProfile.setBounds(579, 521, 213, 30);
        contentPane.add(btnUserProfile);
        
        JButton btnLogout = new JButton("Déconnexion");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login loginFrame = new Login();
                loginFrame.setVisible(true);
            }
        });
        btnLogout.setBounds(615, 157, 231, 24);
        
        btnLogout.setBackground(new Color(255, 183, 183));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Sylfaen", Font.BOLD, 16));
        
        contentPane.add(btnLogout, BorderLayout.EAST);
        
     

        
    }
    
    public void accesNomPrenom(String CIN) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String sql = "SELECT nom, prenom FROM users WHERE cin=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, CIN);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String nom = resultSet.getString("nom");
                        String prenom = resultSet.getString("prenom");
                        String texteLabel = "Compte : " + prenom + " " + nom;

                        JLabel lblProfile = new JLabel(texteLabel);
                        lblProfile.setForeground(new Color(0, 147, 220));
                        lblProfile.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
                        lblProfile.setBounds(110, 103, 231, 24);
                        contentPane.add(lblProfile);
                    } else {
                        System.out.println("Aucun utilisateur trouvé pour le CIN : " + CIN);
                        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
    public String getUsername() {
        return CIN;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Citoyens frame = new Citoyens("testUser");
            frame.setVisible(true);
        });
    }
}
