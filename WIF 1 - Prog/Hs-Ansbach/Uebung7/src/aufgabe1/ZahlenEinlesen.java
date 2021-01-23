package aufgabe1;

import javapack.Einlesen;
import tools.Abfrage;

public class ZahlenEinlesen {

	public static void main(String[] args) {
		do {
			zahlEinlesen();
		} while(Abfrage.weiter());
		System.out.println("*** E N D E ***");
	}
	
	public static void zahlEinlesen() {
		short i = Einlesen.liesShort("Ziffer> ");
		
		String s="Die Zahl "+i+" als Wort: ";		
		switch(i) {
		case 0: s+="Null"; break;
		case 1: s+="Eins"; break;
		case 2: s+="Zwei"; break;
		case 3: s+="Drei"; break;
		case 4: s+="Vier"; break;
		case 5: s+="Fünf"; break;
		case 6: s+="Sechs"; break;
		case 7: s+="Sieben"; break;
		case 8: s+="Acht"; break;
		case 9: s+="Neun"; break;
		default: s="ungültige Eingabe: "+i; 
		}
		System.out.println(s);
	}

}
