package aufgabe6;

import javapack.Einlesen;
import tools.Abfrage;

public class Zahlenausgabe {

	public static void main(String[] args) {
		String zahl;
		byte z;
		
		do {
			zahl = Einlesen.liesString("Zahl> ");
			z = TextZuZahl(zahl);
			
			if(z==-1) System.out.println("Fehler: Ungültige Eingabe '"+zahl+"'");
			else	System.out.println(z);
			
		} while(Abfrage.weiter());
		System.out.println("*** E N D E ***");
	}
	
	public static byte TextZuZahl(String s) {
		s.toLowerCase();
		
		switch(s) {
		case "null":	return 0;
		case "eins":	return 1;
		case "zwei":	return 2;
		case "drei":	return 3;
		case "vier":	return 4;
		case "fuenf":	return 5; 
		case "sechs":	return 6;
		case "sieben":	return 7;
		case "acht":	return 8;
		case "neun":	return 9;
			default: return -1; 
		}

	}
}
