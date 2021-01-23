package aufgabe1;

import javapack.Einlesen;

public class Aufgabe1 {

	public static void main(String[] args) {
		
		long l1;
		int i1;
		short s1;
		double d1;
		float f1;
		char c1;
		
		l1 = Einlesen.liesLong("long> ");
		i1 = Einlesen.liesInt("int> ");
		s1 = Einlesen.liesShort("short> ");
		d1 = Einlesen.liesDouble("double> ");
		f1 = Einlesen.liesFloat("float> ");
		c1 = Einlesen.liesChar("char> ");

				
		System.out.printf("%c = %d\n", c1, (int)c1);
		double ergebnis = l1 + i1 + s1 + f1 + d1 + c1;
		System.out.format("A1: %f = %d + %d + %d + %f + %f + %c\n", ergebnis, l1, i1, s1, f1, d1, c1);
		String sErgebnis1 = "" + l1 + i1 + s1 + f1 + d1 + c1;
		System.out.format("A2: " + sErgebnis1 + " = \"\" + %d + %d + %d + %f + %f + %c\n", l1, i1, s1, f1, d1, c1);
		String sErgebnis2 = l1 + i1 + s1 + f1 + d1 + c1 + "";
		System.out.format("A3: " + sErgebnis2 + " = %d + %d + %d + %f + %f + %c + \"\"\n", l1, i1, s1, f1, d1, c1);
		String sErgebnis3 = l1 + i1 + s1 + "" + f1 + d1 + c1;
		System.out.format("A4: " + sErgebnis3 + " = %d + %d + %d + \"\" + %f + %f + %c\n", l1, i1, s1, f1, d1, c1);
		int iErgebnis = (int)l1 + i1 + s1 + (int)f1 + (int)d1 + c1;
		System.out.format("A5: " + iErgebnis + " = (int)%d + %d + %d + (int)%f + (int)%f + %c\n", l1, i1, s1, f1, d1, c1);
		float fErgebnis = l1 + i1 + s1 + f1 + (float)d1 + (int)c1;
		System.out.format("A6: " + fErgebnis + " = %d + %d + %d + %f + (float)%f + (int)%c\n", l1, i1, s1, f1, d1, c1);
		
		// A1: Das Ergebnis ist vom Typ Double, da ein Operand vom Typ Double ist
		// A2: Das Ergebnis ist vom Typ String, da der erste Operand vom Typ String ist
		// A3 & A4: Die Operanden werden normal miteinander verrechnet, bis auf den Operanden vom Typ String gestoﬂen wird. Das vorherig errechnete Ergebnis wird an den String vorne Konkateniert und alle folgenden Argumente werden auch an den String angeh‰ngt.
		// A5: Das Ergebnis ist vom Typ int, da alle Operanden die nicht bereits vom Typ byte, short, long oder int sind in den Typ int "gecasted" werden.
		// A6: Das Ergebnis ist vom Typ float, da der Operand vom Typ Dobule in den Typ float "gecasted" wird.


	}

}
