import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProfileGestionnaire extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nom;
	private JTextField prenom;
	private JTextField date_naissance;
	private JTextField CIN;
	private JTextField province;
	private JTextField Ntel;
	private static String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileGestionnaire frame = new ProfileGestionnaire(username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param username 
	 */
	public ProfileGestionnaire(String username) {
    	setTitle(" Mon Profile");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 846, 482);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 contentPane.setLayout(null);
		 JLabel lblNewLabel = new JLabel("");
	        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/image/back.PNG")));
	        lblNewLabel.setBounds(-20, 0, 866, 82);
	        contentPane.add(lblNewLabel);
		setContentPane(contentPane);
		
		this.username=username;
		
		afficher();
		initialize();
		
		
	}
	
	private void initialize() {
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(108, 125, 105, 24);
		contentPane.add(lblNewLabel_1);
		
		nom = new JTextField();
		nom.setBounds(287, 122, 207, 30);
		contentPane.add(nom);
		nom.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Prenom");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(108, 164, 105, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Date de naissance\r\n");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(108, 203, 126, 24);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("CIN");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3.setBounds(108, 238, 126, 24);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Numéro de telephone");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3_1.setBounds(108, 316, 105, 24);
		contentPane.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Province");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_3_2.setBounds(108, 281, 105, 24);
		contentPane.add(lblNewLabel_1_3_2);
		
		prenom = new JTextField();
		prenom.setColumns(10);
		prenom.setBounds(287, 162, 207, 30);
		contentPane.add(prenom);
		
		date_naissance = new JTextField();
		date_naissance.setColumns(10);
		date_naissance.setBounds(287, 200, 207, 30);
		contentPane.add(date_naissance);
		
		CIN = new JTextField();
		CIN.setColumns(10);
		CIN.setBounds(287, 236, 207, 30);
		contentPane.add(CIN);
		
		province = new JTextField();
		province.setColumns(10);
		province.setBounds(287, 270, 207, 30);
		contentPane.add(province);
		
		Ntel = new JTextField();
		Ntel.setColumns(10);
		Ntel.setBounds(287, 311, 207, 30);
		contentPane.add(Ntel);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			        if (validateFields()) {  // Assuming validateFields() checks if the input fields are properly filled
			            updateUserProfile();
			        } else {
			            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs correctement.", "Validation Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBounds(371, 412, 91, 23);
		contentPane.add(btnNewButton);
		
	}
	
	 public void afficher() {
	        String fetchUser = "SELECT nom, prenom, date_naissance, CIN, province, Ntel FROM users WHERE CIN = ?";

	        try (Connection conn = ConnectionDB.getConnection();
	             PreparedStatement pstmtUser = conn.prepareStatement(fetchUser)) {

	            pstmtUser.setString(1, username);
	            ResultSet rsUser = pstmtUser.executeQuery();
	            if (rsUser.next()) {
	                nom.setText(rsUser.getString("nom"));
	                prenom.setText(rsUser.getString("prenom"));
	                date_naissance.setText(rsUser.getDate("date_naissance").toString());
	                CIN.setText(rsUser.getString("CIN"));
	                province.setText(rsUser.getString("province"));
	                Ntel.setText(rsUser.getString("Ntel"));
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error accessing the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	 
	 
	 
	 private boolean validateFields() {
		    
		    if (nom.getText().trim().isEmpty() ||
		        prenom.getText().trim().isEmpty() ||
		        date_naissance.getText().trim().isEmpty() ||
		        CIN.getText().trim().isEmpty() ||
		        province.getText().trim().isEmpty() ||
		        Ntel.getText().trim().isEmpty()) {
		        return false;
		    }
		    return true;
		}
	 
	 private void updateUserProfile() {
		   
		    String updateSql = "UPDATE users SET nom=?, prenom=?, date_naissance=?, CIN=?, province=?, Ntel=? WHERE CIN=?";
		    try (Connection conn = ConnectionDB.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
		        
		        pstmt.setString(1, nom.getText());
		        pstmt.setString(2, prenom.getText());
		        pstmt.setDate(3, Date.valueOf(date_naissance.getText()));  // Assuming the format is correct
		        pstmt.setString(4, CIN.getText());
		        pstmt.setString(5, province.getText());
		        pstmt.setString(6, Ntel.getText());
		        pstmt.setString(8, username);

		        int updated = pstmt.executeUpdate();
		        if (updated > 0) {
		            JOptionPane.showMessageDialog(null, "Mise à jour du profil réussie!", "Update Success", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(null, "Échec de la mise à jour du profil. Veuillez réessayer.", "Update Failure", JOptionPane.ERROR_MESSAGE);
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error updating the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		    } catch (IllegalArgumentException ex) {
		        JOptionPane.showMessageDialog(null, "Format de date invalide. Veuillez utiliser AAAA-MM-JJ.", "Format Error", JOptionPane.ERROR_MESSAGE);
		    }
	 }
		    
	 
	 
	 
}
