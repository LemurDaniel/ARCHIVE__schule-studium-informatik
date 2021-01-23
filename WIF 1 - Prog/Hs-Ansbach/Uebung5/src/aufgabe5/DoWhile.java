package aufgabe5;

import javapack.Einlesen;

public class DoWhile {

	public static void main(String[] args) {

		int zaehler = 0, feldlaenge = Einlesen.liesInt("Feldlänge> ");
		float initwert = 0.0f, feld[] = new float[feldlaenge];
		
		if(feld.length>0) {
			System.out.print("do...while-Schleife: ");
			do {
				feld[zaehler] = initwert;
				initwert += 0.5;	
				System.out.print( (zaehler==0 ? "":"; ") + feld[zaehler]);
				zaehler++;
			} while(zaehler < feldlaenge);
		}

	}

}
