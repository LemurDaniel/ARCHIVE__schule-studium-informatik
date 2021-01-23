package aufgabe4;

import javapack.Einlesen;

public class Brutto {

	public static void main(String[] args) {
		
		System.out.print("Menge: ");
		
		int menge = Einlesen.liesInt(); 
		//System.out.println(menge);
		
		System.out.print("Nettopreis: "); 
		double nettoPreis = Einlesen.liesDouble(); 
		
		System.out.print("MWST: "); 
		double mwst = Einlesen.liesDouble();
		
		double wareNetto = nettoPreis*menge; 
		System.out.println(" Warenpreis Brutto: " + wareNetto*(mwst + 100)/100); 
		
	}

}
