package aufgabe11;

public class Selbsttest {

	public static void main(String[] args) {

		int i = 2, j = 3, erg1, erg2;
		
		erg1 = (i++ == j ? 7 : 8) % 3;
		// Ergebnis: 2
		System.out.println("Ergebnis1: " + erg1);
				
		erg2 = (++i == j ? 7 : 8) % 2;
		// Ergebnis: 0
		System.out.println("Ergebnis2: " + erg2);
		
		
		boolean la1, la2, la3;
		i = 3;
		char c = 'n';
		
		la1 = 2 > 3 && 2 == 2 ^ 1 == 1;
		// Ergebnis: False
		System.out.println("Ergebnis3: " + la1);
		
		la2 = (2 > 3 && 2 == 2) ^ (1 == 1);
		// Ergebnis: True
		System.out.println("Ergebnis4: " + la2);	
		
		la3 = !(i > 0 || c == 'j');
		// Ergebnis: False
		System.out.println("Ergebnis5: " + la3);
	}

}
