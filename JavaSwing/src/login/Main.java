package login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {

	private JFrame frame;
	private JTextField id_field;
	private JPasswordField pw_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
			//back
		ImageIcon icon = new ImageIcon ("./image/Sad.jpg");
		Image img = icon.getImage();
		Image cimg = img.getScaledInstance(400, 400/3*4, Image.SCALE_SMOOTH );
		ImageIcon cicon = new ImageIcon(cimg);
		
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 400, 504);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel start_la = new JLabel("명티 공듀 팬클럽..");
		start_la.setForeground(new Color(245, 255, 249));
		start_la.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		start_la.setHorizontalAlignment(SwingConstants.CENTER);
		start_la.setBounds(100, 410, 217, 21);
		panel_1.add(start_la);
		
		JButton start_btn = new JButton("시작 하시겠습니까..?");
		start_btn.setBounds(100, 443, 223, 29);
		panel_1.add(start_btn);
		JLabel back1 = new JLabel(cicon);
		back1.setBounds(0, 0, 400, 504);
		panel_1.add(back1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 0, 400, 504);
		frame.getContentPane().add(panel_2);
		panel_2.setVisible(false);
		
		JLabel id_label = new JLabel("ID :");
		id_label.setForeground(new Color(242, 255, 247));
		id_label.setHorizontalAlignment(SwingConstants.RIGHT);
		id_label.setBounds(48, 386, 61, 16);
		panel_2.add(id_label);
		
		JLabel pw_label = new JLabel("PW :");
		pw_label.setForeground(new Color(245, 255, 249));
		pw_label.setHorizontalAlignment(SwingConstants.RIGHT);
		pw_label.setBounds(48, 414, 61, 16);
		panel_2.add(pw_label);
		
		id_field = new JTextField();
		id_field.setBounds(121, 381, 189, 26);
		panel_2.add(id_field);
		id_field.setColumns(10);
		
		pw_field = new JPasswordField();
		pw_field.setBounds(121, 409, 189, 26);
		panel_2.add(pw_field);
		
		JButton end_page = new JButton("죄송합니다..");
		end_page.setBounds(79, 436, 117, 29);
		panel_2.add(end_page);
		
		JButton login = new JButton("로그인");
		login.setForeground(new Color(0, 0, 0));
		login.setBounds(193, 436, 117, 29);
		panel_2.add(login);
		
		JLabel back2 = new JLabel(cicon);
		back2.setBounds(0, 0, 400, 504);
		panel_2.add(back2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 400, 504);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		panel_3.setVisible(false);
		
		
		JLabel massage1 = new JLabel("로그인 성공.. 환영합니다.....");
		massage1.setForeground(new Color(245, 255, 249));
		massage1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		massage1.setBounds(108, 374, 224, 47);
		panel_3.add(massage1);
		
		JLabel smail = new JLabel("씨익..ㅎ");
		smail.setForeground(new Color(245, 255, 249));
		smail.setBounds(281, 272, 61, 16);
		panel_3.add(smail);
		
		JButton go_panel4 = new JButton("서비스 시작하기...");
		go_panel4.setBounds(140, 433, 117, 29);
		panel_3.add(go_panel4);
		
		ImageIcon icon2 = new ImageIcon ("./image/Anger.jpg");
		Image img2 = icon2.getImage();
		Image cimg2 = img2.getScaledInstance(400, 400/3*4, Image.SCALE_SMOOTH );
		ImageIcon cicon2 = new ImageIcon(cimg2);
		JLabel back3 = new JLabel(cicon2);
		panel_3.add(back3);
		
		start_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
			}
		});
		end_page.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = "audtls7267";
				String pw = "mtmt7267";
			if(id.equals(id_field.getText()) && pw.equals(pw_field.getText())) {
				JOptionPane.showMessageDialog(null, "로그인 성공...");
				panel_2.setVisible(false);
				panel_3.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "가입되지 않은 회원입니다...");
			}
			}
		});
		
		frame.setSize(400, 400/3*4);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
