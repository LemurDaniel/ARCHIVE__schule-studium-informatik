package aufgabe2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.List;
import java.awt.Color;
import javax.swing.JRadioButton;

public class TaschenrechnerC extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblEingabe1;
	private JLabel lblEingabe2;
	private JLabel lblErg;
	private JLabel lblInfo;
	private JLabel lblMethode;
	private JTextField tFe1;
	private JTextField tFe2;
	private JTextField tFerg;
	private JButton btnBerechne;
	private JButton btnReset;
	private List list;
	private JRadioButton rdbtnInteger;
	private JRadioButton rdbtnDouble;
	private JLabel lblInfo2;


	
	public TaschenrechnerC() {
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
		getContentPane().add(getLblInfo());
		getContentPane().add(getRdbtnInteger());
		getContentPane().add(getRdbtnDouble());
		getContentPane().add(getLblMethode());
		list.select(0);
		rdbtnInteger.setSelected(true);
		getContentPane().add(getLblInfo2());
		setTitle("Taschenrechner");
	}

	
	
	public static void main(String[] args) {
		TaschenrechnerC t = new TaschenrechnerC();
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
	private JLabel getLblErg() {
		if (lblErg == null) {
			lblErg = new JLabel("Ergebnis");
			lblErg.setFont(new Font("Dialog", Font.BOLD, 12));
			lblErg.setBounds(349, 91, 64, 14);
		}
		return lblErg;
	}
	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("");
			lblInfo.setFont(new Font("Dialog", Font.BOLD, 12));
			lblInfo.setForeground(Color.RED);
			lblInfo.setBounds(10, 11, 414, 22);
		}
		return lblInfo;
	}
	private JLabel getLblMethode() {
		if (lblMethode == null) {
			lblMethode = new JLabel("Rechenmethode w\u00E4hlen: ");
			lblMethode.setFont(new Font("Dialog", Font.BOLD, 12));
			lblMethode.setBounds(99, 205, 240, 22);
		}
		return lblMethode;
	}
	
	
	private JTextField getTFe1() {
		if (tFe1 == null) {
			tFe1 = new JTextField();
			tFe1.setFont(new Font("Dialog", Font.PLAIN, 12));
			tFe1.setBounds(100, 71, 86, 22);
			tFe1.setColumns(10);
		}
		return tFe1;
	}
	private JTextField getTFe2() {
		if (tFe2 == null) {
			tFe2 = new JTextField();
			tFe2.setFont(new Font("Dialog", Font.PLAIN, 12));
			tFe2.setColumns(10);
			tFe2.setBounds(100, 104, 86, 22);
		}
		return tFe2;
	}
	private JTextField getTFerg() {
		if (tFerg == null) {
			tFerg = new JTextField();
			tFerg.setFont(new Font("Dialog", Font.PLAIN, 12));
			tFerg.setBounds(253, 89, 86, 20);
			tFerg.setColumns(10);
			tFerg.setEditable(false);
		}
		return tFerg;
	}
	
	private JButton getBtnBerechne() {
		if (btnBerechne == null) {
			btnBerechne = new JButton("berechne");
			btnBerechne.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnBerechne.setBounds(100, 171, 89, 23);
			btnBerechne.addActionListener( e -> { 
				try{	
					if( rdbtnDouble.isSelected() )
						tFerg.setText( Rechner.berechneDouble(tFe1, tFe2, list.getSelectedItem())+"" );
					else
						tFerg.setText( Rechner.berechneInt( tFe1, tFe2, list.getSelectedItem())+"" );
				}catch(Exception e1) {
					lblInfo.setText( e1.getClass().getSimpleName()+": " );
					lblInfo2.setText(e1.getMessage());
					return;
				}
				lblInfo.setText(null);
				lblInfo2.setText(null);
			});
		}
		return btnBerechne;
	}
	private JButton getBtnReset() {
		if (btnReset == null) {
			btnReset = new JButton("reset");
			btnReset.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnReset.setBounds(250, 171, 89, 23);
			btnReset.addActionListener( e -> {
				lblInfo.setText(null);
				lblInfo2.setText(null);
				rdbtnInteger.setSelected(true);
				rdbtnDouble.setSelected(false);
				tFe1.setText(null);
				tFe2.setText(null);
				tFerg.setText(null);
			});
		}
		return btnReset;
	}
	private JRadioButton getRdbtnInteger() {
		if (rdbtnInteger == null) {
			rdbtnInteger = new JRadioButton("Integer");
			rdbtnInteger.setFont(new Font("Dialog", Font.BOLD, 12));
			rdbtnInteger.setBounds(100, 231, 109, 23);
			rdbtnInteger.addActionListener( e -> rdbtnDouble.setSelected(!rdbtnInteger.isSelected()) );
		}
		return rdbtnInteger;
	}
	private JRadioButton getRdbtnDouble() {
		if (rdbtnDouble == null) {
			rdbtnDouble = new JRadioButton("Double");
			rdbtnDouble.setFont(new Font("Dialog", Font.BOLD, 12));
			rdbtnDouble.setBounds(214, 231, 109, 23);
			rdbtnDouble.addActionListener( e -> rdbtnInteger.setSelected(!rdbtnDouble.isSelected()) );
		}
		return rdbtnDouble;
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
	private JLabel getLblInfo2() {
		if (lblInfo2 == null) {
			lblInfo2 = new JLabel("");
			lblInfo2.setForeground(Color.RED);
			lblInfo2.setFont(new Font("Dialog", Font.BOLD, 12));
			lblInfo2.setBounds(10, 36, 414, 22);
		}
		return lblInfo2;
	}
}
