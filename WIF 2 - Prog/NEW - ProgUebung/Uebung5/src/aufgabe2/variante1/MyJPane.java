package aufgabe2.variante1;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;

public class MyJPane extends JPanel {

	public MyJPane() {
		
		setLayout(null);
		
		JTextField t1 = new JTextField();
		t1.setBounds(0, 0, 100, 25);
		t1.setBackground(Color.magenta);
		
		JTextField t2 = new JTextField("Text: Feld 2");
		t2.setBounds(0, 25, 80, 25);
		t2.setEditable(false);
		t2.setBackground(Color.lightGray);
		
		t1.setText(t2.getText());
		
		add(t1);
		add(t2);
	}
}
