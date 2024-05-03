import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Principale extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principale frame = new Principale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principale() {
		 setTitle("Espace principale");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 874, 531);
	        contentPane = new JPanel();
	        contentPane.setBackground(Color.WHITE);
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	        
	        JLabel backgroundLabel = new JLabel();
	        backgroundLabel.setIcon(new ImageIcon(Principale.class.getResource("/image/back.PNG")));
	        backgroundLabel.setBounds(0, -109, 868, 293);
	        contentPane.add(backgroundLabel);
	        
	        JLabel lblNewLabel = new JLabel("");
	        lblNewLabel.setIcon(new ImageIcon(Principale.class.getResource("/image/MAROC-MAP-low.png")));
	        lblNewLabel.setBounds(420, 85, 622, 396);
	        contentPane.add(lblNewLabel);
	        
	        JLabel lblNewLabel_1 = new JLabel("Plateforme de Gestion des Réclamation locales");
	        lblNewLabel_1.setForeground(new Color(0, 128, 0));
	        lblNewLabel_1.setFont(new Font("Urdu Typesetting", Font.PLAIN, 30));
	        lblNewLabel_1.setBounds(10, 161, 586, 55);
	        contentPane.add(lblNewLabel_1);
	        
	        JButton btnNewButton = new JButton("Authentification");
	        btnNewButton.setBackground(new Color(255, 128, 128));
	        btnNewButton.setForeground(new Color(255, 255, 255));
	        btnNewButton.setBounds(139, 377, 224, 37);
	        contentPane.add(btnNewButton);
	        btnNewButton.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                Login recuper = new Login();
	                recuper.setVisible(true);
	            }
	        });
	    
	        JLabel lblCommencerLtapeDauthentification = new JLabel("Accéder au votre espace numérique");
	        lblCommencerLtapeDauthentification.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
	        lblCommencerLtapeDauthentification.setBounds(134, 348, 256, 18);
	        contentPane.add(lblCommencerLtapeDauthentification);
	}
}
