package aufgabe10;

import javapack.Einlesen;

public class Durchschnitt {

	public static void main(String[] args) {
			
		int feldlaenge, min, max, summe = 0, feld[];
		float durchschnitt;
		
		feldlaenge = Einlesen.liesInt("Feldlänge> ");
		min = Einlesen.liesInt("Untergrenze> ");
		max = Einlesen.liesInt("Obergrenze> ");
		
		feld = new int[feldlaenge];
		
		System.out.print("\nZufallszahlen Feld: ");
		for(int i = 0; i < feld.length; i++) {
			feld[i] = (int) (Math.random()*(max-min+1)+min);
			summe += feld[i];
			System.out.print( feld[i] + (i==feld.length-1 ? "\n":", ") );
		}
			durchschnitt = (float)summe/feld.length;
			System.out.println("Summe: " + summe);
			System.out.format("Durchschnitt: %f -> %d/%d\n",durchschnitt, summe, feld.length);
		
	}

}
