import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Citoyens extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String CIN;

    public Citoyens(String CIN) {
        this.CIN = CIN;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        lblEspaceCitoyens.setBounds(350, 157, 231, 24);
        contentPane.add(lblEspaceCitoyens);

        JButton btnReclamations = new JButton(new ImageIcon(Citoyens.class.getResource("/image/recla.jpeg")));
        btnReclamations.setBackground(new Color(255, 183, 183));
        btnReclamations.addActionListener(e -> {
            Reclammation reclammation = new Reclammation(CIN);
            reclammation.setVisible(true);
        });
        btnReclamations.setBounds(48, 302, 213, 216);
        contentPane.add(btnReclamations);

        JButton btnProfile = new JButton(new ImageIcon(Citoyens.class.getResource("/image/")));
        btnProfile.setBackground(new Color(255, 183, 183));
        btnProfile.addActionListener(e -> {

        });
        btnProfile.setBounds(311, 302, 208, 216);
        contentPane.add(btnProfile);

        JButton btnSettings = new JButton(new ImageIcon(Citoyens.class.getResource("/image/")));
        btnSettings.setBackground(new Color(255, 183, 183));
        btnSettings.addActionListener(e -> {

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

        JButton btnReclamationRefus = new JButton("Réclamation Refus");
        btnReclamationRefus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	  try {
                      Connection connection = ConnectionDB.getConnection();
                      String sql = "SELECT COUNT(*) AS refus_count FROM Reclamation WHERE CIN=? AND status='refuser'";
                      try (PreparedStatement statement = connection.prepareStatement(sql)) {
                          statement.setString(1, CIN);
                          try (ResultSet resultSet = statement.executeQuery()) {
                              if (resultSet.next()) {
                                  int refusCount = resultSet.getInt("refus_count");
                                  if (refusCount > 0) {
                                      Reclamation_refus rec_refus = new Reclamation_refus(CIN);
                                      rec_refus.setVisible(true);
                                  } else {
                                      JOptionPane.showMessageDialog(null, "Aucune réclamation refusée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                  }
                              }
                          }
                      }
                  } catch (SQLException ex) {
                      ex.printStackTrace();
                      JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                  }
            }
        });
        
        
        btnReclamationRefus.setForeground(Color.WHITE);
        btnReclamationRefus.setBackground(new Color(255, 183, 183));
        btnReclamationRefus.setFont(new Font("Sylfaen", Font.BOLD, 16));
        btnReclamationRefus.setBounds(579, 521, 213, 30);
        contentPane.add(btnReclamationRefus);

        JButton btnLogout = new JButton("Déconnexion");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login loginFrame = new Login();
                loginFrame.setVisible(true);
            }
        });
        btnLogout.setBounds(550, 103, 231, 24);
        btnLogout.setBackground(new Color(255, 183, 183));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Sylfaen", Font.BOLD, 16));
        contentPane.add(btnLogout);

        JButton monProfil = new JButton("Mon Profil");
        monProfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mon_profile monprofile = new Mon_profile(CIN);
                monprofile.setVisible(true);
            }
        });
        monProfil.setBounds(310, 103, 231, 24);
        monProfil.setBackground(new Color(255, 183, 183));
        monProfil.setForeground(Color.WHITE);
        monProfil.setFont(new Font("Sylfaen", Font.BOLD, 16));
        contentPane.add(monProfil);
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
