import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public Menu() {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);
    }
}
