package aufgabe6;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private DUhr panel;
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI t = new GUI();
		t.setVisible(true);
		t.setBounds(500,250,200,70);
	}	
	private DUhr getPanel() {
		if (panel == null) {
			panel = new DUhr();
			panel.setBounds(10, 0, 150, 50);
		}
		return panel;
	}
}
