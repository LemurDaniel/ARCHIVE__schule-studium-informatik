package aufgabe4;

import javax.swing.JFrame;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.Button;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KundenverwaltungGUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextArea textArea;
	private Button btn_speichern;
	private Button btn_anzeigen;
	private Button btn_loeschen;
	private Button btn_exit;
	private Label lbl_kundenname;
	private Label lbl_adresse;
	private TextField textField;
	private TextField textField_1;
	private Label lbl_kundenliste;
	private Label lbl_kundenanzeige;
	
	private Kunde kunde = new Kunde(null, null);

	public KundenverwaltungGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Kundenverwaltung");
		getContentPane().setLayout(null);
		getContentPane().add(getTextArea());
		getContentPane().add(getBtn_speichern());
		getContentPane().add(getBtn_anzeigen());
		getContentPane().add(getBtn_loeschen());
		getContentPane().add(getBtn_exit());
		getContentPane().add(getLbl_kundenname());
		getContentPane().add(getLbl_adresse());
		getContentPane().add(getTextField());
		getContentPane().add(getTextField_1());
		getContentPane().add(getLbl_kundenliste());
		getContentPane().add(getLbl_kundenanzeige());
	}

	public static void main(String[] args) {
		KundenverwaltungGUI kvg = new KundenverwaltungGUI();
		kvg.setBounds(230, 230, 500, 350);
		kvg.setVisible(true);

	}

	private TextArea getTextArea() {
		if (textArea == null) {
			textArea = new TextArea();
			textArea.setEditable(false);
			textArea.setBackground(Color.WHITE);
			textArea.setBounds(128, 110, 304, 160);
		}
		return textArea;
	}

	private Button getBtn_speichern() {
		if (btn_speichern == null) {
			btn_speichern = new Button("speichern");
			btn_speichern.setBounds(23, 115, 85, 22);
			// externe Klasse/ äußerer Adapter
			KundeAction ac = new KundeAction(this);
			btn_speichern.addActionListener(ac);
		}
		return btn_speichern;
	}

	private Button getBtn_anzeigen() {
		if (btn_anzeigen == null) {
			btn_anzeigen = new Button("anzeigen");
			btn_anzeigen.setBounds(23, 143, 85, 22);
			// GUI Klasse selbst
			btn_anzeigen.addActionListener(this);
		}
		return btn_anzeigen;
	}

	private Button getBtn_loeschen() {
		if (btn_loeschen == null) {
			btn_loeschen = new Button("l\u00F6schen");
			btn_loeschen.setBounds(23, 171, 85, 22);
			//innere Klasse
			KundeLoeschen kl = new KundeLoeschen();
			btn_loeschen.addActionListener(kl);
		}
		return btn_loeschen;
	}

	private Button getBtn_exit() {
		if (btn_exit == null) {
			btn_exit = new Button("exit");
			//Anonymer innerer Adapter
			btn_exit.addActionListener(e -> System.exit(0));
			btn_exit.setBounds(23, 199, 85, 22);
		}
		return btn_exit;
	}

	private Label getLbl_kundenname() {
		if (lbl_kundenname == null) {
			lbl_kundenname = new Label("Kundenname:");
			lbl_kundenname.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbl_kundenname.setBounds(23, 10, 85, 22);
		}
		return lbl_kundenname;
	}

	private Label getLbl_adresse() {
		if (lbl_adresse == null) {
			lbl_adresse = new Label("Firmenadresse:");
			lbl_adresse.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbl_adresse.setBounds(23, 38, 99, 22);
		}
		return lbl_adresse;
	}

	private TextField getTextField() {
		if (textField == null) {
			textField = new TextField();
			textField.setBounds(146, 10, 106, 22);
		}
		return textField;
	}

	private TextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new TextField();
			textField_1.setBounds(146, 38, 106, 22);
		}
		return textField_1;
	}

	private Label getLbl_kundenliste() {
		if (lbl_kundenliste == null) {
			lbl_kundenliste = new Label("Kundenliste");
			lbl_kundenliste.setFont(new Font("Dialog", Font.BOLD, 12));
			lbl_kundenliste.setBounds(128, 92, 81, 22);
		}
		return lbl_kundenliste;
	}

	private Label getLbl_kundenanzeige() {
		if (lbl_kundenanzeige == null) {
			lbl_kundenanzeige = new Label(null);
			lbl_kundenanzeige.setFont(new Font("Dialog", Font.BOLD, 12));
			lbl_kundenanzeige.setForeground(Color.RED);
			lbl_kundenanzeige.setBounds(23, 66, 364, 22);
		}
		return lbl_kundenanzeige;
	}
	
	// Methode für den Speicher Button
	public void storeKunde() {
		if( textField.getText().length() >2 && textField_1.getText().length() >2) {
			kunde = new Kunde(textField.getText(), textField_1.getText());
			textField.setText("");
			textField_1.setText(null);
			lbl_kundenanzeige.setText(null);
		} else 
			lbl_kundenanzeige.setText("Es wird nicht gespeichert: Kundendaten unvollständig");
		
	}

	
	// Methode für den Anzeige-Button
	@Override
	public void actionPerformed(ActionEvent e) {
		textArea.append(kunde.getName() + ", " + kunde.getAdresse() + "\n");
		lbl_kundenanzeige.setText("Kunde angezeigt");
	}
	
	
	private class KundeLoeschen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			textArea.setText(null);
			lbl_kundenanzeige.setText("Kundenliste gelöscht");
		}
		
	}
}
