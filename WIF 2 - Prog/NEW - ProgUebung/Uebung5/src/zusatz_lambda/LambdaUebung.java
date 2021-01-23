package zusatz_lambda;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Rectangle;

//Lambda Ausdrücke helfen, die oft schlecht lesbaren, 
//langen Deklarationen innerer, anonymer Klassen zu vermeiden

//Lamdas repräsentieren also eine anonyme Funktion

//Nur selten oder gar nicht wiederverwendete Klassen 
//werden in Java häufig und gerne als anonyme Klassen geschrieben

//Schreibweise: Arguments -> Expression


public class LambdaUebung extends JFrame{
	private JButton btnNewButton;
	private JTextField textField;
	
	public LambdaUebung() {
		setBounds(new Rectangle(500, 300, 450, 300));
		setTitle("Lambda\u00DCbung");
		getContentPane().setLayout(null);
		getContentPane().add(getBtnNewButton());
		getContentPane().add(getTextField());
	}

	public static void main(String[] args) {
		LambdaUebung lu = new LambdaUebung();
		lu.setVisible(true);
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Button");
			btnNewButton.setBounds(46, 100, 137, 56);
			//anonyme Klasse beginn
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textField.setText("Button angeklickt");
				}
			});
			//anonyme Klasse ende
			
			//Die hier deklarierte anonyme Klasse implementiert das Interface ActionListener, 
			//das nur eine einzige Methode besitzt. Seit Java 8.0 werden solche Interfaces 
			//mit genau einer abstrakten Methode als funktionale Interfaces bezeichnet. 
			//Um deren Methoden zu nutzen, können ab Java 8.0 sog. Lambda Ausdrücke eingesetzt werden. 
			//Das obige Beispiel kann hiermit auf die folgende Weise umformuliert werden:
			
			//alternative Schreibweise als Lambda-Ausdruck
			btnNewButton.addActionListener(e -> {textField.setText("Button angeklickt");} );
			
		}
		return btnNewButton;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(193, 100, 191, 56);
			textField.setColumns(10);
		}
		return textField;
	}
}
