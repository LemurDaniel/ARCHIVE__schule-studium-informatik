package aufgabe2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.awt.Color;

public class TRkompakt extends JFrame {
	
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
	private KeyListener kl;
	private JButton btnNewButton;
	
	public TRkompakt() {
		kl = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==10)	btnBerechne.doClick();
			}
		};
		
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
		TRkompakt t = new TRkompakt();
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
			tFe1.addKeyListener(kl);
		}
		return tFe1;
	}
	private JTextField getTFe2() {
		if (tFe2 == null) {
			tFe2 = new JTextField();
			tFe2.setColumns(10);
			tFe2.setBounds(100, 104, 86, 22);
			tFe2.addKeyListener(kl);
		}
		return tFe2;
	}
	private JTextField getTFerg() {
		if (tFerg == null) {
			tFerg = new JTextField();
			tFerg.setBounds(253, 89, 86, 20);
			tFerg.setColumns(10);
			tFerg.addKeyListener(kl);
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
					tFerg.setText(berechne(tFe1, tFe2, list.getSelectedItem())+"");					
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
			list.addKeyListener(kl);
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
	
	public Number berechne(JTextField tfArg1, JTextField tfArg2, String operation) throws Exception{
	
		BigDecimal num1 = new BigDecimal(tfArg1.getText());
		BigDecimal num2 = new BigDecimal(tfArg2.getText());
		BigDecimal erg = null;
		
		switch(operation) {
		case "+": erg = num1.add(num2); break;
		case "-": erg = num1.subtract(num2); break;
		case "*": erg = num1.multiply(num2); break;
		case "/": 	if (num2.equals(BigDecimal.ZERO))
						throw new Exception("Ergebnis nicht verwertbar (Division durch Null) ");
					erg = num1.divide(num2); 
					break;
		}
		if (Double.isInfinite(erg.doubleValue()) )
			throw new ArithmeticException("Ergebnis ist unendlich");
			
		return erg;
	}	

}


