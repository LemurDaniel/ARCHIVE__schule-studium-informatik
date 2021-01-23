package aufgabe5;

import javapack.Einlesen;

public class Start {

	public static void main(String[] args) {
		Zähler tages = new Zähler(0);
		Zähler gesamt = new Zähler(0);
		boolean reset = true;
		
		do {
			System.out.println("\nOptions: ");	
			int option = Einlesen.liesInt("Anzeigen(1), reset Tg-Zähler(2), reset Gesamtzähler(3), erhöhe(4), ende(0)\n");
			switch(option) {
			case 0:
				System.exit(0);
			case 1:
				System.out.format(">> Tageszähler: %3d, Gesamtzähler %3d\n", tages.getWert(), gesamt.getWert());	
				break;
			case 2:
				tages.setzeAufNull();	
				break;
			case 3:
				if(reset) {
					gesamt.setzeAufNull();
					reset = false;
				}	
				break;
			case 4:
				tages.erhöhe();
				gesamt.erhöhe();
				break;
				default:
					System.out.println("Falsche Eingabe!!!");
			}
					
		} while(true);

	}
	
	

}
