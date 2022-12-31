package prectice;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


class ImagePanel  extends JPanel {
	
	private Image img;
	
	public ImagePanel (Image img) {
		this.img = img;
		// image(=panel) 의 크기를 조절하는 method
		setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setLayout (null);
	}
	// image 불러오는 method
	public void paintComponent (Graphics g) {
		g.drawImage(img,0,0,null);
	}
}
public class Test{
		
		public static void start () {
			JFrame jf = new JFrame ();
			jf.setLayout(null);
			
				// instance 생성 (image icon 으로 경로 호출)
			ImagePanel panel = new ImagePanel(
					new ImageIcon("./image/background.jpg").getImage());
				// frame 에 panel 삽
			jf.add(panel);
			
			// 경로를 확인하는 code
//			File f = new File("./image/background.jpg");
//			System.out.println(f.exists()?"Exists":"doesnt exists");
			
			jf.setTitle("Image Challange");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.setSize(600,450);
			jf.setVisible(true);
		}
	
	
	public static void main(String[] args) {
		
		start();
	}
}
