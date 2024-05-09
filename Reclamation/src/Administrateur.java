import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Font;
import com.toedter.components.JLocaleChooser;

public class Administrateur extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrateur frame = new Administrateur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Administrateur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1081, 675);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Administrateur.class.getResource("/image/back.PNG")));
		lblNewLabel.setBounds(86, 0, 739, 71);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Espace Administrateur");
		lblNewLabel_1.setForeground(new Color(0, 196, 0));
		lblNewLabel_1.setFont(new Font("Sitka Small", Font.BOLD, 17));
		lblNewLabel_1.setBounds(434, 166, 231, 24);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setBackground(new Color(255, 183, 183));
		btnNewButton_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		        ListeReclamation listeCitoyen = new ListeReclamation(); 
		        listeCitoyen.setVisible(true); 
		    }
		});
		btnNewButton_1_1.setIcon(new ImageIcon(Administrateur.class.getResource("/image/rapport (1).png")));
		btnNewButton_1_1.setBounds(544, 283, 213, 235);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     
		        ListeGestionnaire listeCitoyen = new ListeGestionnaire(); 
		        listeCitoyen.setVisible(true); 
		    }
		});
	
		btnNewButton_1_1_1.setBackground(new Color(255, 183, 183));
		btnNewButton_1_1_1.setIcon(new ImageIcon(Administrateur.class.getResource("/image/profile.png")));
		btnNewButton_1_1_1.setBounds(277, 283, 208, 235);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("");
		btnNewButton_1_1_1_1.setIcon(new ImageIcon(Administrateur.class.getResource("/image/Citoyens.png")));
		btnNewButton_1_1_1_1.setBackground(new Color(255, 183, 183));
		btnNewButton_1_1_1_1.setBounds(21, 283, 197, 235);
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
			}
		});
		btnNewButton.setBounds(21, 521, 197, 29);
		contentPane.add(btnNewButton);
		
		JButton btnGestionnaire = new JButton("Agents");
		btnGestionnaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionnaire.setBackground(new Color(255, 183, 183));
		btnGestionnaire.setForeground(new Color(255, 255, 255));
		btnGestionnaire.setFont(new Font("Sylfaen", Font.BOLD, 16));
		btnGestionnaire.setBounds(277, 522, 208, 29);
		contentPane.add(btnGestionnaire);
		
		JButton btnRclamations = new JButton("Réclamations");
		btnRclamations.setForeground(new Color(255, 255, 255));
		btnRclamations.setBackground(new Color(255, 183, 183));
		btnRclamations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRclamations.setFont(new Font("Sylfaen", Font.BOLD, 16));
		btnRclamations.setBounds(544, 521, 213, 30);
		contentPane.add(btnRclamations);
		
		JLabel lblNewLabel_1_1 = new JLabel("Interface principale");
		lblNewLabel_1_1.setForeground(new Color(0, 147, 0));
		lblNewLabel_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(21, 93, 231, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Administrateur.class.getResource("/image/boss.png")));
		lblNewLabel_2.setBounds(508, 89, 86, 66);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1_1_2 = new JButton("");
		btnNewButton_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    Recuperation Recuper = new Recuperation(); 
			    Recuper.setVisible(true);
			}
		});
		btnNewButton_1_1_2.setIcon(new ImageIcon(Administrateur.class.getResource("/image/sauvegarder (1).png")));
		btnNewButton_1_1_2.setBackground(new Color(255, 183, 183));
		btnNewButton_1_1_2.setBounds(810, 283, 213, 235);
		contentPane.add(btnNewButton_1_1_2);
		
		JButton btnRcuperationxml = new JButton("Récuperation (XML)");
		btnRcuperationxml.setForeground(Color.WHITE);
		btnRcuperationxml.setFont(new Font("Sylfaen", Font.BOLD, 16));
		btnRcuperationxml.setBackground(new Color(255, 183, 183));
		btnRcuperationxml.setBounds(810, 522, 213, 30);
		contentPane.add(btnRcuperationxml);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Administrateur.class.getResource("/image/back - Copie.PNG")));
		lblNewLabel_3.setBounds(572, 0, 881, 71);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(Administrateur.class.getResource("/image/back - Copie.PNG")));
		lblNewLabel_3_1.setBounds(0, -11, 175, 89);
		contentPane.add(lblNewLabel_3_1);
		
		JButton btnLogout = new JButton("");
		btnLogout.setIcon(new ImageIcon(Administrateur.class.getResource("/image/se-deconnecter (1).png")));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Sylfaen", Font.BOLD, 16));
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setBounds(931, 78, 115, 53);
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
		lblDconnection.setBounds(920, 140, 247, 30);
		contentPane.add(lblDconnection);

	}
}
