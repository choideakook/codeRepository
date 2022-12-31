package login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Customer_App {

	private JFrame frame;
	private JTextField field_id;
	private JPasswordField field_pw;
	private JTextField name;
	private JTextField phone;
	private JTextField age;
	private JTextField birthday;
	private JTextField search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer_App window = new Customer_App();
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
	public Customer_App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Customer App Service");
		frame.getContentPane().setLayout(null);
		
		JPanel background = new JPanel();
		background.setBounds(0, 0, 693, 497);
		background.setLayout(null);
		
		ImageIcon icon = new ImageIcon("./image/basic.jpg");
		Image img = icon.getImage();
		Image cimg = img.getScaledInstance(693, 497, Image.SCALE_SMOOTH);
		ImageIcon cicon = new ImageIcon(cimg);
		JLabel back = new JLabel(cicon);
		back.setBounds(0, 0, 693, 497);
		background.add(back);
		
		JPanel login = new JPanel();
		login.setBounds(200, 130, 299, 200);
		login.setBackground(new Color(247, 247, 247));
		login.setVisible(true);
		
		JPanel main = new JPanel();
		main.setBackground(new Color(247, 247, 247));
		main.setBounds(20, 30, 655, 400);
		main.setVisible(false);
		
		JPanel db = new JPanel();
		db.setBackground(new Color(247, 247, 247));
		db.setBounds(20, 30, 655, 400);
		db.setVisible(false);
		frame.getContentPane().add(db);
		db.setLayout(null);
		
		String[] head = new String [] {"Name","Phone","Gender","Age","Note"};
		String[][] data = Customer.getCustomers();
		
		JTable table = new JTable(data,head);
		table.setLocation(130, 99);
		table.setRowHeight(30);
		table.setAlignmentX(0);
		table.setPreferredScrollableViewportSize(new Dimension(400, 200));
		JScrollPane t1 = new JScrollPane(table);
		t1.setLocation(33, 113);
		t1.setSize(571, 200);
		db.add(t1);
		
		JLabel db_title = new JLabel("Customer Info");
		db_title.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		db_title.setForeground(new Color(0, 0, 0));
		db_title.setHorizontalAlignment(SwingConstants.CENTER);
		db_title.setBounds(23, 6, 612, 81);
		db.add(db_title);
		
		JButton btn_main = new JButton("Before");
		btn_main.setBounds(268, 347, 117, 29);
		db.add(btn_main);
		
		search = new JTextField();
		search.setBounds(33, 75, 571, 26);
		db.add(search);
		search.setColumns(10);
		
		search.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String val = search.getText();
				TableRowSorter<TableModel> trs = new TableRowSorter<>(table.getModel());
				table.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(val));
			}
		});
		// table column 의 좌우 넓이 조정하는 method
		TableColumnModel model = table.getColumnModel();
		btn_main.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.setVisible(true);
				db.setVisible(false);
			}
		});
		frame.getContentPane().add(main);
		main.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome This is Main Panel");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(170, 18, 308, 38);
		main.add(lblNewLabel_1);
		
		JLabel table_name = new JLabel("Name");
		table_name.setHorizontalAlignment(SwingConstants.CENTER);
		table_name.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		table_name.setBounds(20, 92, 125, 30);
		main.add(table_name);
		
		JLabel table_Age = new JLabel("Age");
		table_Age.setHorizontalAlignment(SwingConstants.CENTER);
		table_Age.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		table_Age.setBounds(20, 177, 125, 30);
		main.add(table_Age);
		
		JLabel table_Gender = new JLabel("Gender");
		table_Gender.setHorizontalAlignment(SwingConstants.CENTER);
		table_Gender.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		table_Gender.setBounds(20, 266, 125, 30);
		main.add(table_Gender);
		
		JLabel table_Phone = new JLabel("Phone");
		table_Phone.setHorizontalAlignment(SwingConstants.CENTER);
		table_Phone.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		table_Phone.setBounds(280, 92, 125, 30);
		main.add(table_Phone);
		
		JLabel table_Birthday = new JLabel("Birthday");
		table_Birthday.setHorizontalAlignment(SwingConstants.CENTER);
		table_Birthday.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		table_Birthday.setBounds(280, 177, 125, 30);
		main.add(table_Birthday);
		
		JLabel table_Note = new JLabel("Note");
		table_Note.setHorizontalAlignment(SwingConstants.CENTER);
		table_Note.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		table_Note.setBounds(280, 266, 125, 30);
		main.add(table_Note);
		
		name = new JTextField();
		name.setBounds(119, 96, 167, 26);
		main.add(name);
		name.setColumns(10);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(386, 96, 167, 26);
		main.add(phone);
		
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(119, 181, 167, 26);
		main.add(age);
		
		birthday = new JTextField();
		birthday.setColumns(10);
		birthday.setBounds(386, 181, 167, 26);
		main.add(birthday);
		
		JComboBox gender = new JComboBox(new String[] {"Male","Female"});
		gender.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		gender.setBounds(119, 271, 167, 27);
		main.add(gender);
		
		JTextArea note = new JTextArea();
		note.setBounds(386, 275, 167, 57);
		note.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		main.add(note);
		
		JButton btn_submit = new JButton("Submit");
		btn_submit.setBounds(180, 351, 117, 29);
		main.add(btn_submit);
		
		JButton btn_dataBase = new JButton("DataBase");
		btn_dataBase.setBounds(302, 351, 117, 29);
		main.add(btn_dataBase);
		
		btn_submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameTxt = name.getText();
				String ageTxt = age.getText();
				String phoneTxt = phone.getText();
				String genderTxt = gender.getSelectedItem().toString();
				String noteAr = note.getText();
				
				Customer.createCustomer(nameTxt, phoneTxt, genderTxt, ageTxt, noteAr);
				JOptionPane.showMessageDialog(null, "your data has been saved successfully");
				name.setText(null);
				age.setText(null);
				phone.setText(null);
				note.setText(null);
			}
		});
		btn_dataBase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.setVisible(false);
				db.setVisible(true);
			}
		});
		
			// 첫번째 column 좌우 넓이 (0 = 첫번째 column / 최소 100 픽셀)
		model.getColumn(0).setPreferredWidth(100);
		model.getColumn(2).setPreferredWidth(50);
		model.getColumn(3).setPreferredWidth(20);
		frame.getContentPane().add(login);
		login.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer App");
		lblNewLabel.setForeground(new Color(114, 119, 219));
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(36, 29, 214, 16);
		login.add(lblNewLabel);
		
		JLabel label_id = new JLabel("ID :");
		label_id.setHorizontalAlignment(SwingConstants.RIGHT);
		label_id.setBounds(20, 77, 61, 16);
		login.add(label_id);
		
		JLabel label_pw = new JLabel("PW :");
		label_pw.setHorizontalAlignment(SwingConstants.RIGHT);
		label_pw.setBounds(20, 114, 61, 16);
		login.add(label_pw);
		
		JButton btn_signin = new JButton("Sign in");
		btn_signin.setBounds(28, 147, 117, 29);
		login.add(btn_signin);
		
		JButton btn_login = new JButton("Log in");
		btn_login.setBounds(146, 147, 117, 29);
		login.add(btn_login);
		
		field_id = new JTextField();
		field_id.setBounds(93, 72, 157, 26);
		login.add(field_id);
		field_id.setColumns(10);
		
		field_pw = new JPasswordField();
		field_pw.setBounds(91, 109, 159, 26);
		login.add(field_pw);
		
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(field_id.getText().equals("audtls") && Arrays.equals(field_pw.getPassword(), "qwer".toCharArray())) {
					login.setVisible(false);
					main.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "ID and PW was not correct");
				}
				
			}
		});
		
		btn_signin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Customer.getConnection();
			}
		});
		
		frame.setJMenuBar(bars());
		frame.setResizable(false);
		frame.getContentPane().add(background);
		background.setVisible(true);
		frame.setBounds(100, 100, 693, 497);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public JMenuBar bars () {
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu about = new JMenu ("About");
		
		bar.add(file);
		bar.add(about);
		
		JMenuItem open = new JMenuItem ("Open");
		JMenuItem exit = new JMenuItem ("Exit");
		file.add(open);
		file.addSeparator();
		file.add(exit);
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		return bar;
	}
}
