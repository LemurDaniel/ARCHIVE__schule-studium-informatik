package aufgabe7;

import javapack.Einlesen;
import tools.Abfrage;

public class Modifizierte4 {

	public static void main(String[] args) {
		long z1, z2;
		char c, c2;
		long erg;
		
		do {
			z1 = Einlesen.liesLong("Zahl 1> ");
			z2 = Einlesen.liesLong("Zahl 2> ");

			boolean loop = true;
			while(true) {
				c = Einlesen.liesChar("Rechenoperation> ");				
				if(!operatorTest(c)) {
					System.out.format("Fehler: '%s' ist kein gültiger Operator.\n", c);
					System.out.println("Bitte geben sie ein gültiges Zeichen ein.");
				}else	
					break;
			} 
			
			loop = true;
			while(true) {
				c2 = Character.toLowerCase(Einlesen.liesChar("Quersumme/Drehen(q/d)> "));
				if(c2!='d' && c2!='q') {
					System.out.format("Fehler: '%s' ist kein gültiger Operator.\n", c2);
					System.out.println("Bitte geben sie ein gültiges Zeichen ein.");
				} else 
					break;
			}
		
			
			erg = rechner(z1, z2, c);
			System.out.format("%d %s %d = %d\n", z1, c, z2, erg);		
			if(c2=='d')	System.out.println( "Gedreht: "+drehe(zerlege(erg)) );
			else		System.out.println( "Quersumme: "+quersumme(zerlege(erg)) );
			
		} while(Abfrage.weiter());
		System.out.println("*** E N D E ***");
	}
	
	public static boolean operatorTest(char c) {
		switch(c) {
		case '+':	case '-':	case '/':	case ':':	case '*':	return true;
			default:	return false;
		}
	}
	
	public static long rechner(long a, long b, char c) {	
		switch(c) {
		case '+':	return a+b;
		case '-':	return a-b;
		case ':':
		case '/':	return a/b;
		case '*':	return a*b;
			default:	return 0;
		}
	}
	
	static int[] zerlege(long zahl)	{;
		int[] ziffer = new int[String.valueOf(zahl).length()];
		
		for(int i=0; i<ziffer.length; i++) {
			ziffer[i] = (int) (zahl/ (long)Math.pow(10, i) %10);
		}
		return ziffer;
	}
	
	static long drehe(int[] ziffern){
		long l = 0;
		for(int i=0; i<ziffern.length; i++) {
			l += ziffern[ziffern.length-i-1]* (long)Math.pow(10, i);
		}
		return l;
	}
	
	static long quersumme(int[] ziffern) {
		long quersumme = 0;
		for(int i=0; i<ziffern.length; i++) {
			quersumme += ziffern[i];
		}
		return quersumme;
	}

}

