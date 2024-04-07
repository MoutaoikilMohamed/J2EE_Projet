import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField CIN;
    private JTextField PASS;
    private JLabel Recuperer;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
    	setTitle(" Espace d'Authentification");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 846, 440);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton LOGIN = new JButton("Connecter");
        LOGIN.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        LOGIN.setForeground(new Color(0, 0, 0));
        LOGIN.setBackground(new Color(255, 128, 128));
        LOGIN.setBounds(357, 358, 123, 32);
        contentPane.add(LOGIN);
        
        CIN = new JTextField();
        CIN.setBounds(209, 187, 383, 32);
        contentPane.add(CIN);
        CIN.setColumns(10);
        
        PASS = new JTextField();
        PASS.setColumns(10);
        PASS.setBounds(209, 255, 383, 32);
        contentPane.add(PASS);
        
        JLabel lblNewLabel_1 = new JLabel("CIN");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_1.setBounds(211, 162, 46, 14);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Clé de securité");
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_1_1.setBounds(209, 230, 114, 14);
        contentPane.add(lblNewLabel_1_1);
        

        Recuperer = new JLabel("Clé de securité oublié ?");
        Recuperer.setBackground(new Color(0, 255, 0));
        Recuperer.setFont(new Font("Times New Roman", Font.BOLD, 12));
        Recuperer.setBounds(210, 298, 150, 14);
        Recuperer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                Recuper recuper = new Recuper();
				recuper.setVisible(true);
            }
        });
        contentPane.add(Recuperer);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/image/back.PNG")));
        lblNewLabel.setBounds(-29, -112, 868, 306);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/image/login.PNG")));
        lblNewLabel_2.setBounds(661, 165, 168, 146);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Panneau d'authentification");
        lblNewLabel_1_1_1.setForeground(new Color(0, 153, 0));
        lblNewLabel_1_1_1.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
        lblNewLabel_1_1_1.setBounds(300, 131, 228, 25);
        contentPane.add(lblNewLabel_1_1_1);
    }
}