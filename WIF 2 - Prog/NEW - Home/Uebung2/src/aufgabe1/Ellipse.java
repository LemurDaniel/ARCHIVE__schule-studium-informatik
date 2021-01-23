package aufgabe1;

import java.awt.Graphics;

// Eine Unterklasse o.O
//Einfachvererbung -- Keine Mehrfachverebung in Java möglich aber interfaces
public class Ellipse extends GObjekt {

	// Die gleichnahmigen Variablen der Superklasse werden durch diese verborgen.
	// Objektvariablen
	protected int posX;
	protected int posY;
	
	// abstrakte methode der Oberklasse wird implementiert oder auch überschrieben.
	// Objektoperationen
	@Override
	void zeichnen(Graphics g) {
		// TODO Auto-generated method stub

	}

}
