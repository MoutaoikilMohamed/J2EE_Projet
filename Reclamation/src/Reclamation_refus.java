
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Reclamation_refus extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private String CIN;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
            	Reclamation_refus window = new Reclamation_refus("testUser");
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Reclamation_refus(String CIN) {
        this.CIN = CIN;
        initialize();
        afficherReclamations();
    }

    private void initialize() {
        setTitle("Mes Réclamations Refusées");
        setBounds(100, 100, 865, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("/image/back.PNG")));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        contentPane.add(centerPanel, BorderLayout.CENTER);

        JLabel lblNewLabel_1 = new JLabel(new ImageIcon(getClass().getResource("/image/Citoyen.PNG")));
        centerPanel.add(lblNewLabel_1, BorderLayout.NORTH);

        JLabel lblNewLabel_2 = new JLabel("Espace Citoyen");
        lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(lblNewLabel_2, BorderLayout.CENTER);

        JLabel lblNewLabel_3 = new JLabel("Mes Réclamations Refusées");
        lblNewLabel_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(lblNewLabel_3, BorderLayout.SOUTH);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"CIN", "Titre", "Type", "Date Création", "Sujet de réclamation", "Status", "Motif"}
        ));
        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public void afficherReclamations() {
        String fetchReclamations = "SELECT nom, type, date_creation, Descreption, status, motif FROM Reclamation WHERE CIN=? and status=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmtRecl = conn.prepareStatement(fetchReclamations)) {
String ref="refuser";
            pstmtRecl.setString(1, CIN);
            pstmtRecl.setString(2, ref);
            ResultSet rsRecl = pstmtRecl.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            while (rsRecl.next()) {
                String titre = rsRecl.getString("nom");
                String type = rsRecl.getString("type");
                Date date = rsRecl.getDate("date_creation");
                String sujet = rsRecl.getString("Descreption");
                String status = rsRecl.getString("status");
                String motif = rsRecl.getString("motif");

                model.addRow(new Object[]{CIN, titre, type, date, sujet, status, motif});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error accessing the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
