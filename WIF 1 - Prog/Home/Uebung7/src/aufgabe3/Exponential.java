package aufgabe3;

import javapack.Einlesen;
import tools.Abfrage;

public class Exponential {

	public static void main(String[] args) {
		
		do {
			exponiere();
		} while(Abfrage.weiter());
		System.out.println("*** E N D E ***");
	}
	
	public static void exponiere() {
		double erg, basis = Einlesen.liesDouble("Basis> ");
		int expo = Einlesen.liesInt("Exponent> ");		
			
		if(basis==0) {
			if(expo <0) {
				System.out.println("\nUngültige Eingabe: Null mit negativem Exponenten");
				return;
			} else	erg= 0;
			
		} else {		
			if (expo==0 || basis==1)	erg= 1;
			else {
				erg= basis;
				int zaehler= (expo<0 ? -expo:expo);	// Weil kein Math.abs() erlaubt?	
				while(zaehler >1) {
					erg *= basis;
					zaehler--;
				}
			}
			if (expo <0)	erg = 1/erg;
		}
		
		System.out.format("%f potenziert mit %d ist %f\n", basis, expo, erg);
	}

}
