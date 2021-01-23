package aufgabe6;

import javapack.Einlesen;

public class Start {

	public static void main(String[] args) {
		Zähler pegel1 = new Zähler(0);
		Zähler pegel2 = new Zähler(0);
		
		do {
			System.out.println("\nOptionen: ");
			int eingabe = Einlesen.liesInt("Anzeigen(1), äendere(2), ende(0)\n --> ");
			
			switch(eingabe) {
			case 0:
				System.exit(0);
			case 1:
				System.out.format(">> Pegel 1: %3d, Pegel 2: %3d\n", pegel1.getWert(), pegel2.getWert() );
				break;
			case 2:
				int pegel = eingabe("Pegel? (1, 2)> ", 1, 2);
				int delta = eingabe("Delta? (-1, 1, -5, 5)> ", -1, 1, -5, 5);
				if(pegel == 1)
					pegel1.aendere(delta);
				else
					pegel2.aendere(delta);
				break;
				default:
					System.out.println("Falsche Eingabe!!!");
			}
		} while(true);

	}
	
	public static int eingabe(String text, int... auswahl) {
		if(auswahl.length < 1)
			return 0;
		
		int eingabe;
		do {
			eingabe = Einlesen.liesInt(text);
			for(int i:auswahl) {
				if(eingabe == i)
					return i;
			}
			System.out.println("Falsche Eingabe!!");
		} while(true);
	}

	}
