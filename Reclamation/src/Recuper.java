import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Recuper extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
        setBounds(100, 100, 846, 440);
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
		
		textField = new JTextField();
		textField.setBounds(330, 180, 229, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(330, 245, 229, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"-Choisir-", "Tanger-Assilah", "M'diq-Fnideq", "Tétouan", "Fahs-Anjra", "Larache", "Al Hoceïma", "Chefchaouen", "Ouezzane", "Oujda-Angad", "Nador", "Driouch", "Jerada", "Berkane", "Taourirt", "Guercif", "Figuig", "Fès", "Meknès", "El Hajeb", "Ifrane", "Moulay Yaâcoub", "Séfrou", "Boulemane", "Taounate", "Taza", "Rabat", "Salé", "Skhirate-Témara", "Kénitra", "Khémisset", "Sidi Kacem", "Sidi Slimane", "Béni-Mellal", "Azilal", "Fquih Ben Salah", "Khénifra", "Khouribga", "Casablanca", "Mohammédia", "El Jadida", "Nouaceur", "Médiouna", "Benslimane", "Berrechid", "Settat", "Sidi Bennour", "Marrakech", "Chichaoua", "Al Haouz", "El Kelaâ des Sraghna", "Essaouira", "Rehamna", "Safi", "Youssoufia", "Errachidia", "Ouarzazate", "Midelt", "Tinghir", "Zagora", "Agadir Ida-Outanane", "Inezgane-Aït Melloul", "Chtouka-Aït Baha", "Taroudant", "Tiznit", "Tata", "Guelmim", "Assa-Zag", "Tan-Tan", "Sidi Ifni", "Laâyoune", "Boujdour", "Tarfaya", "Es-Semara", "Oued Ed Dahab", "Aousserd"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(330, 325, 229, 22);
		contentPane.add(comboBox);
	}
}
