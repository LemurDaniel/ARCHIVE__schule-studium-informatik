package aufgabe6;

import javapack.Einlesen;

public class WerteVergleiche {

	public static void main(String[] args) {
		
		int feldlaenge = Einlesen.liesInt("Feldlänge> ");
		int[] f1 = new int[feldlaenge], f2 = new int[feldlaenge];

		for(int i = 0; i < f1.length; i++) {
			f1[i] = Einlesen.liesInt("Feld1["+ i + "]> ");
			f2[i] = Einlesen.liesInt("Feld2[" + i + "]> ");
		}
		
		for(int i = 0; i < f1.length; i++) {
			String zeichen;
			if (f1[i] < f2[i])		zeichen = " < ";
			else if (f1[i] > f2[i])	zeichen = " > ";	
			else					zeichen = " = ";
			System.out.println(f1[i] + zeichen + f2[i]);
				
		}
	}

}
