package aufgabe2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.List;
import java.awt.Color;

public class TaschenrechnerDouble extends JFrame {
	
	private JLabel lblEingabe1;
	private JLabel lblEingabe2;
	private JTextField tFe1;
	private JTextField tFe2;
	private JTextField tFerg;
	private JLabel lblErg;
	private JButton btnBerechne;
	private JButton btnReset;
	private List list;
	private JLabel lblStatus;
	
	public TaschenrechnerDouble() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(getLblEingabe1());
		getContentPane().add(getLblEingabe2());
		getContentPane().add(getTFe1());
		getContentPane().add(getTFe2());
		getContentPane().add(getTFerg());
		getContentPane().add(getLblErg());
		getContentPane().add(getBtnBerechne());
		getContentPane().add(getBtnReset());
		getContentPane().add(getList());
		getContentPane().add(getLblStatus());
		list.select(0);
	}

	
	
	public static void main(String[] args) {
		TaschenrechnerDouble t = new TaschenrechnerDouble();
		t.setVisible(true);
		t.setBounds(500, 250, 450, 300);

	}

	private JLabel getLblEingabe1() {
		if (lblEingabe1 == null) {
			lblEingabe1 = new JLabel("Eingabe 1:");
			lblEingabe1.setFont(new Font("Dialog", Font.BOLD, 12));
			lblEingabe1.setBounds(10, 69, 80, 22);
		}
		return lblEingabe1;
	}
	private JLabel getLblEingabe2() {
		if (lblEingabe2 == null) {
			lblEingabe2 = new JLabel("Eingabe 2:");
			lblEingabe2.setFont(new Font("Dialog", Font.BOLD, 12));
			lblEingabe2.setBounds(10, 102, 80, 22);
		}
		return lblEingabe2;
	}
	private JTextField getTFe1() {
		if (tFe1 == null) {
			tFe1 = new JTextField();
			tFe1.setBounds(100, 71, 86, 22);
			tFe1.setColumns(10);
		}
		return tFe1;
	}
	private JTextField getTFe2() {
		if (tFe2 == null) {
			tFe2 = new JTextField();
			tFe2.setColumns(10);
			tFe2.setBounds(100, 104, 86, 22);
		}
		return tFe2;
	}
	private JTextField getTFerg() {
		if (tFerg == null) {
			tFerg = new JTextField();
			tFerg.setBounds(253, 89, 86, 20);
			tFerg.setColumns(10);
		}
		return tFerg;
	}
	private JLabel getLblErg() {
		if (lblErg == null) {
			lblErg = new JLabel("Ergebnis");
			lblErg.setFont(new Font("Dialog", Font.BOLD, 12));
			lblErg.setBounds(349, 91, 64, 14);
		}
		return lblErg;
	}
	private JButton getBtnBerechne() {
		if (btnBerechne == null) {
			btnBerechne = new JButton("berechne");
			btnBerechne.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnBerechne.setBounds(135, 171, 89, 23);
			btnBerechne.addActionListener(e -> {
				try {
					lblStatus.setText(null);
					tFerg.setText( berechneDouble(tFe1, tFe2, list.getSelectedItem())+"" );
				}catch(NumberFormatException nfe) {
					lblStatus.setText("Konvertierungsfehler");
				}catch(Exception e1) {
					lblStatus.setText(e1.getMessage());
				}
			});
		}
		return btnBerechne;
	}
	private JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton("reset");
			btnReset.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnReset.setBounds(234, 171, 89, 23);
			btnReset.addActionListener( e ->{
				tFe1.setText(null);
				tFe2.setText(null);
				tFerg.setText(null);
				lblStatus.setText(null);
				list.select(0);
			});
		}
		return btnReset;
	}
	private List getList() {
		if (list == null) {
			list = new List();
			list.setBounds(205, 64, 29, 72);
			list.add("+");
			list.add("-");
			list.add("*");
			list.add("/");
		}
		return list;
	}
	
	private JLabel getLblStatus() {
		if (lblStatus == null) {
			lblStatus = new JLabel("");
			lblStatus.setForeground(Color.RED);
			lblStatus.setFont(new Font("Dialog", Font.BOLD, 12));
			lblStatus.setBounds(10, 218, 414, 14);
		}
		return lblStatus;
	}
	
	public double berechneDouble(JTextField tfArg1, JTextField tfArg2, String operation) throws Exception{
		double num1, num2, erg;
		
		//try {
			num1 = Double.parseDouble(tfArg1.getText());
			num2 = Double.parseDouble(tfArg2.getText());
		//}catch(NumberFormatException e) {
		//	throw e;
		//}
		
		// Bei valueOf automatisch richtige Kovertierung
		switch(operation) {
		case "+": erg = num1+num2; break;
		case "-": erg = num1-num2; break;
		case "*": erg = num1*num2; break;
		case "/": erg = num1/num2; 
					if(Double.isInfinite(erg))
						throw new Exception("Ergebnis nicht verwertbar (Division durch Null) ");
					break;

		default: erg=0; break;
		}
		
		
		return erg;
	}
	
}

