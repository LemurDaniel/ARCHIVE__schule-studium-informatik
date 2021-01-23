package aufgabe2;

public class Hund extends Fuchs{

	Maus m = new Maus();
	Ratte r = new Ratte();
	
	Hund() {
		System.out.println("Hund");
	}
	
	public static void main(String[] args) {
		new Hund();
	}

// Überlegung
//	Maus
//	Maus
//	Ratte
//	Katze
//	Fuchs
//	Hund
	
	
//	kommt raus
//	Katze
//	Fuchs
//	Maus
//	Maus
//	Ratte
//	Hund
	
//	Zuerst Oberste Klasse Konstruktor, dann dessen Variablen.
//	Bis nach unten zur Klasse Hund
}
