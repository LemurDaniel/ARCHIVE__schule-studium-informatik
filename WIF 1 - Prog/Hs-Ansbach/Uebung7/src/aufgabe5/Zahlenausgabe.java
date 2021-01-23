package aufgabe5;

import javapack.Einlesen;
import tools.Abfrage;

public class Zahlenausgabe {

	public static void main(String[] args) {
		int zahl;
		
		do {
			zahl = Einlesen.liesInt("Zahl> ");
			
			String zString = String.valueOf(zahl);
			System.out.println(zString);
		
			for(int i=0; i<zString.length(); i++) {
				System.out.print( zahlZuText(zString.charAt(i)) + " " );
			}
			System.out.println();
			
		} while(Abfrage.weiter());
		System.out.println("*** E N D E ***");

	}
	
	public static String zahlZuText(char c) {
		switch(c) {
		case '0':	return "Null";
		case '1':	return "Eins";
		case '2':	return "Zwei";
		case '3':	return "Drei";
		case '4':	return "Vier";
		case '5':	return "Fünf"; 
		case '6':	return "Sechs";
		case '7':	return "Sieben";
		case '8':	return "Acht";
		case '9':	return "Neun";
			default: return "FALSCH"; 
		}

	}

}
