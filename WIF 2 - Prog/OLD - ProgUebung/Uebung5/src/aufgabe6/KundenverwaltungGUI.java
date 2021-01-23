package aufgabe6;

import javax.swing.JFrame;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.Button;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class KundenverwaltungGUI extends JFrame implements ActionListener {
	private TextArea textArea;
	private Button btn_speichern;
	private Button btn_anzeigen;
	private Button btn_loeschen;
	private Button btn_exit;
	private Label lbl_kundenname;
	private Label lbl_adresse;
	private TextField tF_kundenname;
	private TextField tF_Firmenadresse;
	private Label lbl_kundenliste;
	private Label lbl_kundenanzeige;
	
	private Kunde kunde = new Kunde();
	private Kunde[] kunden = {new Kunde(), new Kunde(), new Kunde()};
	private Label lbl_Kundenwahl;
	private Choice choice_Kunde;

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
		getContentPane().add(getTF_kundenname());
		getContentPane().add(getTF_Firmenadresse());
		getContentPane().add(getLbl_kundenliste());
		getContentPane().add(getLbl_kundenanzeige());
		getContentPane().add(getLbl_Kundenwahl());
		getContentPane().add(getChoice_Kunde());
		kunde = kunden[0];
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
			btn_speichern.addActionListener(this);
		}
		return btn_speichern;
	}

	private Button getBtn_anzeigen() {
		if (btn_anzeigen == null) {
			btn_anzeigen = new Button("anzeigen");
			btn_anzeigen.setBounds(23, 143, 85, 22);
			btn_anzeigen.addActionListener(this);
		}
		return btn_anzeigen;
	}

	private Button getBtn_loeschen() {
		if (btn_loeschen == null) {
			btn_loeschen = new Button("l\u00F6schen");
			btn_loeschen.setBounds(23, 171, 85, 22);
			btn_loeschen.addActionListener(this);
		}
		return btn_loeschen;
	}

	private Button getBtn_exit() {
		if (btn_exit == null) {
			btn_exit = new Button("exit");
			//Anonymer innerer Adapter
			btn_exit.addActionListener(this);
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

	private TextField getTF_kundenname() {
		if (tF_kundenname == null) {
			tF_kundenname = new TextField();
			tF_kundenname.setBounds(146, 10, 106, 22);
		}
		return tF_kundenname;
	}

	private TextField getTF_Firmenadresse() {
		if (tF_Firmenadresse == null) {
			tF_Firmenadresse = new TextField();
			tF_Firmenadresse.setBounds(146, 38, 106, 22);
		}
		return tF_Firmenadresse;
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
	
	private Label getLbl_Kundenwahl() {
		if (lbl_Kundenwahl == null) {
			lbl_Kundenwahl = new Label("Kundenwahl");
			lbl_Kundenwahl.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbl_Kundenwahl.setBounds(278, 10, 85, 22);
		}
		return lbl_Kundenwahl;
	}
	private Choice getChoice_Kunde() {
		if (choice_Kunde == null) {
			choice_Kunde = new Choice();
			choice_Kunde.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if(choice_Kunde.getSelectedIndex()>=0)
						kunde = kunden[choice_Kunde.getSelectedIndex()];
				}
			});
			choice_Kunde.setBounds(278, 38, 85, 22);
			choice_Kunde.addItem("Kunde 1");
			choice_Kunde.addItem("Kunde 2");
			choice_Kunde.addItem("Kunde 3");
		}
		return choice_Kunde;
	}
	
	// Methode für den Speicher Button
	public void storeKunde() {
		if( tF_kundenname.getText().length() >2 && tF_Firmenadresse.getText().length() >2) {
			//kunde = new Kunde(tF_kundenname.getText(), tF_Firmenadresse.getText());		
			kunde = new Kunde(tF_kundenname.getText(), tF_Firmenadresse.getText());
			kunden[choice_Kunde.getSelectedIndex()] = kunde;
			tF_kundenname.setText(null);
			tF_Firmenadresse.setText(null);
			lbl_kundenanzeige.setText("Unter " + choice_Kunde.getSelectedItem() + " gespeichert");
		} else 
			lbl_kundenanzeige.setText("Es wird nicht gespeichert: Kundendaten unvollständig");
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btn_speichern) 
			storeKunde();
		else if(e.getSource() == btn_anzeigen){
			textArea.append(kunde.getName() + ", " + kunde.getAdresse() + "\n");
			lbl_kundenanzeige.setText("Kunde angezeigt");
		}
		else if(e.getSource() == btn_loeschen) {
			textArea.setText(null);
			lbl_kundenanzeige.setText("Kundenliste gelöscht");
		}
		else if(e.getSource() == btn_exit)
			System.exit(0);	
	
	}

}
