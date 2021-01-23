package aufgabe10;

import javapack.Einlesen;

public class Grillfest {

	public static void main(String[] args) {

		boolean satt, steak, vorspeise, nachspeise;
		int kartoffeln, getraenke;
		
		vorspeise = Einlesen.liesBoolean("Vorspeise> ");
		steak = Einlesen.liesBoolean("Steak> ");
		nachspeise = Einlesen.liesBoolean("Nachspeise> ");
		kartoffeln = Einlesen.liesInt("Kartoffeln> ");
		getraenke = Einlesen.liesInt("Getränke> ");
		
		satt = (steak || nachspeise) && vorspeise && getraenke >= 3 && kartoffeln >= 2;
		System.out.println( satt ? "Ich bin randvoll!!" : "Hungrig... *Magenknurr*");
		

	}

}
