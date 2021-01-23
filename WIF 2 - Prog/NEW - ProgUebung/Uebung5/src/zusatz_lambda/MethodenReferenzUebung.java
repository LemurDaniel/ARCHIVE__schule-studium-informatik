package zusatz_lambda;

import javax.swing.JFrame;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

//Methoden-Referenzen identifizieren Methoden ohne sie aufzurufen. 
//Syntaktisch trennen zwei Doppelpunkte den Klassenamen bzw. 
//die Referenz auf der linken Seite von einem Methodennamen auf der rechten.



public class MethodenReferenzUebung extends JFrame implements ActionListener {
	private JButton btnNewButton;
	private JTextField textField;
	
	public MethodenReferenzUebung() {
		setBounds(new Rectangle(500, 300, 450, 300));
		setTitle("Methoden Referenz \u00DCbung");
		getContentPane().setLayout(null);
		getContentPane().add(getBtnNewButton());
		getContentPane().add(getTextField());
	}

	public static void main(String[] args) {
		MethodenReferenzUebung mru = new MethodenReferenzUebung();
		mru.setVisible(true);

	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Button");
			btnNewButton.setBounds(58, 99, 130, 43);
			//Aufruf der Methode die das Interface ActionListener bereit stellt
			btnNewButton.addActionListener(this);
			
			//Aufruf mit Methoden Referenz
			btnNewButton.addActionListener(this::actionPerformed);
			
			//Eine Methoden-Referenz ist wie ein Lambda-Ausdruck ein Exemplar einer funktionalen Schnittstelle, 
			//jedoch für eine existierende Methode einer bekannten Klasse.
			
			
		}
		return btnNewButton;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(220, 97, 139, 45);
			textField.setColumns(10);
		}
		return textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		textField.setText("Button gedrückt");
		
	}
	
	
	
}
