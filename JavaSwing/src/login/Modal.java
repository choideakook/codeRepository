package login;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Modal extends JDialog {
		// 스윙과 연결
	public Modal (Window parent, JLabel title) {
		super(parent, "Modal Practice", ModalityType.APPLICATION_MODAL);
		setLayout (null);
		
		JLabel lb = new JLabel("Title Exchange");
		lb.setBounds(80, -50, 100, 200);
		add(lb);
		JTextField field = new JTextField();
		field.setBounds(10, 100, 230, 30);
		add(field);
		JButton btn = new JButton ("Change Title");
		btn.setBounds(50, 150, 130, 30);
		add(btn);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				title.setText(field.getText());
				dispose();
			}
		});
				
		
		setSize (250,350);
//		setBackground(Color.WHITE);
//		setLocationRelativeTo(null);
	}
}
