import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mes_Reclammation extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrollPane;
    private String CIN;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Mes_Reclammation window = new Mes_Reclammation("testUser");
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }



    public Mes_Reclammation(String CIN) {
        this.CIN = CIN;
        initialize();
        afficher_recl();
	}

	

	private void initialize() {
        this.setBounds(100, 100, 865, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Mes Réclamations");

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        this.setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("/image/back.PNG")));
        lblNewLabel.setBounds(10, 0, 839, 89);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(new ImageIcon(getClass().getResource("/image/Citoyen.PNG")));
        lblNewLabel_1.setBounds(420, 116, 68, 59);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Espace Citoyen");
        lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel_2.setBounds(352, 186, 250, 30);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Mes réclamations");
        lblNewLabel_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        lblNewLabel_3.setBounds(90, 230, 168, 14);
        contentPane.add(lblNewLabel_3);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"CIN", "Titre","Secteur", "Date Création","Sujet de reclammation", "Status"}
        ));
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 250, 819, 200);
        contentPane.add(scrollPane);
    }

	public void afficher_recl() {
	    String fetchCIN = "SELECT CIN FROM users WHERE CIN=?";
	    String fetchReclamations = "SELECT nom, idsec, date_creation, Description,status FROM Reclamation WHERE CIN=?";
	    String fetchSecteur = "SELECT secteur FROM secteur_rec WHERE idsec=?";
	    try (Connection conn = ConnectionDB.getConnection();
	         PreparedStatement pstmtCIN = conn.prepareStatement(fetchCIN)) {

	        pstmtCIN.setString(1, CIN);
	        ResultSet rsCIN = pstmtCIN.executeQuery();
	        if (rsCIN.next()) {
	            String CIN = rsCIN.getString("CIN");

	            try (PreparedStatement pstmtRecl = conn.prepareStatement(fetchReclamations)) {
	                pstmtRecl.setString(1, CIN);
	                ResultSet rsRecl = pstmtRecl.executeQuery();
	                DefaultTableModel model = (DefaultTableModel) table.getModel();
	                model.setRowCount(0); 

	                while (rsRecl.next()) {
	                    String titre = rsRecl.getString("nom");
	                    int idSec = rsRecl.getInt("idsec");
	                    Date date = rsRecl.getDate("date_creation");
	                    String sujet = rsRecl.getString("Description");
	                    String secteur = "";
	                    String status=rsRecl.getString("status");

	                    try (PreparedStatement pstmtSecteur = conn.prepareStatement(fetchSecteur)) {
	                        pstmtSecteur.setInt(1, idSec);
	                        ResultSet rsSecteur = pstmtSecteur.executeQuery();
	                        if (rsSecteur.next()) {
	                            secteur = rsSecteur.getString("secteur");
	                        }
	                    }

	                    model.addRow(new Object[]{CIN, titre, secteur, date, sujet,status});
	                }
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "No CIN found for the username.", "Data Not Found", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error accessing the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
