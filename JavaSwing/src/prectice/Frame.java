package prectice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Frame {
	
	public static void start () {
	
	JFrame jf = new JFrame ();
	jf.setLayout(new BorderLayout());
	jf.setSize(640, 640*9/12);
	jf.setResizable(false);
	jf.setLocationRelativeTo(null);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JLabel head = new JLabel ("I'am Title");
	head.setHorizontalAlignment(JLabel.CENTER);
	head.setFont(head.getFont().deriveFont(50.0f));
	jf.add(head, BorderLayout.NORTH);
	
	JTextArea area = new JTextArea ();
	jf.add(area, BorderLayout.CENTER);
	
	JPanel btnp = new JPanel ();
	btnp.setBackground(Color.LIGHT_GRAY);
	
	JButton btn1 = new JButton ("Edit");
	btn1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			head.setText(area.getText());
		}
	});
	btnp.add(btn1);
	
	JButton btn2 = new JButton ("Input");
	btn2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			area.append("I don't give up Fuck\n");
		}
	});
	btnp.add(btn2);
	
	JButton btn3 = new JButton ("Clear");
	btn3.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			area.setText(null);
		}
	});
	btnp.add(btn3);
	
	JButton btn4 = new JButton ("Exit");
	btn4.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	btnp.add(btn4);
	
	jf.add(btnp, BorderLayout.WEST);
	
	
	
	jf.setVisible(true);
		   	
		   }
	}

