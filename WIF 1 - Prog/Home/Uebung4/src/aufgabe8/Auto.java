package aufgabe8;

import javapack.Einlesen;

public class Auto {

	public static void main(String[] args) {
		
		boolean zuendschluessel, handbremse;

		
		zuendschluessel = Einlesen.liesBoolean("Z�ndschl�ssel> ");
		handbremse = Einlesen.liesBoolean("Handbremse> ");		
		System.out.println( zuendschluessel && !handbremse ? "Das Auto f�hrt" : "Das Auto f�hrt nicht" );
	}

}
