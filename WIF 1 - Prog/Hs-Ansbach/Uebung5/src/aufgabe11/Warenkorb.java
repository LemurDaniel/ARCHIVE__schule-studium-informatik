package aufgabe11;

import javapack.Einlesen;

public class Warenkorb {
	
	public static void main(String[] args) {
	
		float gesamtPreis = 0f;
		float brieftasche = Einlesen.liesFloat("Inhalt der Brieftasche> ");
		Artikel[] waren = new Artikel[Einlesen.liesInt("Warenanzahl> ")];
	
		for(int i = 0; i < waren.length; i++) {
			waren[i] = new Artikel(i+1);
			gesamtPreis += waren[i].menge*waren[i].preis;
		}
	
		if (gesamtPreis > brieftasche) {
			System.out.format("Für diesen Einkauf fehlen noch %.2f €\n", gesamtPreis-brieftasche);
			return;
		}
		
		System.out.println("\n-----------------------------------------");
		System.out.println("		Kassenbon");
		System.out.println("-----------------------------------------");
		for(Artikel a:waren) {
			System.out.format("	%s: %d * %.2f ---> %.2f\n", a.bezeichnung, a.menge, a.preis, a.preis*a.menge);
		}
		System.out.println("-----------------------------------------");
		System.out.format("Gesamt: %.2f €\n", gesamtPreis);
		System.out.format("Gegeben: %.2f €\n", brieftasche);
		System.out.format("Rückgeld: %.2f €\n", brieftasche-gesamtPreis);
		System.out.println("\n      Vielen Dank für ihren Einkauf");
		System.out.println("-----------------------------------------");
	}
}
