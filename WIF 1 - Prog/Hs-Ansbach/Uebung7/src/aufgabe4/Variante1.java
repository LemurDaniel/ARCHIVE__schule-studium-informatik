package aufgabe4;

import javapack.Einlesen;
import tools.Abfrage;

public class Variante1 {

	public static void main(String[] args) {
		double z1, z2;
		char c;
		
		do {
			z1 = Einlesen.liesDouble("Zahl 1> ");
			z2 = Einlesen.liesDouble("Zahl 2> ");
			
			while(true) {
				c = Einlesen.liesChar("Rechenoperation> ");
				if(!operatorTest(c)) {
					System.out.format("Fehler: '%s' ist kein gültiger Operator.\n", c);
					System.out.println("Bitte geben sie ein gültiges Zeichen ein.");
				} else 
					break;
			}
			
			System.out.format("%f %s %f = %f\n", z1, c, z2, rechner(z1, z2, c));	
			
		} while(Abfrage.weiter());
		System.out.println("*** E N D E ***");
	}
	
	public static boolean operatorTest(char c) {
		switch(c) {
		case '+':	case '-':	case '/':	case ':':	case '*':	return true;
			default:	return false;
		}
	}
	
	public static double rechner(double a, double b, char c) {	
		switch(c) {
		case '+':	return a+b;
		case '-':	return a-b;
		case ':':
		case '/':	return a/b;
		case '*':	return a*b;
			default:	return 0;
		}
	}

}
