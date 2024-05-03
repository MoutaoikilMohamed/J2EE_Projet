import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;


public class AccessReclamation extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccessReclamation frame = new AccessReclamation();
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
	public AccessReclamation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 872, 675);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 866, 82);
		lblNewLabel.setIcon(new ImageIcon(AccessReclamation.class.getResource("/image/back.PNG")));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Interface d'exploration");
		lblNewLabel_1_1.setForeground(new Color(0, 147, 0));
		lblNewLabel_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(23, 103, 231, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Espace Gestionnaire");
		lblNewLabel_1.setForeground(new Color(0, 196, 0));
		lblNewLabel_1.setFont(new Font("Sitka Small", Font.BOLD, 17));
		lblNewLabel_1.setBounds(336, 170, 231, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(AccessReclamation.class.getResource("/image/Gestionnaire.png")));
		lblNewLabel_2.setBounds(411, 93, 86, 66);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Liste des reclamations");
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(364, 205, 231, 24);
		contentPane.add(lblNewLabel_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 272, 812, 171);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nom", "Type", "Localisation", "date création", "date résolution", "Status", "CIN"
			}
		));
		Connection conn = null;
		try {
		    conn = ConnectionDB.getConnection();

		    String sql = "SELECT *from Reclamation";
		    java.sql.Statement statement = conn.createStatement();
		    ResultSet resultSet = statement.executeQuery(sql);

		    DefaultTableModel model = (DefaultTableModel) table.getModel();

		    while (resultSet.next()) {
		        int id = resultSet.getInt("ID");
		        String nom = resultSet.getString("nom");
		        String type = resultSet.getString("type");
		        String localisation = resultSet.getString("localisation");
		        Date date_creation = resultSet.getDate("date_creation"); 
		        Date date_resolution = resultSet.getDate("date_resolution");
		        String status = resultSet.getString("status");
		        String CIN = resultSet.getString("CIN");

		        model.addRow(new Object[]{id, nom, type, localisation, date_creation, date_resolution, status, CIN});
		    }
		    
		    JButton btnNewButton = new JButton("Vérifier la Réclamation");
		    btnNewButton.setForeground(new Color(255, 255, 255));
		    btnNewButton.setBackground(new Color(255, 128, 128));
		    btnNewButton.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		    btnNewButton.setBounds(180, 460, 200, 31);
		    contentPane.add(btnNewButton);

		    JTextField textField = new JTextField();
		    textField.setBounds(26, 460, 150, 31);
		    contentPane.add(textField);

		    btnNewButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            try {
		                String textFieldText = textField.getText();
		                if (textFieldText.isEmpty()) {
		                    JOptionPane.showMessageDialog(contentPane, "Veuillez saisir un ID.");
		                    return; // Sortir de la méthode actionPerformed si le champ texte est vide
		                }

		                int ID = Integer.parseInt(textFieldText);

		                Connection conn = null;
		                PreparedStatement statement = null;
		                ResultSet resultSet = null;
		                try {
		                    conn = ConnectionDB.getConnection();
		                    String sql = "SELECT * FROM reclamation WHERE ID = ?";
		                    statement = conn.prepareStatement(sql);
		                    statement.setInt(1, ID);
		                    resultSet = statement.executeQuery();
		                    
		                    if (resultSet.next()) {
		                        // Extraire les détails de la réclamation du ResultSet
		                        String nom = resultSet.getString("nom");
		                        String type = resultSet.getString("type");
		                        String localisation = resultSet.getString("localisation");
		                        Date dateCreation = resultSet.getDate("date_creation");
		                        Date dateResolution = resultSet.getDate("date_resolution");
		                        String description = resultSet.getString("Description");
		                        String status = resultSet.getString("status");
		                        String cin = resultSet.getString("CIN");

		                        // Créer et afficher la fenêtre des détails de la réclamation
		                        ReclamationDetailsWindow detailsWindow = new ReclamationDetailsWindow(ID, nom, type, localisation, dateCreation, dateResolution, description, status, cin);
		                        detailsWindow.setVisible(true);
		                    } else {
		                        JOptionPane.showMessageDialog(contentPane, "Aucune réclamation trouvée pour l'ID spécifié.");
		                    }
		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(contentPane, "Veuillez saisir un ID valide.");
		                } finally {
		                    // Fermer les ressources JDBC
		                    if (resultSet != null) resultSet.close();
		                    if (statement != null) statement.close();
		                    if (conn != null) conn.close();
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		    });
	
		} catch (SQLException ex) {
		    JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
		} finally {
		    ConnectionDB.closeConnection(conn);
		}
	}
	
	private void updateResolutionDate(int id, Date currentDate) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionDB.getConnection();

            String sql = "UPDATE Reclamation SET date_resolution = ? WHERE id = ?";
            
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(currentDate.getTime())); // Convertir java.util.Date en java.sql.Date
            statement.setInt(2, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Date de résolution de la réclamation " + id + " mise à jour à : " + currentDate);
            } else {
                System.out.println("Aucune réclamation trouvée avec l'ID : " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
