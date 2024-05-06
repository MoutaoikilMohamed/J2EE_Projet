import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
public class Recuperation extends JFrame {

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
					Recuperation frame = new Recuperation();
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
	public Recuperation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setTitle(" Espace Recuperation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 866, 594);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 855, 82);
		lblNewLabel.setIcon(new ImageIcon(Recuperation.class.getResource("/image/back.PNG")));
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 123, 805, 228);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"CIN", "Date Naissance", "Province", "Rendez vous", "code"
			}
		));
		
		JLabel lblNewLabel_1 = new JLabel("Liste des citoyen qui ont demandé un code ");
		lblNewLabel_1.setForeground(new Color(0, 196, 0));
		lblNewLabel_1.setFont(new Font("Sitka Small", Font.BOLD, 17));
		lblNewLabel_1.setBounds(34, 93, 492, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Exporter la liste en  XML");
		lblNewLabel_1_1.setForeground(new Color(255, 128, 128));
		lblNewLabel_1_1.setFont(new Font("Sitka Small", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(58, 391, 274, 24);
		contentPane.add(lblNewLabel_1_1);
		
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Importer une liste XML");
		lblNewLabel_1_1_1.setForeground(new Color(255, 128, 128));
		lblNewLabel_1_1_1.setFont(new Font("Sitka Small", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(530, 391, 245, 24);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton ExporterAll = new JButton("Exporter tout");
		ExporterAll.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		ExporterAll.setBackground(new Color(255, 128, 128));
		ExporterAll.setBounds(110, 440, 129, 38);
		contentPane.add(ExporterAll);
		
		   ExporterAll.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                exportToXML();
	            }
	        });
		JButton Importer = new JButton("Importer");
		Importer.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		Importer.setBackground(new Color(255, 128, 128));
		Importer.setBounds(580, 440, 129, 38);
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

		    String sql = "SELECT CIN, Date_naissance, Province, rendez_vous, code FROM recuperation;";
		    java.sql.Statement statement = conn.createStatement();
		    ResultSet resultSet = statement.executeQuery(sql);

		    DefaultTableModel model = (DefaultTableModel) table.getModel();

		    while (resultSet.next()) {
		        String cin = resultSet.getString("CIN");
		        String Date_naissance = resultSet.getString("Date_naissance");
		        String Province = resultSet.getString("Province");
		        String rendez_vous = resultSet.getString("rendez_vous");
		        String code = resultSet.getString("code");


		        model.addRow(new Object[]{cin, Date_naissance, Province,rendez_vous,code});
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

                NodeList nodeList = doc.getElementsByTagName("recuperation");

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                try {
                    conn = ConnectionDB.getConnection();
                    conn.setAutoCommit(false); // Start transaction

                    String insertQuery = "INSERT INTO recuperation (CIN, Date_naissance, Province, rendez_vous, code) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);

                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Element element = (Element) nodeList.item(i);
                        String cin = element.getElementsByTagName("CIN").item(0).getTextContent();
                        String dateNaissance = element.getElementsByTagName("Date_naissance").item(0).getTextContent();
                        String province = element.getElementsByTagName("Province").item(0).getTextContent();
                        String rendezVous = element.getElementsByTagName("rendez_vous").item(0).getTextContent();
                        String code = element.getElementsByTagName("code").item(0).getTextContent();

                        model.addRow(new Object[]{cin, dateNaissance, province, rendezVous, code});

                        // Add data to database
                        preparedStatement.setString(1, cin);
                        preparedStatement.setString(2, dateNaissance);
                        preparedStatement.setString(3, province);
                        preparedStatement.setString(4, rendezVous);
                        preparedStatement.setString(5, code);
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

	            String sql = "SELECT CIN, Date_naissance, Province, rendez_vous, code FROM recuperation;";
	            java.sql.Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);

	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            model.setRowCount(0); 

	            while (resultSet.next()) {
	                String cin = resultSet.getString("CIN");
	                String Date_naissance = resultSet.getString("Date_naissance");
	                String Province = resultSet.getString("Province");
	                String rendez_vous = resultSet.getString("rendez_vous");
	                String code = resultSet.getString("code");

	                model.addRow(new Object[]{cin, Date_naissance, Province, rendez_vous, code});
	            }
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Erreur lors du rafraîchissement des données: " + ex.getMessage());
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
            writer.write("<recuperations>\n");

            for (int i = 0; i < rowCount; i++) {
                writer.write("\t<recuperation>\n");
                writer.write("\t\t<CIN>" + model.getValueAt(i, 0) + "</CIN>\n");
                writer.write("\t\t<Date_naissance>" + model.getValueAt(i, 1) + "</Date_naissance>\n");
                writer.write("\t\t<Province>" + model.getValueAt(i, 2) + "</Province>\n");
                writer.write("\t\t<rendez_vous>" + model.getValueAt(i, 3) + "</rendez_vous>\n");
                writer.write("\t\t<code>" + model.getValueAt(i, 4) + "</code>\n");
                writer.write("\t</recuperation>\n");
            }

            writer.write("</recuperations>");
            writer.close();

            JOptionPane.showMessageDialog(null, "Exportation réussie vers le chemin principale 'exported_data.xml'");

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'exportation vers XML: " + ex.getMessage());
        }
    }
}
