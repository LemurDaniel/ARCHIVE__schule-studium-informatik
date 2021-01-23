package aufgabe2.variante2;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyJFrame extends JFrame {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


		MyJFrame(){
			JTextField t1 = new JTextField();
			t1.setBounds(0, 0, 100, 25);
			t1.setBackground(Color.magenta);
			
			JTextField t2 = new JTextField("Text: Feld 2");
			t2.setBounds(0, 25, 80, 25);
			t2.setEditable(false);
			t2.setBackground(Color.lightGray);
			
			t1.setText(t2.getText());
		
			
			JPanel p = new JPanel();
			p.add(t1);
			p.add(t2);
			p.setLayout(null);
			
			add(p);
		}
		
		
		public static void main(String[] args) {
			MyJFrame jf = new MyJFrame();
			jf.setVisible(true);
			jf.setSize(800, 600);
		}
}
