package aufgabe9.test;

import javax.swing.JFrame;

import aufgabe7.Kunde;

import java.awt.TextArea;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;

public class KundenverwaltungGUI  extends SicherBeenden implements ActionListener{
	//Nachbau in SWING
	private TextArea textArea; // Umwandlung in JTextArea mit JScrollBar
	private Button btn_anzeigen;
	private Button btn_speichern;
	private Button btn_loeschen;
	private Button btn_exit;
	private Label Lb_Kundenname;
	private Label lb_Firmenadresse;
	private TextField textField_Kundenname;
	private TextField textField_Firmenadresse;
	private Label lb_Kundenliste;
	private Label lb_info;
	private Kunde kunde = new Kunde(null, null);
	private Kunde[] kunden = {new Kunde(), new Kunde(), new Kunde()};
	private Label lb_Kundewahl;
	private Choice choiceKunde;
	
	public KundenverwaltungGUI() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Kundenverwaltung");
		getContentPane().setLayout(null);
		getContentPane().add(getTextArea());
		getContentPane().add(getBtn_anzeigen());
		getContentPane().add(getBtn_speichern());
		getContentPane().add(getBtn_loeschen());
		getContentPane().add(getBtn_exit());
		getContentPane().add(getLb_Kundenname());
		getContentPane().add(getLb_Firmenadresse());
		getContentPane().add(getTextField_Kundenname());
		getContentPane().add(getTextField_Firmenadresse());
		getContentPane().add(getLb_Kundenliste());
		getContentPane().add(getLb_info());
		getContentPane().add(getLb_Kundewahl());
		getContentPane().add(getChoiceKunde());
		kunde = kunden[0];
	}

	public static void main(String[] args) {
		KundenverwaltungGUI kvg = new KundenverwaltungGUI();
		kvg.setBounds(100, 100, 500, 300);
		kvg.setVisible(true);

	}
	private TextArea getTextArea() {
		if (textArea == null) {
			textArea = new TextArea();
			textArea.setBounds(147, 107, 265, 130);
		}
		return textArea;
	}
	private Button getBtn_anzeigen() {
		if (btn_anzeigen == null) {
			btn_anzeigen = new Button("anzeigen");
			btn_anzeigen.setBounds(25, 136, 89, 22);
			//GUI Klasse selbst
			//btn_anzeigen.addActionListener(this);
			//Alternativ mit Methoden-Referenz
			btn_anzeigen.addActionListener(this::actionPerformed);
		}
		return btn_anzeigen;
	}
	private Button getBtn_speichern() {
		if (btn_speichern == null) {
			btn_speichern = new Button("speichern");
			btn_speichern.setBounds(25, 108, 89, 22);
			//externe Klasse/äußerer Adapter
			//KundeAction ac = new KundeAction(this);
			//btn_speichern.addActionListener(ac);
			btn_speichern.addActionListener(this);
		}
		return btn_speichern;
	}
	private Button getBtn_loeschen() {
		if (btn_loeschen == null) {
			btn_loeschen = new Button("l\u00F6schen");
			btn_loeschen.setBounds(25, 164, 89, 22);
			//innere Klasse
			//KundeLoeschen kl = new KundeLoeschen();
			//btn_loeschen.addActionListener(kl);
			btn_loeschen.addActionListener(this);
		}
		return btn_loeschen;
	}
	private Button getBtn_exit() {
		if (btn_exit == null) {
			btn_exit = new Button("exit");
			//Anonymer innerer Adapter
//			btn_exit.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					System.exit(0);
//				}
//			});
			btn_exit.addActionListener(this);
			btn_exit.setBounds(25, 192, 89, 22);
		}
		return btn_exit;
	}
	private Label getLb_Kundenname() {
		if (Lb_Kundenname == null) {
			Lb_Kundenname = new Label("Kundenname:");
			Lb_Kundenname.setBounds(25, 10, 89, 22);
		}
		return Lb_Kundenname;
	}
	private Label getLb_Firmenadresse() {
		if (lb_Firmenadresse == null) {
			lb_Firmenadresse = new Label("Firmenadresse:");
			lb_Firmenadresse.setBounds(25, 38, 89, 22);
		}
		return lb_Firmenadresse;
	}
	private TextField getTextField_Kundenname() {
		if (textField_Kundenname == null) {
			textField_Kundenname = new TextField();
			textField_Kundenname.setBounds(147, 10, 114, 22);
			textField_Kundenname.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent ke) {
					if(ke.getKeyCode() == 10) textField_Firmenadresse.requestFocus();
				}
			});
		}
		return textField_Kundenname;
	}
	private TextField getTextField_Firmenadresse() {
		if (textField_Firmenadresse == null) {
			textField_Firmenadresse = new TextField();
			textField_Firmenadresse.setBounds(147, 38, 114, 22);
			textField_Firmenadresse.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent ke) {
					if(ke.getKeyCode() == KeyEvent.VK_ENTER) textField_Kundenname.requestFocus();
				}
			});
		}
		return textField_Firmenadresse;
	}
	private Label getLb_Kundenliste() {
		if (lb_Kundenliste == null) {
			lb_Kundenliste = new Label("Kundenliste");
			lb_Kundenliste.setFont(new Font("Dialog", Font.BOLD, 13));
			lb_Kundenliste.setBounds(147, 83, 89, 22);
		}
		return lb_Kundenliste;
	}
	private Label getLb_info() {
		if (lb_info == null) {
			lb_info = new Label("");
			lb_info.setForeground(Color.RED);
			lb_info.setBounds(25, 66, 387, 22);
		}
		return lb_info;
	}
	
	//Methode für den Speichern Button
	public void storeKunde() {
		if(textField_Kundenname.getText().length() > 2 &&
				textField_Firmenadresse.getText().length()>2) {
			
			kunden[choiceKunde.getSelectedIndex()] = kunde 
				= new Kunde(textField_Kundenname.getText(), 
							textField_Firmenadresse.getText());
			
			//kunde = new Kunde(textField_Kundenname.getText(), 
			//		textField_Firmenadresse.getText());
			
			textField_Kundenname.setText(null);
			textField_Firmenadresse.setText("");
			lb_info.setText(null);
			lb_info.setText("unter " + choiceKunde.getSelectedItem() 
			+ " gespeichert");
		}else
			lb_info.setText("Es wir nicht gespeichert: "
					+ "Kundendaten unvollständig");
	}

	
	//Methode für Aufgabe 5
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_loeschen) {
			textArea.setText(null);
			lb_info.setText("Kundenliste gelöscht");
		}
		else if(e.getSource() == btn_speichern ) storeKunde();
		else if(e.getSource() == btn_anzeigen ) {
			textArea.append(kunde.getName() + ", " + 
			kunde.getAdresse() + '\n');
			lb_info.setText("Kunde angezeigt");
		}
		else if(e.getSource() == btn_exit) {
			//System.exit(0);
			//der Button muss nun explizit den Window-Closing event auslösen
			this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
			
			//Ist die Komponente bekannt, der ein Ereignis geschickt werden soll, lässt sich 
			//die Component-Methode dispatchEvent(AWTEvent e) verwenden. Sie sendet ein AWTEvent 
			//– die Basisklasse aller AWT-Ereignisse – an die Komponente, womit alle Listener aufgerufen werden.
		}
			
			
		
		
		
		
	}
	
	//Button Löschen // innerer Klasse
	private class KundeLoeschen implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			textArea.setText(null);
			lb_info.setText("Kundenliste gelöscht");
			
		}
		
	}
	private Label getLb_Kundewahl() {
		if (lb_Kundewahl == null) {
			lb_Kundewahl = new Label("Kundenwahl:");
			lb_Kundewahl.setBounds(301, 10, 89, 22);
		}
		return lb_Kundewahl;
	}
	private Choice getChoiceKunde() {
		if (choiceKunde == null) {
			choiceKunde = new Choice();
			choiceKunde.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if(choiceKunde.getSelectedIndex()>=0)
						kunde = kunden[choiceKunde.getSelectedIndex()];
				}
			});
			choiceKunde.setBounds(301, 38, 89, 20);
			choiceKunde.addItem("Kunde 1");
			choiceKunde.addItem("Kunde 2");
			choiceKunde.addItem("Kunde 3");
			
		}
		return choiceKunde;
	}
}
