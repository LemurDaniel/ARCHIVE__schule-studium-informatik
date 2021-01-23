package aufgabe11;

import javapack.Einlesen;

public class Kasse {

	public static void main(String[] args) {
				
		float brieftaschenInhalt = Einlesen.liesFloat("Inhalt der Brieftasche> ");
		int Warenanzahl = Einlesen.liesInt("Anzahl der Waren> ");
		
		Warenkorb warenkorb1 = new Warenkorb(Warenanzahl);
		warenkorb1.gibKassenBon(brieftaschenInhalt);

	}

}

