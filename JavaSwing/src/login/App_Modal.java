package login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

public class App_Modal {

	private JFrame frame;
	private final JPanel panel = new JPanel();
	private final JLabel title = new JLabel("It is Label");
	private final JTextField textField = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App_Modal window = new App_Modal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App_Modal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		textField.setBounds(42, 160, 130, 26);
		textField.setColumns(10);
		textField.setEnabled(false);
		frame = new JFrame();
		frame.setSize(300, 400);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBounds(0, 0, 300, 372);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JButton btn = new JButton("Open Modal");
		btn.setBackground(new Color(238, 238, 238));
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Modal m = new Modal (frame, title);
					m.setVisible(true);
				}
			});
			
		btn.setBounds(91, 263, 117, 29);
		panel.add(btn);
		
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(32, 73, 233, 81);
		
		panel.add(title);
		
		panel.add(textField);
	}
}
