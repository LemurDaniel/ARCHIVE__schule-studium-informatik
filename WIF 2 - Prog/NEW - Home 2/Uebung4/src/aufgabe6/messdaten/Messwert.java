package aufgabe6.messdaten;

import java.time.LocalDate;

class Messwert {

	private static int anzahlMesswerte = 0;
	
	
	private double wert;
	private LocalDate messDatum;
	private int messwertID;
	
	
	Messwert(double messwert, LocalDate messDatum){
		this.messDatum = messDatum;
		wert = messwert;
		messwertID = ++anzahlMesswerte;
	}
	
	
	double getWert() {
		return wert;
	}
	
	LocalDate getMessDatum() {
		return messDatum;
	}
	
	int getMesswertID() {
		return messwertID;
	}
	
}
