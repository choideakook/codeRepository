package login;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class Test1 {

	private JFrame f1;
	private final JPanel p1 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test1 window = new Test1();
					window.f1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		f1 = new JFrame();
		
		f1.setSize(300, 400);
		f1.setLocationRelativeTo(null);
		f1.setResizable(false);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.getContentPane().setLayout(null);
		p1.setBounds(0, 0, 300, 372);
		f1.getContentPane().add(p1);
		p1.setLayout(null);
		
		JLabel title = new JLabel("Hello World");
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(18, 16, 263, 52);
		p1.add(title);
		
		int apple = 3470;
		int banana = 5440;
		int melon = 6730;
		
		String [] h1 = {"Product","Price","Including VAT"};
		Object [][] d1 = {{"Apple",apple,Math.floor(apple*1.1)},
				{"Banana",banana,Math.floor(banana*1.1)},
				{"Melon",melon,Math.floor(melon*1.1)}
		};
		JTable t1 = new JTable(d1,h1);
		t1.setBackground(new Color(255, 255, 255));
		JScrollPane s1 = new JScrollPane(t1);
		s1.setSize(263, 73);
		s1.setLocation(18, 63);
		p1.add(s1);
		
		JLabel buy = new JLabel("want buy");
		buy.setHorizontalAlignment(SwingConstants.CENTER);
		buy.setBounds(88, 147, 126, 31);
		p1.add(buy);
		
		JPanel p2 = new JPanel();
		p2.setBounds(67, 178, 173, 98);
		p1.add(p2);
		String [] item = {"Appel","Banana","Melon"};
		p2.setLayout(new GridLayout(item.length, 1, -10, 0));
		
		JLabel price = new JLabel("");
		price.setBounds(67, 297, 173, 43);
		p1.add(price);
		
		JCheckBox [] cart = new JCheckBox[item.length];
		
		for(int i =0; i<item.length ; i++) {
			cart[i] = new JCheckBox (item[i]);
			p2.add(cart[i]);
		}
		cart[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean on = cart[0].isSelected();
				if(on) {
					price.setText(item[0]);
				}else {
					price.setText("");
				}
			}
		});
		cart[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean on = cart[1].isSelected();
				if(on) {
					price.setText(item[1]);
				}else {
					price.setText("");
				}
			}
		});
		cart[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean on = cart[2].isSelected();
				if(on) {
					price.setText(item[2]);
				}else {
					price.setText("");
				}
			}
		});
	
	}
	
	
	
}
