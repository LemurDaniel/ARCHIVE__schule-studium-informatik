package aufgabe1;

import java.awt.Panel;
import java.awt.TextField;
import java.awt.Color;

public class Panel1 extends Panel {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public Panel1() {
			setLayout(null);
			TextField t1 = new TextField();
			t1.setBackground(Color.lightGray);
			t1.setBounds(0, 0, 100, 25);
			
			TextField t2 = new TextField("Text: Feld 2");
			t2.setBackground(Color.magenta);
			t2.setEditable(false);
			t2.setBounds(0, 25, 80, 25);
			
			t1.setText(t2.getText());
			
			add(t1);
			add(t2);
		}
}
