package aufgabe7;

public class Durchschnitt2 {

	public static void main(String[] args) {
		// Korrektur von Durchsschnitt
		
		int summe;
		
		int ersteZahl = 3;
		System.out.println("erste Zahl : " + ersteZahl);
		summe = ersteZahl;
		
		int zweiteZahl = 2;
		summe = summe + zweiteZahl;
		System.out.println("zweite Zahl : " + zweiteZahl);
		double durchschnitt = summe / 2.0;
		System.out.println("Der Durchschnitt der beiden Zahlen ist: " + durchschnitt);

	}

}
