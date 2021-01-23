package aufgabe3;

import javapack.Einlesen;

public class CD extends MedienKunstwerk {

	private String interpret;
	private int anzahlTracks;
	private String musikRichtung;
	
	CD(){		
	}
	
	CD(String titel, int katalogNummer, double preis, int lauflaenge, String interpret, int anzahlTracks, String musikRichtung){
		super(titel, katalogNummer, preis, lauflaenge);
		this.interpret = interpret;
		this.anzahlTracks = anzahlTracks;
		this.musikRichtung = musikRichtung;
	}
	
	@Override
	public void leseDaten() {
		System.out.println("CD");
		super.leseDaten();
		interpret = Einlesen.liesString("Interpret> ");
		anzahlTracks = Einlesen.liesInt("Anzahl der Tracks> ");
		musikRichtung = Einlesen.liesString("musikRichtung> ");
	}
	
	
	//get
	public String getInterpret() {
		return interpret;
	}
	public int getAnzahlTracks() {
		return anzahlTracks;
	}
	public String getMusikRichtung() {
		return musikRichtung;
	}
	
	
}
