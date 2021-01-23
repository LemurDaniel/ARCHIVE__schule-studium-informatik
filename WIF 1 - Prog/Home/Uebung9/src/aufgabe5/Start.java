package aufgabe5;

import javapack.Einlesen;

public class Start {

	public static void main(String[] args) {
		Z�hler tages = new Z�hler(0);
		Z�hler gesamt = new Z�hler(0);
		boolean reset = true;
		
		do {
			System.out.println("\nOptions: ");	
			int option = Einlesen.liesInt("Anzeigen(1), reset Tg-Z�hler(2), reset Gesamtz�hler(3), erh�he(4), ende(0)\n");
			switch(option) {
			case 0:
				System.exit(0);
			case 1:
				System.out.format(">> Tagesz�hler: %3d, Gesamtz�hler %3d\n", tages.getWert(), gesamt.getWert());	
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
				tages.erh�he();
				gesamt.erh�he();
				break;
				default:
					System.out.println("Falsche Eingabe!!!");
			}
					
		} while(true);

	}
	
	

}
