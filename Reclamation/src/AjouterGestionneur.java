import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

public class AjouterGestionneur extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Nom;
	private JTextField Prenom;
	private JTextField CIN;
	private JTextField mot_passe;
	private JButton AjouterG;
	private JTextField Username;
	private JTextField Tel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterGestionneur frame = new AjouterGestionneur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AjouterGestionneur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 872, 675);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AjouterGestionneur.class.getResource("/image/back.PNG")));
		lblNewLabel.setBounds(0, 0, 866, 82);
		contentPane.add(lblNewLabel);
		
		JLabel txtCIN = new JLabel("CIN");
		txtCIN.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		txtCIN.setBounds(163, 254, 46, 14);
		contentPane.add(txtCIN);
		
		Nom = new JTextField();
		Nom.setBounds(96, 208, 210, 28);
		contentPane.add(Nom);
		Nom.setColumns(10);
		
		JLabel txtPrenom = new JLabel("Prenom");
		txtPrenom.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		txtPrenom.setBounds(592, 170, 86, 14);
		contentPane.add(txtPrenom);
		
		Prenom = new JTextField();
		Prenom.setBounds(536, 208, 193, 28);
		contentPane.add(Prenom);
		Prenom.setColumns(10);
		
		JLabel txtN = new JLabel("Nom");
		txtN.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
        txtN.setBounds(163, 170, 46, 14);
		contentPane.add(txtN);
		
		CIN = new JTextField();
		CIN.setBounds(96, 285, 210, 28);
		contentPane.add(CIN);
		CIN.setColumns(10);
		
		JLabel txtCle = new JLabel("Clé de sécurité");
		txtCle.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		txtCle.setBounds(571, 468, 107, 14);
		contentPane.add(txtCle);
		
		
		mot_passe = new JTextField();
		mot_passe.setBounds(536, 496, 193, 28);
		contentPane.add(mot_passe);
		mot_passe.setColumns(10);
		
		AjouterG = new JButton("Ajouter");
		AjouterG.setBackground(new Color(255, 128, 128));
		AjouterG.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		AjouterG.setBounds(337, 564, 193, 28);
		contentPane.add(AjouterG);
		
		JDateChooser date_naissance = new JDateChooser();
		date_naissance.setBounds(96, 394, 210, 28);
		contentPane.add(date_naissance);
		
		JLabel dtnG = new JLabel("Date de naissance");
		dtnG.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		dtnG.setBounds(138, 353, 122, 28);
		contentPane.add(dtnG);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Ajouter un Gestionneur");
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(337, 110, 231, 24);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel txtU = new JLabel("Nom utilisateur");
		txtU.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtU.setBounds(571, 250, 107, 28);
		contentPane.add(txtU);
		
		Username = new JTextField();
		Username.setBounds(536, 289, 193, 24);
		contentPane.add(Username);
		Username.setColumns(10);
		
		JLabel TXTP = new JLabel("Povince");
		TXTP.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		TXTP.setBounds(604, 358, 74, 23);
		contentPane.add(TXTP);
		
		JComboBox PROVINCE = new JComboBox();
		PROVINCE.setModel(new DefaultComboBoxModel(new String[] {"-Choisir-", "Tanger-Assilah", "M'diq-Fnideq", "Tétouan", "Fahs-Anjra", "Larache", "Al Hoceïma", "Chefchaouen", "Ouezzane", "Oujda-Angad", "Nador", "Driouch", "Jerada", "Berkane", "Taourirt", "Guercif", "Figuig", "Fès", "Meknès", "El Hajeb", "Ifrane", "Moulay Yaâcoub", "Séfrou", "Boulemane", "Taounate", "Taza", "Rabat", "Salé", "Skhirate-Témara", "Kénitra", "Khémisset", "Sidi Kacem", "Sidi Slimane", "Béni-Mellal", "Azilal", "Fquih Ben Salah", "Khénifra", "Khouribga", "Casablanca", "Mohammédia", "El Jadida", "Nouaceur", "Médiouna", "Benslimane", "Berrechid", "Settat", "Sidi Bennour", "Marrakech", "Chichaoua", "Al Haouz", "El Kelaâ des Sraghna", "Essaouira", "Rehamna", "Safi", "Youssoufia", "Errachidia", "Ouarzazate", "Midelt", "Tinghir", "Zagora", "Agadir Ida-Outanane", "Inezgane-Aït Melloul", "Chtouka-Aït Baha", "Taroudant", "Tiznit", "Tata", "Guelmim", "Assa-Zag", "Tan-Tan", "Sidi Ifni", "Laâyoune", "Boujdour", "Tarfaya", "Es-Semara", "Oued Ed Dahab", "Aousserd"}));

		PROVINCE.setToolTipText("");
		PROVINCE.setBounds(538, 394, 191, 28);
		contentPane.add(PROVINCE);
		
		JLabel TXTTel = new JLabel("Telephone");
		TXTTel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		TXTTel.setBounds(163, 468, 86, 13);
		contentPane.add(TXTTel);
		
		Tel = new JTextField();
		Tel.setBounds(99, 498, 207, 28);
		contentPane.add(Tel);
		Tel.setColumns(10);
		AjouterG.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String cin = CIN.getText();
		        java.util.Date dtn = date_naissance.getDate();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        String dateString = dateFormat.format(dtn);
		        String province = PROVINCE.getSelectedItem().toString();
		        String tel = Tel.getText();

		        Connection conn = ConnectionDB.getConnection();

		        if (cin.isEmpty() || dateString.isEmpty() || province.isEmpty() || tel.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        if (province.equals("-Choisir-")) {
		            JOptionPane.showMessageDialog(null, "Veuillez choisir une province.", "Erreur", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        if (!tel.matches("\\d+")) {
		            JOptionPane.showMessageDialog(null, "Le numéro de téléphone doit être un nombre.", "Erreur", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        if (conn != null) {
		            try {
		                String sql = "INSERT INTO USERS (idusers, username, nom, prenom, pwd, date_naissance, province, Ntel, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'Gestionneur')";
		                PreparedStatement pstmt = conn.prepareStatement(sql);

		                pstmt.setString(1, cin);
		                pstmt.setString(2, Username.getText());
		                pstmt.setString(3, Nom.getText());
		                pstmt.setString(4, Prenom.getText());
		                pstmt.setString(5, mot_passe.getText());
		                pstmt.setString(6, dateString);
		                pstmt.setString(7, province);
		                pstmt.setString(8, tel); 

		                pstmt.executeUpdate();

		                JOptionPane.showMessageDialog(null, "Données bien insérées, veuillez patienter !");
		            } catch (SQLException ex) {
		                JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion des données : " + ex.getMessage());
		            } finally {
		                ConnectionDB.closeConnection(conn);
		            }
		        }
		    }
		});
	    }
	}
