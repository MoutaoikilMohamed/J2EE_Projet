import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

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
        setTitle("Mes Réclamations");
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

        JLabel lblNewLabel_3 = new JLabel("Mes réclamations");
        lblNewLabel_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(lblNewLabel_3, BorderLayout.SOUTH);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"CIN", "Titre", "Type", "Date Création","Date de Résolution","Sujet de réclamation", "Status"}
        ));
        scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public void afficher_recl() {
        String fetchReclamations = "SELECT nom, type, date_creation,date_resolution, Descreption, status FROM Reclamation WHERE CIN=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmtRecl = conn.prepareStatement(fetchReclamations)) {

            pstmtRecl.setString(1, CIN);
            ResultSet rsRecl = pstmtRecl.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            while (rsRecl.next()) {
                String titre = rsRecl.getString("nom");
                String type = rsRecl.getString("type");
                Date date = rsRecl.getDate("date_creation");
                Date date1 = rsRecl.getDate("date_resolution");
                String sujet = rsRecl.getString("Descreption");
                String status = rsRecl.getString("status");

                model.addRow(new Object[]{CIN, titre, type, date,date1,sujet, status});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error accessing the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
