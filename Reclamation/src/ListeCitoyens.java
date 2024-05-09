import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.mysql.cj.xdevapi.Statement;

import javax.swing.JScrollPane;

public class ListeCitoyens extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

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

	public ListeCitoyens() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 884, 675);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 879, 82);
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
	        scrollPane.setBounds(10, 254, 848, 228);
	        contentPane.add(scrollPane);

	        table = new JTable();
	        scrollPane.setViewportView(table);
	        table.setModel(new DefaultTableModel(
	                new Object[][]{},
	                new String[]{
	                        "CIN", "Nom", "Prénom", "Date de naissance", "Province", "Ntel"
	                }
	        ));
	        table.setRowHeight(table.getRowHeight() + 10);
	        JTableHeader header = table.getTableHeader();
	        header.setFont(header.getFont().deriveFont(Font.BOLD, 14)); 

		JButton ExporterAll = new JButton("Exporter tout");
		ExporterAll.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		ExporterAll.setBackground(new Color(255, 128, 128));
		ExporterAll.setBounds(142, 568, 129, 38);
		contentPane.add(ExporterAll);
		ExporterAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToXML();
            }
        });
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Exporter la liste en  XML");
		lblNewLabel_1_1_2.setForeground(new Color(255, 128, 128));
		lblNewLabel_1_1_2.setFont(new Font("Sitka Small", Font.BOLD, 17));
		lblNewLabel_1_1_2.setBounds(90, 519, 274, 24);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Importer une liste XML");
		lblNewLabel_1_1_1_1.setForeground(new Color(255, 128, 128));
		lblNewLabel_1_1_1_1.setFont(new Font("Sitka Small", Font.BOLD, 17));
		lblNewLabel_1_1_1_1.setBounds(562, 519, 245, 24);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JButton Importer = new JButton("Importer");
		Importer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		Importer.setBackground(new Color(255, 128, 128));
		Importer.setBounds(612, 568, 129, 38);
		contentPane.add(Importer);
		Importer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importFromXML();
            }
        });
		Connection conn = null;
		try {
		    conn = ConnectionDB.getConnection();

		    String sql = "SELECT CIN, nom, prenom, date_naissance, province, Ntel FROM USERS WHERE Role ='Citoyen'";
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

		        model.addRow(new Object[]{cin, nom, prenom, dateNaissance, province,NTel});
		    }
	
		} catch (SQLException ex) {
		    JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
		} finally {
		    ConnectionDB.closeConnection(conn);
		}
		
	}
	private void importFromXML() {
	    Connection conn = null;

	    try {
	        JFileChooser fileChooser = new JFileChooser();
	        int returnValue = fileChooser.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fileChooser.getSelectedFile();

	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            org.w3c.dom.Document doc = dBuilder.parse(selectedFile);

	            doc.getDocumentElement().normalize();

	            NodeList nodeList = doc.getElementsByTagName("citoyen");

	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            model.setRowCount(0);

	            try {
	                conn = ConnectionDB.getConnection();
	                conn.setAutoCommit(false); 

	                String insertQuery = "INSERT INTO users (CIN, nom, prenom, date_naissance, province, Ntel,role) VALUES (?, ?, ?, ?, ?, ?,?)";
	                PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

	                for (int i = 0; i < nodeList.getLength(); i++) {
	                    Element element = (Element) nodeList.item(i);
	                    String cin = element.getElementsByTagName("CIN").item(0).getTextContent();
	                    String nom = element.getElementsByTagName("nom").item(0).getTextContent();
	                    String prenom = element.getElementsByTagName("prenom").item(0).getTextContent();
	                    String date_naissance = element.getElementsByTagName("Date_naissance").item(0).getTextContent();
	                    String province = element.getElementsByTagName("province").item(0).getTextContent();
	                    String Ntel = element.getElementsByTagName("Ntel").item(0).getTextContent();
	                    String role = element.getElementsByTagName("Role").item(0).getTextContent();

	                    model.addRow(new Object[]{cin, nom, prenom, date_naissance, province, Ntel});

	                    // Add data to database
	                    preparedStatement.setString(1, cin);
	                    preparedStatement.setString(2, nom);
	                    preparedStatement.setString(3, prenom);
	                    preparedStatement.setString(4, date_naissance);
	                    preparedStatement.setString(5, province);
	                    preparedStatement.setString(6, Ntel);
	                    preparedStatement.setString(7, role);

	                    preparedStatement.addBatch(); 
	                }

	                preparedStatement.executeBatch(); 

	                conn.commit();
	                JOptionPane.showMessageDialog(null, "Importation réussie depuis le fichier XML.");
	                refreshTableData();

	            } catch (SQLException ex) {
	                if (conn != null) {
	                    conn.rollback(); 
	                }
	                JOptionPane.showMessageDialog(null, "Erreur lors de l'importation dans la base de données: " + ex.getMessage());
	            } finally {
	                if (conn != null) {
	                    conn.setAutoCommit(true); 
	                    ConnectionDB.closeConnection(conn);
	                }
	            }
	        }
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Erreur lors de l'importation depuis le fichier XML: " + ex.getMessage());
	    }
	}
	  private void refreshTableData() {
	        Connection conn = null;
	        try {
	            conn = ConnectionDB.getConnection();

	            String sql = "SELECT CIN, nom, prenom, date_naissance, province, Ntel FROM USERS WHERE Role ='Citoyen';";
	            java.sql.Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);

	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            model.setRowCount(0); 
	            while (resultSet.next()) {
			        String cin = resultSet.getString("CIN");
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

	private void exportToXML() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();

        try {
            FileWriter writer = new FileWriter("exported_data.xml");

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<Utilisateurs>\n");

            for (int i = 0; i < rowCount; i++) {
                writer.write("\t<citoyen>\n");
                writer.write("\t\t<CIN>" + model.getValueAt(i, 0) + "</CIN>\n");
                writer.write("\t\t<nom>" + model.getValueAt(i, 1) + "</nom>\n");
                writer.write("\t\t<prenom>" + model.getValueAt(i, 2) + "</prenom>\n");
                writer.write("\t\t<Date_naissance>" + model.getValueAt(i, 3) + "</Date_naissance>\n");
                writer.write("\t\t<province>" + model.getValueAt(i, 4) + "</province>\n");
                writer.write("\t\t<Ntel>" + model.getValueAt(i, 4) + "</Ntel>\n");
                writer.write("\t\t<Role>"+"Citoyen"+"</Role>\n");

                writer.write("\t</citoyen>\n");
            }

            writer.write("</Utilisateurs>");
            writer.close();

            JOptionPane.showMessageDialog(null, "Exportation réussie vers le chemin principale 'exported_data.xml'");

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'exportation vers XML: " + ex.getMessage());
        }
    }
}



