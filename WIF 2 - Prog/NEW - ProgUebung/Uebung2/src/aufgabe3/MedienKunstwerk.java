package aufgabe3;

import Bla.Einlesen;

public class MedienKunstwerk {

	private String titel;
	private int katalogNummer;
	private double preis;
	private int lauflaenge;
	
	MedienKunstwerk(){	
	}
	
	MedienKunstwerk(String titel, int katalogNummer, double preis, int lauflaenge){
		this.titel = titel;
		this.katalogNummer = katalogNummer;
		this.preis = preis;
		this.lauflaenge = lauflaenge;
	}
	
	public void leseDaten() {
		System.out.println("Dateneingabe: ");
		titel = Einlesen.liesString("Titel> ");
		katalogNummer = Einlesen.liesInt("Katalognummer> ");
		preis = Einlesen.liesDouble("Preis> ");
		lauflaenge = Einlesen.liesInt("Lauflänge> ");
	}
	
	// get
	public String getTitel() {
		return titel;
	}
	public int getKatalognummer() {
		return katalogNummer;
	}
	public double getPreis() {
		return preis;
	}
	public int lauflaenge() {
		return lauflaenge;
	}
}
