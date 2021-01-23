package aufgabe2.variante1;


import javax.swing.JFrame;

public class MyJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyJFrame(){
		add(new Content());
	}
	
	public static void main(String args[]) {
		MyJFrame m = new MyJFrame();
		m.setVisible(true);
		m.setSize(800, 600);

	}
	
	

	
	
	

}
