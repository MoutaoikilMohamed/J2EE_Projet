import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class Reclammation extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;

    public Reclammation() {
        setTitle("Reclammation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 468);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Reclammation.class.getResource("/image/back.PNG")));
        lblNewLabel.setBounds(10, 11, 664, 89);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(Reclammation.class.getResource("/image/Citoyen.PNG")));
        lblNewLabel_1.setBounds(357, 111, 68, 59);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Espace Citoyen");
        lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 22));
        lblNewLabel_2.setBounds(295, 186, 250, 30);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Remplissage de réclamation");
        lblNewLabel_1_1_1.setForeground(new Color(0, 153, 0));
        lblNewLabel_1_1_1.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        lblNewLabel_1_1_1.setBounds(274, 222, 290, 20);
        contentPane.add(lblNewLabel_1_1_1);
        
        JLabel lblNewLabel_3 = new JLabel("Titre de réclamation");
        lblNewLabel_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        lblNewLabel_3.setBounds(115, 272, 168, 14);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_3_1 = new JLabel("Type de réclamation");
        lblNewLabel_3_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        lblNewLabel_3_1.setBounds(115, 297, 168, 14);
        contentPane.add(lblNewLabel_3_1);
        
        JLabel lblNewLabel_3_2 = new JLabel("Sujet réclamation");
        lblNewLabel_3_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
        lblNewLabel_3_2.setBounds(115, 342, 168, 14);
        contentPane.add(lblNewLabel_3_2);
        
        textField = new JTextField();
        textField.setBounds(324, 270, 148, 20);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Secteur publique", "secteur prive", "secteur juridique", "secteur social"});
        comboBox.setBounds(324, 293, 148, 22);
        contentPane.add(comboBox);
        
        textField_1 = new JTextField();
        textField_1.setBounds(327, 342, 182, 78);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
    }
}
