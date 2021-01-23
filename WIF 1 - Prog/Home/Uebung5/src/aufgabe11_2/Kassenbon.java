package aufgabe11_2;

import javapack.Einlesen;

public class Kassenbon {

	public static void main(String[] args) {
		
		float gesamtPreis=0f;
		float brieftasche = Einlesen.liesFloat("Brieftasche> ");
		int artikelanzahl = Einlesen.liesInt("Artikelanzahl> ");
		String bezeichnung[] = new String[artikelanzahl];	
		int menge[] = new int[artikelanzahl];
		float preis[] = new float[artikelanzahl];	
		
		for(int i=0; i<artikelanzahl; i++) {
			System.out.println("Artikel "+(i+1)+":");
			bezeichnung[i] = Einlesen.liesString("Bezeichnung> ");
			menge[i] = Einlesen.liesInt("Menge> ");
			preis[i] = Einlesen.liesFloat("Preis> ");
			gesamtPreis += preis[i]*menge[i];
		}
		
		if(gesamtPreis>brieftasche) {
			System.out.format("Für diesen Einkauf fehlen noch %.2f €\n", gesamtPreis-brieftasche);
			return;
		}
		System.out.println("\n-----------------------------------------");
		System.out.println("		Kassenbon");
		System.out.println("-----------------------------------------");
		for(int i=0; i<artikelanzahl; i++) {
			System.out.format("	%s: %d * %.2f ---> %.2f\n", bezeichnung[i], menge[i], preis[i], preis[i]*menge[i]);
		}
		System.out.println("-----------------------------------------");
		System.out.format("Gesamt: %.2f €\n", gesamtPreis);
		System.out.format("Gegeben: %.2f €\n", brieftasche);
		System.out.format("Rückgeld: %.2f €\n", brieftasche-gesamtPreis);
		System.out.println("\n      Vielen Dank für ihren Einkauf");
		System.out.println("-----------------------------------------");
	}
}


