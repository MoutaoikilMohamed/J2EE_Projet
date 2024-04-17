import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
public class Recuper extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField CIN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recuper frame = new Recuper();
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
	public Recuper() {
    	setTitle("Récuperation de mot de passe");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 852, 528);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Recuper.class.getResource("/image/back.PNG")));
		lblNewLabel.setBounds(-23, 0, 866, 82);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Veuillez remplir  les champs suivants :");
		lblNewLabel_1.setForeground(new Color(0, 102, 0));
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		lblNewLabel_1.setBounds(318, 98, 248, 22);
		contentPane.add(lblNewLabel_1);
		
		CIN = new JTextField();
		CIN.setBounds(330, 180, 229, 26);
		contentPane.add(CIN);
		CIN.setColumns(8);
		
		JButton btnValider = new JButton("Valider");
		
		btnValider.setForeground(Color.BLACK);
		btnValider.setBackground(new Color(255, 128, 128));
		btnValider.setBounds(391, 358, 123, 32);
		contentPane.add(btnValider);
		
		JLabel lblNewLabel_1_1 = new JLabel("CIN");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(422, 155, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Date de Naissance");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(391, 220, 137, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Province ou préfecture");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(379, 292, 168, 14);
		contentPane.add(lblNewLabel_1_3);
		
		JComboBox PROVINCE = new JComboBox();
		PROVINCE.setModel(new DefaultComboBoxModel(new String[] {"-Choisir-", "Tanger-Assilah", "M'diq-Fnideq", "Tétouan", "Fahs-Anjra", "Larache", "Al Hoceïma", "Chefchaouen", "Ouezzane", "Oujda-Angad", "Nador", "Driouch", "Jerada", "Berkane", "Taourirt", "Guercif", "Figuig", "Fès", "Meknès", "El Hajeb", "Ifrane", "Moulay Yaâcoub", "Séfrou", "Boulemane", "Taounate", "Taza", "Rabat", "Salé", "Skhirate-Témara", "Kénitra", "Khémisset", "Sidi Kacem", "Sidi Slimane", "Béni-Mellal", "Azilal", "Fquih Ben Salah", "Khénifra", "Khouribga", "Casablanca", "Mohammédia", "El Jadida", "Nouaceur", "Médiouna", "Benslimane", "Berrechid", "Settat", "Sidi Bennour", "Marrakech", "Chichaoua", "Al Haouz", "El Kelaâ des Sraghna", "Essaouira", "Rehamna", "Safi", "Youssoufia", "Errachidia", "Ouarzazate", "Midelt", "Tinghir", "Zagora", "Agadir Ida-Outanane", "Inezgane-Aït Melloul", "Chtouka-Aït Baha", "Taroudant", "Tiznit", "Tata", "Guelmim", "Assa-Zag", "Tan-Tan", "Sidi Ifni", "Laâyoune", "Boujdour", "Tarfaya", "Es-Semara", "Oued Ed Dahab", "Aousserd"}));
		PROVINCE.setToolTipText("");
		PROVINCE.setBounds(330, 325, 229, 22);
		contentPane.add(PROVINCE);
		
		JLabel ProvConfir = new JLabel("");
		ProvConfir.setForeground(new Color(0, 128, 0));
		ProvConfir.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		ProvConfir.setBounds(392, 445, 110, 31);
		contentPane.add(ProvConfir);
		
		JLabel MarieText = new JLabel("Vous devez passer au marie de");
		MarieText.setFont(new Font("Traditional Arabic", Font.BOLD, 14));
		MarieText.setBounds(196, 447, 193, 31);
		contentPane.add(MarieText);
		
		JLabel DateText = new JLabel("A la date de ");
		DateText.setFont(new Font("Traditional Arabic", Font.BOLD, 14));
		DateText.setBounds(499, 449, 75, 26);
		contentPane.add(DateText);
		DateText.setVisible(false);
		MarieText.setVisible(false);
		
		JLabel DATEConf = new JLabel("");
		DATEConf.setForeground(new Color(0, 128, 0));
		DATEConf.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		DATEConf.setBounds(584, 445, 123, 31);
		contentPane.add(DATEConf);
		
		JDateChooser DTN = new JDateChooser();
		DTN.setBounds(324, 259, 235, 22);
		contentPane.add(DTN);
		btnValider.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String cin = CIN.getText();
		        java.util.Date dtn = DTN.getDate();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        String dateString = dateFormat.format(dtn);
		        String province = PROVINCE.getSelectedItem().toString();
		        
		        Connection conn = ConnectionDB.getConnection();
		        LocalDate currentDate = LocalDate.now();

                LocalDate futureDate = currentDate.plusDays(2);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = futureDate.format(formatter);

		        if (cin.isEmpty() || dateString.isEmpty() || province.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
		            return; 
		        }
		        Random random = new Random();
		        int code = random.nextInt(10000) + 1;
		        if (conn != null) {
		            try {
		                String sql = "INSERT INTO Recuperation (CIN, date_naissance, Province,rendez_vous,code) VALUES (?, ?, ?, ?, ?)";
		                PreparedStatement pstmt = conn.prepareStatement(sql);
		                
		                pstmt.setString(1, cin);
		                pstmt.setString(2, dateString);
		                pstmt.setString(3, province);
		                pstmt.setString(4,formattedDate);
		                pstmt.setInt(5, code);
		                
		                pstmt.executeUpdate();
		              
		                JOptionPane.showMessageDialog(null, "données bien insérer , Capturer ce code de rendez vous : '"+code+"'");
		              
		                ProvConfir.setText(province);
		                DATEConf.setText(formattedDate);
		                DateText.setVisible(true);
		                MarieText.setVisible(true);
		            } catch (SQLException ex) {
		                JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion des données : " + ex.getMessage());
		            } finally {
		                ConnectionDB.closeConnection(conn);
		            }
		        }
		    }
		});
	}}
