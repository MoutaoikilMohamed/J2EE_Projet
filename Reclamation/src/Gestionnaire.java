import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Gestionnaire extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestionnaire frame = new Gestionnaire();
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
	public Gestionnaire() {
    	setTitle(" Espace Gestionnaire");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 846, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 contentPane.setLayout(null);
		 JLabel lblNewLabel = new JLabel("");
	        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/image/back.PNG")));
	        lblNewLabel.setBounds(-20, 0, 866, 82);
	        contentPane.add(lblNewLabel);
		setContentPane(contentPane);
	}

}
