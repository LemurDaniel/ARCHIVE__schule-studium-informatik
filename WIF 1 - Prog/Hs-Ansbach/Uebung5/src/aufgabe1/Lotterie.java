package aufgabe1;

import javapack.Einlesen;

public class Lotterie {

	public static void main(String[] args) {
		
		int losNr = Einlesen.liesInt("Losnummer> ");
		
		if(losNr % 13 == 0) 
			System.out.println("Das Los gewinnt 100 Euro!");
		else if (losNr % 7 == 0)
			System.out.println("Das Los gewinnt einen Euro!");
		else 
			System.out.println("Leider eine Niete :(");
	}

}
