package aufgabe5;

import javapack.Einlesen;

public class While {

	public static void main(String[] args) {

		int zaehler = 0, feldlaenge = Einlesen.liesInt("Feldlänge> ");
		float initwert = 0.0f, feld[] = new float[feldlaenge];
		
		System.out.print("while-Schleife: ");
		while(zaehler < feldlaenge) {
			feld[zaehler] = initwert;
			initwert += 0.5;
			//System.out.println(zaehler+1 + ".Element ---> " + feld[zaehler]);
			System.out.print(feld[zaehler] + ", ");
			zaehler++;
		}

	}

}
