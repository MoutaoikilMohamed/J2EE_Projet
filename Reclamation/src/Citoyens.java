import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Citoyens extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String username;

    public Citoyens(String username) {
        this.username = username;  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 872, 675);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        initializeComponents();
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
            Mes_Reclammation mesReclammation = new Mes_Reclammation(username); 
            mesReclammation.setVisible(true); 
        });
        btnReclamations.setBounds(48, 302, 213, 216);
        contentPane.add(btnReclamations);

        JButton btnProfile = new JButton(new ImageIcon(Citoyens.class.getResource("/image/profile.png")));
        btnProfile.setBackground(new Color(255, 183, 183));
        btnProfile.addActionListener(e -> {
            Reclammation reclammation = new Reclammation(username); 
            reclammation.setVisible(true); 
        });
        btnProfile.setBounds(311, 302, 208, 216);
        contentPane.add(btnProfile);
        
        JButton btnAddReclamation = new JButton("Ajout Reclamations");
        btnAddReclamation.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Reclammation rec=new Reclammation(username);
        		rec.setVisible(true);        	}
        });
        btnAddReclamation.setForeground(Color.WHITE);
        btnAddReclamation.setBackground(new Color(255, 183, 183));
        btnAddReclamation.setFont(new Font("Sylfaen", Font.BOLD, 17));
        btnAddReclamation.setBounds(48, 521, 213, 29);
        contentPane.add(btnAddReclamation);

        JButton btnMyReclamations = new JButton("Mes Reclamations");
        btnMyReclamations.addActionListener(e -> {
            Mes_Reclammation mesReclammationWindow = new Mes_Reclammation(username);
            mesReclammationWindow.setVisible(true);
        });

        btnMyReclamations.setBackground(new Color(255, 183, 183));
        btnMyReclamations.setForeground(Color.WHITE);
        btnMyReclamations.setFont(new Font("Sylfaen", Font.BOLD, 16));
        btnMyReclamations.setBounds(311, 522, 208, 29);
        contentPane.add(btnMyReclamations);

        JButton btnUserProfile = new JButton("Profile");
        btnUserProfile.setForeground(Color.WHITE);
        btnUserProfile.setBackground(new Color(255, 183, 183));
        btnUserProfile.setFont(new Font("Sylfaen", Font.BOLD, 16));
        btnUserProfile.setBounds(579, 521, 213, 30);
        contentPane.add(btnUserProfile);

        JLabel lblProfile = new JLabel("Profile");
        lblProfile.setForeground(new Color(0, 147, 0));
        lblProfile.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
        lblProfile.setBounds(21, 93, 231, 24);
        contentPane.add(lblProfile);

        
    }

    public String getUsername() {
        return username;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Citoyens frame = new Citoyens("testUser");
            frame.setVisible(true);
        });
    }
}
