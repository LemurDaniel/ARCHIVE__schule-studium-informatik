package aufgabe6;

import javapack.Einlesen;

public class Aufgabe6 {

	public static void main(String[] args) {
	
		float f;
		
		f = Einlesen.liesFloat("Float> ");

		System.out.printf("Gebrochener Anteil: %f\n", f%1);
		System.out.printf("Ganzer Anteil: %.0f", f-f%1);


	}

}
