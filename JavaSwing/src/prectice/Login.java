package prectice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Login extends JFrame {

	public Login () {
		JPanel panel = new JPanel ();
		
		JLabel la1 = new JLabel("ID : ");
		panel.add(la1);
		
		JTextField ID = new JTextField(15);
		panel.add(ID);
		
		JLabel la2 = new JLabel("Password : ");
		panel.add(la2);
		
		JPasswordField PW = new JPasswordField(15);
		panel.add(PW);
		
		JButton in = new JButton ("Log in");
		in.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 로그인을 위한 id , pw 변수 생성
				String id = "hello1234";
				String pw = "world1234";
				// text field 입력값과 생성된 변수를 맞추기 위한 if 
				if(id.equals(ID.getText()) && pw.equals(PW.getText())) {
					JOptionPane.showMessageDialog(null, "Correct!\nHello World");
				}else {
					JOptionPane.showMessageDialog(null, "Fuck you go to hell");
				}
			}
		});
		panel.add(in);
		
		add(panel);
		
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,400);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
