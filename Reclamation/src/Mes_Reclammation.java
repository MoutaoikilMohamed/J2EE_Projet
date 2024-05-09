import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mes_Reclammation extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableEnCours, tableAcceptees, tableRefusees;
    private JScrollPane scrollPaneEnCours, scrollPaneAcceptees, scrollPaneRefusees;
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.CIN = CIN;
        initialize();
        afficherReclamations();
    }

    private void initialize() {
        setTitle("Mes Réclamations");
        setBounds(100, 100, 1084, 595);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("/image/back.PNG")));
        lblNewLabel.setBounds(72, -9, 872, 89);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(new ImageIcon(getClass().getResource("/image/Citoyen.PNG")));
        lblNewLabel_1.setBounds(506, 91, 68, 59);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Liste des réclamations");
        lblNewLabel_2.setForeground(new Color(0, 196, 0));
        lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel_2.setBounds(425, 161, 250, 30);
        contentPane.add(lblNewLabel_2);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setForeground(new Color(255, 128, 128));
        tabbedPane.setFont(new Font("Sylfaen", Font.BOLD, 15));
        tabbedPane.setBounds(10, 213, 1048, 309);
        contentPane.add(tabbedPane);

        JPanel panelEnCours = new JPanel();
        tabbedPane.addTab("En Cours", null, panelEnCours, null);
        panelEnCours.setLayout(new BorderLayout(0, 0));

        tableEnCours = new JTable();
        tableEnCours.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"CIN", "Titre", "Type", "Date Création",  "Sujet de réclamation", "Status"}
        ));
        scrollPaneEnCours = new JScrollPane(tableEnCours);
        panelEnCours.add(scrollPaneEnCours);
        
        tableEnCours.setRowHeight(tableEnCours.getRowHeight() + 10);
        JTableHeader header = tableEnCours.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14)); 
        
        JPanel panelAcceptees = new JPanel();
        tabbedPane.addTab("Acceptées", null, panelAcceptees, null);
        panelAcceptees.setLayout(new BorderLayout(0, 0));

        tableAcceptees = new JTable();
        tableAcceptees.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"CIN", "Titre", "Type", "Date Création", "Date de Résolution", "Sujet de réclamation", "Status","Motif"}
        ));
        tableAcceptees.setRowHeight(tableAcceptees.getRowHeight() + 10);
        JTableHeader header2 = tableAcceptees.getTableHeader();
        header2.setFont(header2.getFont().deriveFont(Font.BOLD, 14)); 
        scrollPaneAcceptees = new JScrollPane(tableAcceptees);
        panelAcceptees.add(scrollPaneAcceptees);

        JPanel panelRefusees = new JPanel();
        tabbedPane.addTab("Refusées", null, panelRefusees, null);
        panelRefusees.setLayout(new BorderLayout(0, 0));

        tableRefusees = new JTable();
        tableRefusees.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"CIN", "Titre", "Type", "Date Création", "Sujet de réclamation", "Status","Motif"}
        ));
        scrollPaneRefusees = new JScrollPane(tableRefusees);
        panelRefusees.add(scrollPaneRefusees);
        tableRefusees.setRowHeight(tableRefusees.getRowHeight() + 10);
        
        JLabel lblNewLabel_3 = new JLabel(new ImageIcon(Mes_Reclammation.class.getResource("/image/back - Copie.PNG")));
        lblNewLabel_3.setBounds(-43, -9, 1364, 89);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_3_1 = new JLabel(new ImageIcon(Mes_Reclammation.class.getResource("/image/back - Copie.PNG")));
        lblNewLabel_3_1.setBounds(0, -9, 126, 89);
        contentPane.add(lblNewLabel_3_1);
        JTableHeader header1 = tableRefusees.getTableHeader();
        header1.setFont(header1.getFont().deriveFont(Font.BOLD, 14)); 
    }

    public void afficherReclamations() {
        String fetchReclamations = "SELECT nom, type, date_creation,date_resolution, Description, status,motif FROM Reclamation";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmtRecl = conn.prepareStatement(fetchReclamations)) {

            ResultSet rsRecl = pstmtRecl.executeQuery();

            DefaultTableModel modelEnCours = (DefaultTableModel) tableEnCours.getModel();
            DefaultTableModel modelAcceptees = (DefaultTableModel) tableAcceptees.getModel();
            DefaultTableModel modelRefusees = (DefaultTableModel) tableRefusees.getModel();

            modelEnCours.setRowCount(0);
            modelAcceptees.setRowCount(0);
            modelRefusees.setRowCount(0);

            while (rsRecl.next()) {
                String titre = rsRecl.getString("nom");
                String type = rsRecl.getString("type");
                Date date = rsRecl.getDate("date_creation");
                Date date1 = rsRecl.getDate("date_resolution");
                String sujet = rsRecl.getString("Description");
                String status = rsRecl.getString("status");
                String motif = rsRecl.getString("motif");

                switch (status) {
                    case "En cours de traitement":
                        modelEnCours.addRow(new Object[]{CIN, titre, type, date, sujet, status});
                        break;
                    case "Acceptée":
                        modelAcceptees.addRow(new Object[]{CIN, titre, type, date, date1, sujet, status,motif});
                        break;
                    case "Refusée":
                        modelRefusees.addRow(new Object[]{CIN, titre, type, date, sujet, status,motif});
                        break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error accessing the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
