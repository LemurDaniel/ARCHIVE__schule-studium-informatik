package aufgabe9;

import javapack.Einlesen;

public class ArbeitenMitAdressen {

	public static void main(String[] args) {

		Punkt punkt = new Punkt(), punkte[] = new Punkt[3], punkteKopie[];
		
		punkt.x = Einlesen.liesInt("X-Koordinate> ");
		punkt.y = Einlesen.liesInt("Y-Koordinate> ");	
		
		punkte[0] = punkte[1] = punkte[2] = punkt;		
		punkteKopie = punkte;
		ausgeben("Punkte: ", punkte);
		ausgeben("Kopie:  ", punkteKopie);		
		
		punkt.x = Einlesen.liesInt("X-Koordinate> ");
		punkt.y = Einlesen.liesInt("Y-Koordinate> ");
		ausgeben("Punkte: ", punkte);
		ausgeben("Kopie:  ", punkteKopie);

	}
	
	public static void ausgeben(String text, Punkt[] pArr) {
		System.out.print(text);
		for (int i = 0; i < pArr.length; i++) {
			System.out.format("Punkt[%d](%d, %d)	", i, pArr[i].x, pArr[i].y);
		}
		System.out.println("");
	}

}
