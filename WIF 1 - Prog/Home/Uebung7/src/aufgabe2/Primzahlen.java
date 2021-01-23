package aufgabe2;

import javapack.Einlesen;
import tools.Abfrage;

public class Primzahlen {

	public static void main(String[] args) {
		
		do {
			primzahlen();						
		} while(Abfrage.weiter());
		System.out.println("*** E N D E ***");		
	}

	public static void primzahlen() {
		String ergebnis;
		int n, zahl=5, teiler;
		
		n = Einlesen.liesInt("N> ");
		System.out.println("Bis "+n+" sind folgende Primzahlen vorhanden: ");
		
		if(n<=1) 		ergebnis="n muss >1 sein";
		else if(n<3)	ergebnis="2";
		else if(n<=4)	ergebnis="2, 3";
		else {
			ergebnis="2, 3";
			while(zahl <= n) {
				teiler = 1;
				do {
					teiler +=2;
					if(zahl%teiler == 0)	break;
					else if(teiler*teiler >= zahl)	ergebnis +=", "+zahl;
				} while(teiler*teiler < zahl);
				zahl +=2;
			}
		}
		System.out.println(ergebnis);
	}
}
