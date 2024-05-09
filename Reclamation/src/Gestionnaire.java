import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

public class Gestionnaire extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private static String CIN;
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		    public void run() {
		        try {
		            Gestionnaire frame = new Gestionnaire(CIN);
		            frame.setVisible(true);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		});
	}

	public Gestionnaire( String CIN) {
    	setTitle(" Espace Gestionnaire");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 844, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 contentPane.setLayout(null);
		 JLabel lblNewLabel = new JLabel("");
	        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/image/back.PNG")));
	        lblNewLabel.setBounds(-32, 0, 866, 82);
	        contentPane.add(lblNewLabel);
		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Espace Agent administrative");
		lblNewLabel_1.setForeground(new Color(0, 196, 0));
		lblNewLabel_1.setFont(new Font("Sitka Small", Font.BOLD, 21));
		lblNewLabel_1.setBounds(281, 232, 341, 24);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setBackground(new Color(255, 183, 183));
		btnNewButton_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		    	AccessReclamation accessReclamation = new AccessReclamation(CIN);
		        accessReclamation.setVisible(true);
		    }
		});
		btnNewButton_1_1.setIcon(new ImageIcon(Gestionnaire.class.getResource("/image/rapport (1).png")));
		btnNewButton_1_1.setBounds(512, 287, 213, 231);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		    	AccessReclamation accessReclamation = new AccessReclamation(CIN);
				accessReclamation.setVisible(true);
		    }
		});
	
		JButton btnNewButton_1_1_11 = new JButton("");
		btnNewButton_1_1_11.setBackground(new Color(255, 183, 183));
		btnNewButton_1_1_11.setIcon(new ImageIcon(Gestionnaire.class.getResource("/image/carte-didentite.png")));
		btnNewButton_1_1_11.setBounds(129, 287, 208, 231);
		contentPane.add(btnNewButton_1_1_11);
		btnNewButton_1_1_11.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		        ProfileGestionnaire profileGestionnaire = new ProfileGestionnaire(CIN); 
		        profileGestionnaire.setVisible(true); 
		    }
		});
		
		JButton btnProfileGestionnaire = new JButton("Mon Profil");
		btnProfileGestionnaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileGestionnaire profilGestionnaire = new ProfileGestionnaire(CIN);
				profilGestionnaire.setVisible(true);
			}
		});
		btnProfileGestionnaire.setBackground(new Color(255, 183, 183));
		btnProfileGestionnaire.setForeground(new Color(255, 255, 255));
		btnProfileGestionnaire.setFont(new Font("Sylfaen", Font.BOLD, 16));
		btnProfileGestionnaire.setBounds(129, 522, 208, 29);
		contentPane.add(btnProfileGestionnaire);
		
		JButton btnRclamations = new JButton("Réclamations");
		btnRclamations.setForeground(new Color(255, 255, 255));
		btnRclamations.setBackground(new Color(255, 183, 183));
		btnRclamations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccessReclamation accessReclamation = new AccessReclamation(CIN);
				accessReclamation.setVisible(true);
			}
		});
		btnRclamations.setFont(new Font("Sylfaen", Font.BOLD, 16));
		btnRclamations.setBounds(512, 521, 213, 30);
		contentPane.add(btnRclamations);
		
		JLabel lblNewLabel_1_1 = new JLabel("Interface principale");
		lblNewLabel_1_1.setForeground(new Color(0, 147, 0));
		lblNewLabel_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(22, 104, 231, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Gestionnaire.class.getResource("/image/profile.png")));
		lblNewLabel_2.setBounds(366, 79, 145, 128);
		contentPane.add(lblNewLabel_2);
		
		JButton btnLogout = new JButton("");
		btnLogout.setIcon(new ImageIcon(Gestionnaire.class.getResource("/image/se-deconnecter (1).png")));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Sylfaen", Font.BOLD, 16));
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setBounds(700, 84, 115, 53);
		contentPane.add(btnLogout);
		  btnLogout.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	                Login loginFrame = new Login();
	                loginFrame.setVisible(true);
	            }
	        });
		
		JLabel lblDconnection = new JLabel("Déconnection");
		lblDconnection.setForeground(Color.RED);
		lblDconnection.setFont(new Font("Sitka Small", Font.BOLD, 17));
		lblDconnection.setBounds(689, 146, 247, 30);
		contentPane.add(lblDconnection);
	}

}
