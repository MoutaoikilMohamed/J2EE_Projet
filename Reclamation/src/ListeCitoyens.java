import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import javax.swing.JScrollPane;

public class ListeCitoyens extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListeCitoyens frame = new ListeCitoyens();
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
	public ListeCitoyens() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 872, 675);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-11, 0, 866, 82);
		lblNewLabel.setIcon(new ImageIcon(ListeCitoyens.class.getResource("/image/back.PNG")));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Interface d'exploration");
		lblNewLabel_1_1.setForeground(new Color(0, 147, 0));
		lblNewLabel_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(23, 103, 231, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Espace Administrateur");
		lblNewLabel_1.setForeground(new Color(0, 196, 0));
		lblNewLabel_1.setFont(new Font("Sitka Small", Font.BOLD, 17));
		lblNewLabel_1.setBounds(336, 170, 231, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ListeCitoyens.class.getResource("/image/boss.png")));
		lblNewLabel_2.setBounds(411, 93, 86, 66);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Liste des Citoyens");
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_1.setFont(new Font("Simplified Arabic", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(373, 219, 231, 24);
		contentPane.add(lblNewLabel_1_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 273, 805, 228);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"CIN", "Nom", "Prénom", "Date de naissance", "Province","Ntel"
			}
		));
		Connection conn = null;
		try {
		    conn = ConnectionDB.getConnection();

		    String sql = "SELECT idusers, nom, prenom, date_naissance, province, Ntel FROM USERS WHERE Role ='Citoyen'";
		    java.sql.Statement statement = conn.createStatement();
		    ResultSet resultSet = statement.executeQuery(sql);

		    DefaultTableModel model = (DefaultTableModel) table.getModel();

		    while (resultSet.next()) {
		        String cin = resultSet.getString("idusers");
		        String nom = resultSet.getString("nom");
		        String prenom = resultSet.getString("prenom");
		        String dateNaissance = resultSet.getString("date_naissance");
		        String province = resultSet.getString("province");
		        String NTel = resultSet.getString("Ntel");

		        model.addRow(new Object[]{cin, nom, prenom, dateNaissance, province,NTel});
		    }
	
		} catch (SQLException ex) {
		    JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
		} finally {
		    ConnectionDB.closeConnection(conn);
		}
		
	}
}
