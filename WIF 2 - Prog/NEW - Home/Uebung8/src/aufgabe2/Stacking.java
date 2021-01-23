package aufgabe2;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Stacking extends JFrame{
	private JTextField tf1;
	private JTextField f;
	private JButton btnDrehen;
	public Stacking() {
		getContentPane().setLayout(null);
		getContentPane().add(getTf1());
		getContentPane().add(getF());
		getContentPane().add(getBtnDrehen());
	}

	public static void main(String[] args) {
		Stacking s = new Stacking();
		s.setBounds(500, 300, 455, 300);
		s.setVisible(true);
	}
	private JTextField getTf1() {
		if (tf1 == null) {
			tf1 = new JTextField();
			tf1.setHorizontalAlignment(SwingConstants.CENTER);
			tf1.setFont(new Font("Dialog", Font.BOLD, 13));
			tf1.setBounds(10, 54, 414, 28);
			tf1.setColumns(10);
		}
		return tf1;
	}
	private JTextField getF() {
		if (f == null) {
			f = new JTextField();
			f.setHorizontalAlignment(SwingConstants.CENTER);
			f.setEditable(false);
			f.setFont(new Font("Dialog", Font.BOLD, 13));
			f.setColumns(10);
			f.setBounds(10, 161, 414, 28);
		}
		return f;
	}
	private JButton getBtnDrehen() {
		if (btnDrehen == null) {
			btnDrehen = new JButton("drehen");
			btnDrehen.setFont(new Font("Dialog", Font.BOLD, 13));
			btnDrehen.setBounds(158, 109, 89, 23);
			btnDrehen.addActionListener(al->{
				Stack<Character> stack = new Stack<>();
				for(int i=0; i<tf1.getText().length(); i++) {
					stack.push(tf1.getText().charAt(i));
				}
				StringBuilder s = new StringBuilder();
				while(!stack.isEmpty()) s.append(stack.pop());
				f.setText(s.toString());
			});
		}
		return btnDrehen;
	}
}
