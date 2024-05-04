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
	private String idusers;
    private String nom;
    private String prenom;
    private String username;
    private String CIN;
    private Date date_naissance;
    private String province;
    private String Ntel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		    public void run() {
		        try {
		            Gestionnaire frame = new Gestionnaire();
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
	public Gestionnaire() {
    	setTitle(" Espace Gestionnaire");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 846, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 contentPane.setLayout(null);
		 JLabel lblNewLabel = new JLabel("");
	        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/image/back.PNG")));
	        lblNewLabel.setBounds(-20, 0, 866, 82);
	        contentPane.add(lblNewLabel);
		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Espace Gestionnaire");
		lblNewLabel_1.setForeground(new Color(0, 196, 0));
		lblNewLabel_1.setFont(new Font("Sitka Small", Font.BOLD, 17));
		lblNewLabel_1.setBounds(350, 157, 231, 24);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setBackground(new Color(255, 183, 183));
		btnNewButton_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		    	AccessReclamation accessReclamation = new AccessReclamation();
		        accessReclamation.setVisible(true);
		    }
		});
		btnNewButton_1_1.setIcon(new ImageIcon(Gestionnaire.class.getResource("/image/database (1).png")));
		btnNewButton_1_1.setBounds(579, 302, 213, 216);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		    	AccessReclamation accessReclamation = new AccessReclamation();
				accessReclamation.setVisible(true);
		    }
		});
	
		JButton btnNewButton_1_1_11 = new JButton("");
		btnNewButton_1_1_11.setBackground(new Color(255, 183, 183));
		btnNewButton_1_1_11.setIcon(new ImageIcon(Gestionnaire.class.getResource("/image/profile.png")));
		btnNewButton_1_1_11.setBounds(311, 302, 208, 216);
		contentPane.add(btnNewButton_1_1_11);
		btnNewButton_1_1_11.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		        ProfileGestionnaire profileGestionnaire = new ProfileGestionnaire(idusers); 
		        profileGestionnaire.setVisible(true); 
		    }
		});
		
		JButton btnNewButton_1_1_1_1 = new JButton("");
		btnNewButton_1_1_1_1.setIcon(new ImageIcon(Gestionnaire.class.getResource("/image/Citoyens.png")));
		btnNewButton_1_1_1_1.setBackground(new Color(255, 183, 183));
		btnNewButton_1_1_1_1.setBounds(48, 302, 197, 216);
		contentPane.add(btnNewButton_1_1_1_1);
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		        ListeCitoyens listeCitoyen = new ListeCitoyens(); 
		        listeCitoyen.setVisible(true); 
		    }
		});
		
		JButton btnNewButton = new JButton("Citoyens");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 183, 183));
		btnNewButton.setFont(new Font("Sylfaen", Font.BOLD, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListeCitoyens listeCitoyens = new ListeCitoyens();
				listeCitoyens.setVisible(true);
			}
		});
		btnNewButton.setBounds(48, 521, 197, 29);
		contentPane.add(btnNewButton);
		
		JButton btnProfileGestionnaire = new JButton("Mon Profil");
		btnProfileGestionnaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfileGestionnaire profilGestionnaire = new ProfileGestionnaire(idusers);
				profilGestionnaire.setVisible(true);
			}
		});
		btnProfileGestionnaire.setBackground(new Color(255, 183, 183));
		btnProfileGestionnaire.setForeground(new Color(255, 255, 255));
		btnProfileGestionnaire.setFont(new Font("Sylfaen", Font.BOLD, 16));
		btnProfileGestionnaire.setBounds(311, 522, 208, 29);
		contentPane.add(btnProfileGestionnaire);
		
		JButton btnRclamations = new JButton("Réclamations");
		btnRclamations.setForeground(new Color(255, 255, 255));
		btnRclamations.setBackground(new Color(255, 183, 183));
		btnRclamations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccessReclamation accessReclamation = new AccessReclamation();
				accessReclamation.setVisible(true);
			}
		});
		btnRclamations.setFont(new Font("Sylfaen", Font.BOLD, 16));
		btnRclamations.setBounds(579, 521, 213, 30);
		contentPane.add(btnRclamations);
		
		JLabel lblNewLabel_1_1 = new JLabel("Interface d'exploration");
		lblNewLabel_1_1.setForeground(new Color(0, 147, 0));
		lblNewLabel_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(21, 93, 231, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Gestionnaire.class.getResource("/image/Gestionnaire.png")));
		lblNewLabel_2.setBounds(425, 80, 86, 66);
		contentPane.add(lblNewLabel_2);
	}

}
