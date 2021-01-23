package tools;

import javapack.Einlesen;

public class Abfrage {
	
	public static boolean weiter() {
		char abfrage;
		
		while(true) {
			abfrage = Einlesen.liesChar("\nWeiter/Ende(j/e)> ");
			System.out.println();
			if(abfrage == 'j') return true; 
			else if(abfrage == 'e')	return false;
			else System.out.println("\nFalsche Eingabe");
		}
	}
}
