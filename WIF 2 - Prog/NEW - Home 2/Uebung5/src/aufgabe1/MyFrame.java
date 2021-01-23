package aufgabe1;

import java.awt.Frame;

public class MyFrame extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public MyFrame() {
		add(new Panel1());
	}
	
	
	public static void main( String[] args) {
		MyFrame myFrame = new MyFrame();
		myFrame.setVisible(true);
		myFrame.setSize(800, 600);
	}
}
