package aufgabe6;

import javapack.Einlesen;
import java.util.ArrayList;

public class Start2 {
	
	static ArrayList<Z�hler> pegel = new ArrayList<Z�hler>();
	
	public static void main(String[] args) {
		
		do {
			System.out.println(">> Optionen");
			int eingabe = Einlesen.liesInt(">> Anzeigen(1), �ndern(2), Reset(3), Setze(4), Hinzuf�gen(5), L�schen(6), ende(0)\n --> ");
			System.out.println();
			
			switch(eingabe) {
			case 0:
				System.exit(0);
			case 1:
				ausgeben();
				break;
			case 2:
				aktion("�ndere");
				break;
			case 3:
				aktion("reset");
				break;
			case 4:
				aktion("setze");
				break;
			case 5:
				hinzuf�gen();
				break;
			case 6:
				aktion("l�sche");
				break;
				default:
					System.out.println("\n >>> Falsche Eingabe !!! <<< \n");
			}
			Einlesen.liesString("");
		}while(true);
	}
	
	public static void aktion(String akt) {
		ausgeben();
		if(pegel.isEmpty())
			return;
		
		String ausgabe;
		switch(akt) {
		case "reset":
			ausgabe = ">> Welcher Pegel soll zur�ckgesetzt werden? ";
			break;
		case "l�sche":
			ausgabe = ">> Welcher Pegel soll gel�scht werden? ";
			break;
		case "�ndere":
			ausgabe = ">> Welcher Pegel soll ge�ndert werden? ";
			break;
		case "setze":
			ausgabe = ">> Welcher Pegel soll gesetzt werden? ";
			break;
			default:
				return;
		}
		
		int[] auswahl = new int[pegel.size()];
		for(int i=0; i<pegel.size();) {
			auswahl[i] = ++i;
		}
		int p = eingabe(ausgabe, true, auswahl);
		if( p==-1 )
			return;
		p--;
		
		switch(akt) {	
		case "reset":
			pegel.get(p).setzeAufNull();
			System.out.println(">> Pegel "+(p+1)+" wurde erfolgreich zur�ckgesetzt");
			break;
			
		case "l�sche":
			pegel.remove(p);
			System.out.println(">> Pegel "+(p+1)+" wurde erfolgreich zur�ckgesetzt");
			break;
			
		case "�ndere":
			int delta = eingabe(">> Delta? ", false, -1, 1, -5, 5);		
			pegel.get(p).aendere(delta);
			System.out.println( ">> Delta wurde erfolgreich um "+delta+ (delta>0 ? " erh�ht.":" gesenkt.") );
			break;
			
		case "setze":
			int wert = Einlesen.liesInt(">> Auf welchen Wert soll der Z�hler gesetzt werden? \n --> ");
			pegel.get(p).setzeAufNull();
			pegel.get(p).aendere(wert);
			System.out.println(">> Pegel "+(p+1)+" wurde erfolgreich auf den Wert "+wert+" gesetzt");
			break;
			
			default:
				return;
		}
	}

	public static void hinzuf�gen() {
		System.out.println(">> Wieviele Pegel sollen hinzugef�gt werden? ");
		int anzahl = Einlesen.liesInt(" --> ");

		for(;anzahl>0; anzahl--) {
			pegel.add( new Z�hler(0) );
		}
	}
	
	public static void ausgeben() {
		if ( pegel.isEmpty() ) {
			System.out.println(">> Es sind keine Pegel vorhanden");
			return;
		}
		
		String anzeige1 = "";
		String anzeige2 = "";
		for(int i=0; i<pegel.size(); i++) {
			anzeige1 += String.format("%12s", ("Pegel "+(i+1)) );
			anzeige2 += String.format("%9d%3s", pegel.get(i).getWert(), "");
		}
		System.out.println(anzeige1);
		System.out.println(anzeige2);
	}
	
	public static int eingabe(String text, boolean zur�ck, int... auswahl) {
		int eingabe;
		do {
			System.out.println(text);
			if( zur�ck )	
				System.out.println(">> (return: -1) ");
			
			System.out.print(">>");
			for(int i:auswahl) {
				System.out.print( String.format("%4s%3d", "", i) );
			}
			
			eingabe = Einlesen.liesInt("\n --> ");
			if(eingabe == -1 && zur�ck)
				return -1;
			for(int i:auswahl) {
				if(eingabe == i)
					return i;
			}
			
			System.out.println("\n >>> Falsche Eingabe !!! <<< \n");
		} while(true);
	}
}
