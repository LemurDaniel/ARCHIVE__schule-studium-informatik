package aufgabe1;

import java.awt.Graphics;

// Eine Oberklasse :O
// Sie ist abstrakt --> nicht instanzierbar
abstract class GObjekt {

	// Klassenattribut - wird nicht vererbt
	static int objektAnzahl = 0;
	
	// Objektattribute - werden vererbt
	protected int posX;
	protected int posY;
	
	// Objektoperationen - werden vererbt
	// Polymorphismus -> alle Unterklasse besitzen diese Methode;
	abstract void zeichnen(Graphics g);
	
	// Klassenoperationen - werden nicht vererbt
	// Polymorphismus -> anwendbar auf alle Objekte einer Unterklasse;
	static void objektHinzufuegen(GObjekt objekt) {
		
	}
	
	static void objektEntfernen(GObjekt objekt) {
		
	}
}
