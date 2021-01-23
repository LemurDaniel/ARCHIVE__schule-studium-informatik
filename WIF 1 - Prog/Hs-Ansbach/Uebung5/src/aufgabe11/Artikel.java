package aufgabe11;

import javapack.Einlesen;

public class Artikel {

	String bezeichnung;
	int menge;
	float preis;
	
	Artikel(int id){
		System.out.println("\nArtikel " + id+ ": ");
		bezeichnung = Einlesen.liesString("Bezeichnung> ");
		menge = Einlesen.liesInt("Menge> ");
		preis = Einlesen.liesFloat("Preis> ");
	}

}
