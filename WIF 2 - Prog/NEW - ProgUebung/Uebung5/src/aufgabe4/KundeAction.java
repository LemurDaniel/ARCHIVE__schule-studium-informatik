package aufgabe4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// externe Klasse für Speichern-Button
public class KundeAction implements ActionListener {

	private KundenverwaltungGUI kundenverwaltungGUI;
	
	public KundeAction(KundenverwaltungGUI kundenverwaltungGUI) {
		this.kundenverwaltungGUI = kundenverwaltungGUI;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		kundenverwaltungGUI.storeKunde();
	}
}
