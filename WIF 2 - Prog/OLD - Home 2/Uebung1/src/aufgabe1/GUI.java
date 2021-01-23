package aufgabe1;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.List;
import javax.swing.JLabel;
import java.awt.Color;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private JButton btnNewButton;
	private JTextField tfZ1;
	private JTextField tfN1;
	private JTextField tfZ2;
	private JTextField tfN2;
	private JTextField tfZ3;
	private JTextField tfN3;
	private List list_1;
	private JLabel lblNewLabel;
	private JLabel label;
	public GUI() {
		getContentPane().setLayout(null);
		getContentPane().add(getBtnNewButton());
		getContentPane().add(getTfZ1());
		getContentPane().add(getTfN1());
		getContentPane().add(getTfZ2());
		getContentPane().add(getTfN2());
		getContentPane().add(getTfZ3());
		getContentPane().add(getTfN3());
		getContentPane().add(getList_1());
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLabel());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI g = new GUI();
		g.setBounds(500,250,600, 350);
		g.setVisible(true);
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Calc");
			btnNewButton.setFont(new Font("Dialog", Font.BOLD, 13));
			btnNewButton.setBounds(122, 26, 102, 29);
			btnNewButton.addActionListener(l->{
				Bruch b1, b2, b3;
				try {
					lblNewLabel.setText(null);
					label.setText(null);
					b1 = new Bruch(tfZ1.getText(), tfN1.getText()).kuerze();
					tfN1.setText(b1.getNenner()+"");
					tfZ1.setText(b1.getZaehler()+"");
					b2 = new Bruch(tfZ2.getText(), tfN2.getText()).kuerze();
					tfN2.setText(b2.getNenner()+"");
					tfZ2.setText(b2.getZaehler()+"");
					
					switch(list_1.getSelectedItem()) {
					case "+":	b3 = b1.addiere(b2); break;
					case "-":	b3 = b1.subtrahiere(b2); break;
					case "/":	b3 = b1.dividiere(b2); break;
					case "*":	b3 = b1.multipliziere(b2); break;
					default: throw new Exception("Befehl nicht bekannt");
					}
					tfN3.setText(b3.getNenner()+"");
					tfZ3.setText(b3.getZaehler()+"");
				}catch(Exception e) {
					lblNewLabel.setText(e.getClass().getSimpleName());
					label.setText(e.getMessage());
				}
			});
		}
		return btnNewButton;
	}
	private JTextField getTfZ1() {
		if (tfZ1 == null) {
			tfZ1 = new JTextField();
			tfZ1.setFont(new Font("Dialog", Font.BOLD, 13));
			tfZ1.setBounds(48, 84, 86, 20);
			tfZ1.setColumns(10);
		}
		return tfZ1;
	}
	private JTextField getTfN1() {
		if (tfN1 == null) {
			tfN1 = new JTextField();
			tfN1.setFont(new Font("Dialog", Font.BOLD, 13));
			tfN1.setColumns(10);
			tfN1.setBounds(48, 115, 86, 20);
		}
		return tfN1;
	}
	private JTextField getTfZ2() {
		if (tfZ2 == null) {
			tfZ2 = new JTextField();
			tfZ2.setFont(new Font("Dialog", Font.BOLD, 13));
			tfZ2.setColumns(10);
			tfZ2.setBounds(215, 84, 86, 20);
		}
		return tfZ2;
	}
	private JTextField getTfN2() {
		if (tfN2 == null) {
			tfN2 = new JTextField();
			tfN2.setFont(new Font("Dialog", Font.BOLD, 13));
			tfN2.setColumns(10);
			tfN2.setBounds(215, 115, 86, 20);
		}
		return tfN2;
	}

	private JTextField getTfZ3() {
		if (tfZ3 == null) {
			tfZ3 = new JTextField();
			tfZ3.setFont(new Font("Dialog", Font.PLAIN, 13));
			tfZ3.setBounds(367, 84, 86, 20);
			tfZ3.setColumns(10);
		}
		return tfZ3;
	}
	private JTextField getTfN3() {
		if (tfN3 == null) {
			tfN3 = new JTextField();
			tfN3.setFont(new Font("Dialog", Font.PLAIN, 13));
			tfN3.setBounds(367, 115, 86, 20);
			tfN3.setColumns(10);
		}
		return tfN3;
	}
	private List getList_1() {
		if (list_1 == null) {
			list_1 = new List();
			list_1.setBounds(157, 74, 31, 72);
			list_1.add("+");
			list_1.add("-");
			list_1.add("*");
			list_1.add("/");
			list_1.select(0);
		}
		return list_1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setForeground(Color.RED);
			lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
			lblNewLabel.setBounds(48, 177, 253, 20);
		}
		return lblNewLabel;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setForeground(Color.RED);
			label.setFont(new Font("Dialog", Font.PLAIN, 13));
			label.setBounds(48, 208, 253, 20);
		}
		return label;
	}
}
