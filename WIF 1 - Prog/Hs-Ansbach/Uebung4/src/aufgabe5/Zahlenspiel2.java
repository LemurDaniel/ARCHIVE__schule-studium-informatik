package aufgabe5;

import javapack.Einlesen;

public class Zahlenspiel2 {

	public static void main(String[] args) {
		
		int quersumme = 0, zahl = Einlesen.liesInt("Ganzzahl> ");
		int[] zahlen = new int[String.valueOf(zahl).length()];
		
		System.out.print("Ziffern: ");
		for(int i = 0; i < zahlen.length; i++) {
			zahlen[i] = zahl/(int)Math.pow(10, zahlen.length-(i+1)) % 10;
			System.out.print( zahlen[i] + (i==zahlen.length-1 ? "\n":"; ") );
		}
		
		System.out.print("Umgedrehte Zahl: ");
		for(int i = zahlen.length-1; i >= 0; i--) {
			System.out.print(zahlen[i]);
			quersumme += zahlen[i];
		}
		System.out.println("\nQuersumme: " + quersumme);
		

	}

}
