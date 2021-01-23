package aufgabe4;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;

public class Konverter extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyTF textField;
	private JButton btnInt;
	private JButton btnDouble;
	private JButton btnDatum;
	private JLabel lblInfo;
	private JLabel lbl;
	
	public Konverter() {
		getContentPane().setLayout(null);
		getContentPane().add(getTextField());
		getContentPane().add(getBtnInt());
		getContentPane().add(getBtnDouble());
		getContentPane().add(getBtnDatum());
		getContentPane().add(getLblInfo());
		getContentPane().add(getLbl());
	}
	
	public static void main(String args[]){
		Konverter k = new Konverter();
		k.setBounds(500,250,460,300);
		k.setVisible(true);
	}
	
	
	private JTextField getTextField() {
		if (textField == null) {
			textField = new MyTF();
			textField.setFont(new Font("Dialog", Font.BOLD, 12));
			textField.setBounds(156, 84, 100, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	private JButton getBtnInt() {
		if (btnInt == null) {
			btnInt = new JButton("Integer");
			btnInt.setFont(new Font("Dialog", Font.BOLD, 12));
			btnInt.setBounds(46, 158, 89, 23);
			btnInt.addActionListener( e -> Konvertiere(textField::intZahl) );
		}
		return btnInt;
	}
	private JButton getBtnDouble() {
		if (btnDouble == null) {
			btnDouble = new JButton("Double");
			btnDouble.setFont(new Font("Dialog", Font.BOLD, 12));
			btnDouble.setBounds(167, 159, 89, 23);
			btnDouble.addActionListener( e -> Konvertiere(textField::doubleZahl) );
		}
		return btnDouble;
	}
	private JButton getBtnDatum() {
		if (btnDatum == null) {
			btnDatum = new JButton("Datum");
			btnDatum.setFont(new Font("Dialog", Font.BOLD, 12));
			btnDatum.setBounds(294, 159, 89, 23);
			btnDatum.addActionListener( e -> Konvertiere(textField::datum) );
		}
		return btnDatum;
	}
	private JLabel getLblInfo() {
		if (lblInfo == null) {
			lblInfo = new JLabel("");
			lblInfo.setForeground(Color.RED);
			lblInfo.setFont(new Font("Dialog", Font.BOLD, 12));
			lblInfo.setBounds(10, 11, 424, 14);
		}
		return lblInfo;
	}
	private JLabel getLbl() {
		if (lbl == null) {
			lbl = new JLabel("");
			lbl.setBounds(156, 45, 100, 20);
		}
		return lbl;
	}
	
	private void Konvertiere(method<?> m) {
		try {
			lblInfo.setText(null);
			lbl.setText(""+m.apply());
		}catch(Exception e) {
			lblInfo.setText(e.getMessage());
		}
	}
	
	@FunctionalInterface
	interface method<T> {
		T apply() throws Exception;
	}
}
