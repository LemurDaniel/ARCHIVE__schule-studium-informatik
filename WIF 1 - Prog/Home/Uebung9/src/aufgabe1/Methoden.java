package aufgabe1;

public class Methoden {
	
	private static int objektAnzahl;
	private int objektNr;

	//Konstruktor -- kein Typ -- keine Parameter
	public Methoden() {
		objektAnzahl++; 
		objektNr=objektAnzahl;
	}
	
	//Prozedur -- Typ: void -- keine Parameter -- nicht statisch
	public void drucke() {
		System.out.println(this.getClass().getName());
	}
	
	//Funktion -- Typ: boolean -- keine Parameter -- nicht statisch
	public boolean letztesObjekt () {
		boolean status = objektAnzahl==objektNr; 
		return status;
	}

	//Funktion -- Typ: int -- keine Parameter -- nicht statisch
	public int zaehleZeichen(String einString) {
		return einString.length();
	}

	//Funktion -- Typ: int -- keine Parameter -- statisch
	public static int getObjektanzahl() {
		return objektAnzahl;
	}

	//Funktion -- Typ: String -- zwei String-Objekte als Parameter -- nicht statisch
	public String verkette(String einString, String zweiString) {
		return einString + zweiString;
	}
}
	// Alle nicht statischen Methoden können nur nach Erzeugen einer Objektes der Klasse Methoden(), über dieses Objekt aufgerufen werden.
	// Bsp:
	// Methoden m1 = new Methoden();
	// m1.drucke();
	//
	// Alle statischen Methoden können direkt über die Klasse aufgerufen werden. 
	// Bsp:
	// Methoden.getObjektanzahl();