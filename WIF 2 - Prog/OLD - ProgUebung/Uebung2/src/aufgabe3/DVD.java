package aufgabe3;

import Bla.Einlesen;

public class DVD extends MedienKunstwerk {
	
	private String hauptdarsteller;
	private String genre;
	private String format;
	
	DVD(){
	}
	
	DVD(String titel, int katalogNummer, double preis, int lauflaenge, String hauptdarsteller, String genre, String format){
		super(titel, katalogNummer, preis, lauflaenge);
		this.hauptdarsteller = hauptdarsteller;
		this.genre = genre;
		this.format = format;
	}
	
	@Override
	public void leseDaten() {
		System.out.println("DVD");
		super.leseDaten();
		hauptdarsteller = Einlesen.liesString("hauptdarsteller> ");
		genre = Einlesen.liesString("genre> ");
		format = Einlesen.liesString("format> ");
	}
	
	//get
	public String getHauptdarsteller() {
		return hauptdarsteller;
	}
	public String getGenre() {
		return genre;
	}
	public String getFormat() {
		return format;
	}
}
