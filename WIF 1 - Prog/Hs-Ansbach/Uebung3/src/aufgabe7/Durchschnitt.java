package aufgabe7;

public class Durchschnitt {
	
	public static void main(String[] args) {

		int summe = 1; // Summe wird mit Wert 1 statt 0 initialisiert
		
		int ersteZahl = 3;
		System.out.println("erste Zahl : " + ersteZahl);
		summe = summe + ersteZahl;
		
		int zweiteZahl = 2;
		summe = summe + ersteZahl; // Hier wird nochmals die erste Zahl, anstatt der Zweiten, addiert
		System.out.println("zweite Zahl : " + zweiteZahl);
		double durchschnitt = summe / 2; // mindestens ein Operand muss float oder double sein, damit das Ergebnis in Gleitkommaarithmetik berechnet wird
		
		System.out.println("Der Durchschnitt der beiden Zahlen ist: " 
		+ durchschnitt);
	}
}