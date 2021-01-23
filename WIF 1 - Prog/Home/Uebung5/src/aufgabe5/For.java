package aufgabe5;

import javapack.Einlesen;

public class For {

	public static void main(String[] args) {

		int feldlaenge = Einlesen.liesInt("Feldlänge> ");
		float initwert = 0.0f, feld[] = new float[feldlaenge];
		
		System.out.print("for-schleife: ");
		for(int i = 0; i < feld.length; i++) {
			feld[i] = initwert;
			initwert += 0.5;
			//System.out.println(i+1 + ".Element ---> " + feld[i]);
			System.out.print(feld[i] + ", ");
		}
	}

}
