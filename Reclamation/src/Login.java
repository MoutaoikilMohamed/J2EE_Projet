import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField cinField;
    private JPasswordField passField;
    private JLabel recupererLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setTitle("Espace d'Authentification");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 846, 440);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        cinField = new JTextField();
        cinField.setBounds(209, 187, 383, 32);
        contentPane.add(cinField);
        cinField.setColumns(10);

        passField = new JPasswordField();
        passField.setColumns(10);
        passField.setBounds(209, 255, 383, 32);
        contentPane.add(passField);

        setupLabels();
        setupImages();
        setupLoginButton();
    }

    private void setupLabels() {
        JLabel cinLabel = new JLabel("CIN");
        cinLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        cinLabel.setBounds(211, 162, 46, 14);
        contentPane.add(cinLabel);

        JLabel passLabel = new JLabel("Clé de sécurité");
        passLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        passLabel.setBounds(209, 230, 114, 14);
        contentPane.add(passLabel);

        recupererLabel = new JLabel("Clé de sécurité oublié ?");
        recupererLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        recupererLabel.setBounds(210, 298, 150, 14);
        recupererLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Recuper recuper = new Recuper();
                recuper.setVisible(true);
            }
        });
        contentPane.add(recupererLabel);
    }

    private void setupImages() {
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(Login.class.getResource("/image/back.PNG")));
        backgroundLabel.setBounds(-29, -112, 868, 306);
        contentPane.add(backgroundLabel);

        JLabel loginImageLabel = new JLabel();
        loginImageLabel.setIcon(new ImageIcon(Login.class.getResource("/image/login.PNG")));
        loginImageLabel.setBounds(661, 165, 168, 146);
        contentPane.add(loginImageLabel);
    }

    private void setupLoginButton() {
        JButton loginButton = new JButton("Connecter");
        loginButton.addActionListener(e -> processLogin());
        loginButton.setBackground(new Color(255, 128, 128));
        loginButton.setBounds(357, 358, 123, 32);
        contentPane.add(loginButton);
    }

    private void processLogin() {
        if (checkEmptyFields()) {
            JOptionPane.showMessageDialog(null, "Please enter your CIN and your password", "Missing Information", JOptionPane.ERROR_MESSAGE);
        } else {
            String username = cinField.getText();
            String password = new String(passField.getPassword());
            authenticateUser(username, password);
        }
    }

    private void authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username=? AND pwd=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                handleLoginResult(rs, username);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error accessing the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLoginResult(ResultSet rs, String username) throws SQLException {
        if (rs.next()) {
            int et = rs.getInt("etat");
            if (et == 1) {
                String role = rs.getString("role");
                switch (role) {
                    case "citoyen":
                        openFrame(new Citoyens(username));
                        break;
                    case "gestion":
                        openFrame(new Gestionnaire());
                        break;
                    case "admin":
                        openFrame(new Administrateur());
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Le compte est désactivé par l'administrateur", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No user exists with this username and password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void openFrame(JFrame frame) {
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        dispose();
    }

    private boolean checkEmptyFields() {
        return (cinField.getText().isEmpty() || passField.getPassword().length == 0);
    }
}
