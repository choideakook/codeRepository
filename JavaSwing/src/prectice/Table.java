package prectice;

import java.awt.Dimension;

import javax.swing.*;

public class Table {

	public static void main(String[] args) {

		JFrame jf = new JFrame();
		jf.setLayout(null);
		
		String [] head = new String [] {"ID", "Name", "Country"};
		Object [] [] data = new Object [][] {
			{"1","DeaKuk","Korea"},
			{"2","MongTy","MTKuk"},
			{"3","Sommy","DangDangKuk"}
		};
		
		JTable table = new JTable (data,head);
		// table 의size를 정하는 method
		// setSize 와 같지만 parameter 값이 디맨션 값이 와야되서
		// new Dimension(가로,세로) 이런식으로 넣어줘야한다.
		// window에 반영하기 위해 불투명하게 만들어준뒤 pannel에 추가할 때 JScrollPane으로 추가해야 한다.
		table.setPreferredScrollableViewportSize(new Dimension(750,300));
		// setVisible 과 같은 method (table 버전)
		table.setFillsViewportHeight(true);
		
		JPanel jp1 = new JPanel();
		jp1.setBounds(1, 400, 800, 150);
		// table 의 사이즈를 반영하기위해 JScrall Pane 사용
		jp1.add(new JScrollPane(table));
		jf.add(jp1);
		
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(800,600);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

}
