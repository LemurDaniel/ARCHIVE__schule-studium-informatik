package aufgabe2.variante1;

import javax.swing.JFrame;

public class MyJFrame extends JFrame {

	public MyJFrame() {
		this.getContentPane().add(new MyJPane());
	}
	
	
	public static void main(String args[]) {
		MyJFrame m = new MyJFrame();
		m.setVisible(true);
		m.setSize(800, 600);
	}
}
