import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ListeGestionnaire extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeGestionnaire frame = new ListeGestionnaire();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListeGestionnaire() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 876, 675);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 871, 82);
		lblNewLabel.setIcon(new ImageIcon(ListeGestionnaire.class.getResource("/image/back.PNG")));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Espace Administrateur");
		lblNewLabel_1.setForeground(new Color(0, 196, 0));
		lblNewLabel_1.setFont(new Font("Sitka Small", Font.BOLD, 17));
		lblNewLabel_1.setBounds(336, 170, 231, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ListeGestionnaire.class.getResource("/image/boss.png")));
		lblNewLabel_2.setBounds(411, 93, 86, 66);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Liste des Agents administrative");
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(323, 205, 292, 24);
		contentPane.add(lblNewLabel_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 273, 850, 352);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"CIN", "Nom", "Prénom", "Date de naissance", "Province","Ntel","Service"
			}
		));
	    table.setRowHeight(table.getRowHeight() + 10);
        JTableHeader header = table.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD, 13)); 
		
		JButton btnNewButton = new JButton("Ajouter un agent");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 128, 128));
		btnNewButton.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		btnNewButton.setBounds(26, 217, 231, 31);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        AjouterGestionneur gestionnaireWindow = new AjouterGestionneur();
		        
		        gestionnaireWindow.setVisible(true);
		    }
		});
		Connection conn = null;
		try {
		    conn = ConnectionDB.getConnection();

		    String sql = "SELECT CIN, nom, prenom, date_naissance, province, Ntel,Service FROM USERS WHERE Role ='Gestionneur'";
		    java.sql.Statement statement = conn.createStatement();
		    ResultSet resultSet = statement.executeQuery(sql);

		    DefaultTableModel model = (DefaultTableModel) table.getModel();

		    while (resultSet.next()) {
		        String cin = resultSet.getString("CIN");
		        String nom = resultSet.getString("nom");
		        String prenom = resultSet.getString("prenom");
		        String dateNaissance = resultSet.getString("date_naissance");
		        String province = resultSet.getString("province");
		        String NTel = resultSet.getString("Ntel");
		        String Service = resultSet.getString("Service");


		        model.addRow(new Object[]{cin, nom, prenom, dateNaissance, province,NTel,Service});
		    }
	
		} catch (SQLException ex) {
		    JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
		} finally {
		    ConnectionDB.closeConnection(conn);
		}
		
	}

}

