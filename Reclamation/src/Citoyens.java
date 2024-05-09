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
    private JLabel Myprenom;
    private JLabel Mycin;
    private JLabel Mynom;

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
        lblNewLabel.setBounds(-15, 0, 881, 82);
        contentPane.add(lblNewLabel);

        JLabel lblEspaceCitoyens = new JLabel("Espace Citoyen");
        lblEspaceCitoyens.setForeground(new Color(0, 196, 0));
        lblEspaceCitoyens.setFont(new Font("Sitka Small", Font.BOLD, 21));
        lblEspaceCitoyens.setBounds(368, 224, 247, 29);
        contentPane.add(lblEspaceCitoyens);
        
        
       

        JButton btnReclamations = new JButton(new ImageIcon(Citoyens.class.getResource("/image/ADD.png")));
        btnReclamations.setBackground(new Color(255, 183, 183));
        btnReclamations.addActionListener(e -> {
            Reclammation reclammation = new Reclammation(CIN);
            reclammation.setVisible(true);
        });
        btnReclamations.setBounds(48, 325, 213, 216);
        contentPane.add(btnReclamations);

        JButton btnProfile = new JButton(new ImageIcon(Citoyens.class.getResource("/image/MyRec.png")));
        btnProfile.setBackground(new Color(255, 183, 183));
        btnProfile.addActionListener(e -> {
        	
        	 Mes_Reclammation reclammation = new Mes_Reclammation(CIN);
             reclammation.setVisible(true);
          
        });
        btnProfile.setBounds(311, 325, 208, 216);
        contentPane.add(btnProfile);

        JButton btnSettings = new JButton(new ImageIcon(Citoyens.class.getResource("/image/profil.png")));
        btnSettings.setBackground(new Color(255, 183, 183));
        btnSettings.addActionListener(e -> {
        	 Mon_profile Profile = new Mon_profile(CIN);
             Profile.setVisible(true);
         
        });
        btnSettings.setBounds(579, 325, 213, 216);
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
        btnAddReclamation.setBounds(48, 548, 213, 29);
        contentPane.add(btnAddReclamation);

        JButton btnMyReclamations = new JButton("Mes Reclamations");
        btnMyReclamations.addActionListener(e -> {
            Mes_Reclammation mesReclammationWindow = new Mes_Reclammation(CIN);
            mesReclammationWindow.setVisible(true);
        });

        btnMyReclamations.setBackground(new Color(255, 183, 183));
        btnMyReclamations.setForeground(Color.WHITE);
        btnMyReclamations.setFont(new Font("Sylfaen", Font.BOLD, 16));
        btnMyReclamations.setBounds(311, 549, 208, 29);
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
        btnUserProfile.setBounds(579, 548, 213, 30);
        contentPane.add(btnUserProfile);
        
        JButton btnLogout = new JButton("");
        btnLogout.setIcon(new ImageIcon(Citoyens.class.getResource("/image/se-deconnecter (1).png")));
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login loginFrame = new Login();
                loginFrame.setVisible(true);
            }
        });
        btnLogout.setBounds(731, 91, 115, 53);
        
        btnLogout.setBackground(new Color(255, 255, 255));
        btnLogout.setForeground(new Color(255, 255, 255));
        btnLogout.setFont(new Font("Sylfaen", Font.BOLD, 16));
        
        contentPane.add(btnLogout, BorderLayout.EAST);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Citoyens.class.getResource("/image/Citoyens.png")));
        lblNewLabel_2.setBounds(388, 78, 145, 135);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblDconnection = new JLabel("Déconnection");
        lblDconnection.setForeground(new Color(255, 0, 0));
        lblDconnection.setFont(new Font("Sitka Small", Font.BOLD, 17));
        lblDconnection.setBounds(720, 153, 247, 30);
        contentPane.add(lblDconnection);
        
        JLabel lblNCin = new JLabel("N CIN :");
        lblNCin.setForeground(new Color(0, 196, 0));
        lblNCin.setFont(new Font("Sitka Small", Font.BOLD, 15));
        lblNCin.setBounds(10, 93, 71, 29);
        contentPane.add(lblNCin);
        
        JLabel lblEspaceCitoyens_1_1 = new JLabel("Nom :");
        lblEspaceCitoyens_1_1.setForeground(new Color(0, 196, 0));
        lblEspaceCitoyens_1_1.setFont(new Font("Sitka Small", Font.BOLD, 15));
        lblEspaceCitoyens_1_1.setBounds(10, 126, 64, 29);
        contentPane.add(lblEspaceCitoyens_1_1);
        
        JLabel lblEspaceCitoyens_1_2 = new JLabel("Prenom :");
        lblEspaceCitoyens_1_2.setForeground(new Color(0, 196, 0));
        lblEspaceCitoyens_1_2.setFont(new Font("Sitka Small", Font.BOLD, 15));
        lblEspaceCitoyens_1_2.setBounds(10, 154, 96, 29);
        contentPane.add(lblEspaceCitoyens_1_2);
        Mycin = new JLabel("N CIN :");
        Mycin.setForeground(new Color(0, 0, 0));
        Mycin.setFont(new Font("Sitka Small", Font.BOLD, 17));
        Mycin.setBounds(74, 93, 155, 30);
        contentPane.add(Mycin);

        Mynom = new JLabel("Nom :");
        Mynom.setForeground(new Color(0, 0, 0));
        Mynom.setFont(new Font("Sitka Small", Font.BOLD, 17));
        Mynom.setBounds(62, 125, 155, 30);
        contentPane.add(Mynom);

        Myprenom = new JLabel("Prenom :");
        Myprenom.setForeground(new Color(0, 0, 0));
        Myprenom.setFont(new Font("Sitka Small", Font.BOLD, 17));
        Myprenom.setBounds(84, 153, 155, 30);
        contentPane.add(Myprenom);

        
    }
    
    public void accesNomPrenom(String CIN) {
        try {
            Connection connection = ConnectionDB.getConnection();
            String sql = "SELECT nom, prenom,CIN FROM users WHERE cin=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, CIN);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String nom = resultSet.getString("nom");
                        String prenom = resultSet.getString("prenom");
                        String cin = resultSet.getString("CIN");

                        Myprenom.setText(prenom);
                        Mynom.setText(nom);
                        Mycin.setText(cin);


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
