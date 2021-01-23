package aufgabe9;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;


import aufgabe6.Kunde;

public class KundenVerwaltungGUI extends SicherBeenden {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	KundenVerwaltungGUI test = this;
	
	private JLabel lblKundenname;
	private JLabel lblFirmenadresse;
	private JLabel lblKundenliste;
	private JLabel lblKundenanzeige;
	private JLabel lblKundenwahl;
	
	private JTextField tfKundenname;
	private JTextField tfFrimenadresse;
	
	private JButton btnSpeichern;
	private JButton btnAnzeigen;
	private JButton btnLoeschen;
	private JButton btnExit;
	private JTextArea textArea;
	private JComboBox<String> comboBox;
	
	private Kunde kunden[] = new Kunde[4];
	private Timer timer;
	
	public static void  main(String[] args) {
		KundenVerwaltungGUI k = new KundenVerwaltungGUI();
		k.setVisible(true);
	}
	
	
	public KundenVerwaltungGUI() {
		
		getContentPane().setLayout(null);
		getContentPane().add(getLblKundenname());
		getContentPane().add(getLblFirmenadresse());
		getContentPane().add(getLblkundenliste());
		getContentPane().add(getLblKundenanzeige());
		getContentPane().add(getTfKundenname());
		getContentPane().add(getTfFrimenadresse());
		getContentPane().add(getBtn_speichern());
		getContentPane().add(getBtn_anzeigen());
		getContentPane().add(getBtn_loeschen());
		getContentPane().add(getBtn_exit());
		getContentPane().add(getTextArea());
		getContentPane().add(getLblKundenwahl());
		getContentPane().add(getComboBox());
		
		//Set<AWTKeyStroke> set = new HashSet<AWTKeyStroke>(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
		//set.add(KeyStroke.getKeyStroke("ENTER" ));
		//this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, set);
		
		
		for(int i=0; i<kunden.length; i++) {
			if(kunden[i] == null)
				kunden[i] = new Kunde();
		}
		
		setBounds(300, 300, 475, 325);

	}
	private JLabel getLblKundenname() {
		if (lblKundenname == null) {
			lblKundenname = new JLabel("Kundenname:");
			lblKundenname.setFont(new Font("Dialog", Font.PLAIN, 12));
			lblKundenname.setBounds(23, 10, 85, 22);
		}
		return lblKundenname;
	}
	private JLabel getLblFirmenadresse() {
		if (lblFirmenadresse == null) {
			lblFirmenadresse = new JLabel("Firmenadresse:");
			lblFirmenadresse.setFont(new Font("Dialog", Font.PLAIN, 12));
			lblFirmenadresse.setBounds(23, 38, 95, 22);
		}
		return lblFirmenadresse;
	}
	private JLabel getLblkundenliste() {
		if (lblKundenliste == null) {
			lblKundenliste = new JLabel("Kundenliste");
			lblKundenliste.setFont(new Font("Dialog", Font.BOLD, 12));
			lblKundenliste.setBounds(128, 92, 81, 22);
		}
		return lblKundenliste;
	}
	private JLabel getLblKundenanzeige() {
		if (lblKundenanzeige == null) {
			lblKundenanzeige = new JLabel();
			lblKundenanzeige.setFont(new Font("Dialog", Font.BOLD, 12));
			lblKundenanzeige.setForeground(Color.RED);
			lblKundenanzeige.setBounds(23, 66, 364, 22);
			lblKundenanzeige.addPropertyChangeListener( evt -> {
				
				if (evt.getPropertyName() != "text" || lblKundenanzeige.getText() == null)
					return;
							
					timer = new Timer(2500, e -> {
						lblKundenanzeige.setText(null);
						timer.stop();
					});
					timer.start();
			});
		}
		return lblKundenanzeige;
	}
	private JLabel getLblKundenwahl() {
		if (lblKundenwahl == null) {
			lblKundenwahl = new JLabel("Kundenwahl");
			lblKundenwahl.setFont(new Font("Dialog", Font.PLAIN, 12));
			lblKundenwahl.setBounds(278, 10, 85, 22);
		}
		return lblKundenwahl;
	}
	
	private JTextField getTfKundenname() {
		if (tfKundenname == null) {
			tfKundenname = new JTextField();
			tfKundenname.setBounds(146, 10, 106, 22);
			tfKundenname.setColumns(10);
		}
		return tfKundenname;
	}
	private JTextField getTfFrimenadresse() {
		if (tfFrimenadresse == null) {
			tfFrimenadresse = new JTextField();
			tfFrimenadresse.setColumns(10);
			tfFrimenadresse.setBounds(146, 38, 106, 22);
		}
		return tfFrimenadresse;
	}
	
	private JButton getBtn_speichern() {
		if (btnSpeichern == null) {
			btnSpeichern = new JButton("speichern");
			btnSpeichern.setFont(new Font("Dialog", Font.PLAIN, 11));
			btnSpeichern.setBounds(23, 115, 90, 22);
			btnSpeichern.addActionListener( this::saveKunde );
		}
		return btnSpeichern;
	}	
	private JButton getBtn_anzeigen() {
		if (btnAnzeigen == null) {
			btnAnzeigen = new JButton("anzeigen");
			btnAnzeigen.setFont(new Font("Dialog", Font.PLAIN, 11));
			btnAnzeigen.setBounds(23, 143, 90, 22);
			btnAnzeigen.addActionListener( e -> textArea.append( kunden[comboBox.getSelectedIndex()].getName() + ", " + kunden[comboBox.getSelectedIndex()].getAdresse()+"\n" )  );
		}
		return btnAnzeigen;
	}
	private JButton getBtn_loeschen() {
		if (btnLoeschen == null) {
			btnLoeschen = new JButton("l\u00F6schen");
			btnLoeschen.setFont(new Font("Dialog", Font.PLAIN, 11));
			btnLoeschen.setBounds(23, 171, 90, 22);
			btnLoeschen.addActionListener( e -> textArea.setText(null) );
		}
		return btnLoeschen;
	}
	private JButton getBtn_exit() {
		if (btnExit == null) {
			btnExit = new JButton("exit");
			btnExit.setFont(new Font("Dialog", Font.PLAIN, 11));
			btnExit.setBounds(23, 199, 90, 22);
			btnExit.addActionListener( e -> System.exit(0) );
		}
		return btnExit;
	}
	
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setBackground(Color.WHITE);
			textArea.setBounds(128, 110, 304, 160);
		}
		return textArea;
	}
	
	private JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.setBounds(278, 38, 85, 22);
			for(int i=0; i<kunden.length; i++) {
				comboBox.addItem("Kunde " + (i+1));
			}
		}
		return comboBox;
	}
	
	private void saveKunde(ActionEvent e) {
		if ( tfKundenname.getText().length() <= 2 || tfFrimenadresse.getText().length() <= 2)
			lblKundenanzeige.setText("Ungültige Eingabe");
		else if (comboBox.getSelectedIndex() < -1 || comboBox.getSelectedIndex() > kunden.length-1)
			lblKundenanzeige.setText("Es ist ein Fehler aufgetreten");
		else {
			lblKundenanzeige.setText("Erfolgreich in " + comboBox.getSelectedItem() + " gespeichert");
			kunden[comboBox.getSelectedIndex()].setName(tfKundenname.getText());
			kunden[comboBox.getSelectedIndex()].setAdresse(tfFrimenadresse.getText());
		}
	}
	
}
