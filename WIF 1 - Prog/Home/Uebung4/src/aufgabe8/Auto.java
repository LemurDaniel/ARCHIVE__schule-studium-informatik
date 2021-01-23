package aufgabe8;

import javapack.Einlesen;

public class Auto {

	public static void main(String[] args) {
		
		boolean zuendschluessel, handbremse;

		
		zuendschluessel = Einlesen.liesBoolean("Zündschlüssel> ");
		handbremse = Einlesen.liesBoolean("Handbremse> ");		
		System.out.println( zuendschluessel && !handbremse ? "Das Auto fährt" : "Das Auto fährt nicht" );
	}

}
