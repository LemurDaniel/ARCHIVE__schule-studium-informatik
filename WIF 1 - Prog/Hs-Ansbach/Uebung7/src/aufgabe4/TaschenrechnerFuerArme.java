package aufgabe4;

import javapack.Einlesen;
import tools.Abfrage;

public class TaschenrechnerFuerArme {

	public static void main(String[] args) {
		double z1, z2;
		char c;
		
		do {
			z1 = Einlesen.liesDouble("Zahl 1> ");
			z2 = Einlesen.liesDouble("Zahl 2> ");
			c = Einlesen.liesChar("Rechenoperation(+,-,/,*)> ");
			rechner(z1, z2, c);
		} while(Abfrage.weiter());
		System.out.println("*** E N D E ***");

	}
	
	public static void rechner(double a, double b, char c) {	
		double erg;
		switch(c) {
		case '+':	erg= a+b;	break;
		case '-':	erg= a-b;	break;
		case '/':	erg= a/b;	break;
		case '*':	erg= a*b;	break;
			default:	System.out.format("Fehler: '%s' ist kein gültiger Operator\n", c);
						return;
		}
		System.out.format("%f %s %f = %f\n", a, c, b, erg);
	}

}
