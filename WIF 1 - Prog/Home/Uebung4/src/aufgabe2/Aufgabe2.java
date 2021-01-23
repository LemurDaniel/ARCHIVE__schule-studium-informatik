package aufgabe2;

public class Aufgabe2 {

	public static void main(String[] args) {
		
		// ---> (a)
		long l1 = 5L, l2 = 123L;
		int i1 = 9, i2 = 5877;
		short s1 = 6;
		byte b1 = 99, b2 = 2;
		float f1 = 2.0f;
		double d1 = 0.222, d2 = 17.0;
		char c1 = '!', c2 = 'a';
		String str1 = "123";
		
		// (b1 * i1) / (f1 * 3.0f)
		// Datentyp: float ---> mind. ein Operand den Typ float hat und keiner den Typ double.
		System.out.println("1) " + ( (b1 * i1) / (f1 * 3.0f) ) );
		
		// "1 + 2 + 3 = " + (i1 - 3)
		// Datentyp: String ---> Die Klammer wird zuerst ausgewertet und das Ergebnis and den String angehängt.
		System.out.println("2) " + ( "1 + 2 + 3 = " + (i1 - 3) ) );
		
		// d1 / f1 + i1
		// Datentyp: double ---> mind. ein Operand vom Typ double.
		System.out.println("3) " + ( d1 / f1 + i1 ) );
		
		// c1 * c2
		// Datentyp: int ---> Zwei Operanden vom Typ Char miteinander verrechnet ergeben den Typ int
		System.out.println("4) " + ( c1 * c2 ) );
		
		// i1 + str1 + l2
		// Datentyp: String ---> Beide Operanden werden jeweils vorne und hinten and den String angehängt.
		System.out.println("5) " + ( i1 + str1 + l2 ) );
		
		// s1 + b1
		// Datentyp: int ---> Bei Rechenoperation wird immer mindestens der Datentyp int als Ergebnis angewandt. 
		System.out.println("6) " + ( s1 + b1 ) );
		
		// l1 + f1 + l2
		// Datentyp: float ---> Ein Operand ist vom Typ float
		System.out.println("7) " + ( l1 + f1 + l2 ) );
		
		// ---> (b)
		// byte b3 = b1 + b2 ---> Ergiebt einen Kompilierfehler, da das Ergebnis vom Typ int ist und nicht in byte hinein passt.
		// Man könnte den Datentyp von b3 in int ändern oder das Ergebnis der Rechnung in byte casten. Hierbei jedoch Inormationsverlust auftreten.
		int b3 = b1 + b2;
		System.out.format(" %d + %d = %d \n", b1, b2, b3);
		byte b4 = (byte)(b1 + b2);
		System.out.format(" (byte)(%d + %d) = %d \n", b1, b2, b3);
		
		b1 = Byte.MAX_VALUE;
		b2 = 1;
		b3 = b1 + b2;
		System.out.format(" %d + %d = %d \n", b1, b2, b3);
		b4 = (byte)(b1 + b2);
		System.out.format(" (byte)(%d + %d) = %d \n", b1, b2, b4);	
		
	}

}
