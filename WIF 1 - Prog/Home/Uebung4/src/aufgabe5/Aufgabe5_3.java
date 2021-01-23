package aufgabe5;

import javapack.Einlesen;

public class Aufgabe5_3 {

	public static void main(String[] args) {

		int ganzzahl = 0, quersumme = 0, temp_zahl;
		String umgedrehteZahl = "";
		
		
		while( String.valueOf(ganzzahl).length() != 4) {		
				ganzzahl = Einlesen.liesInt("4 stellige Ganzzahl> ");
		}
		
		temp_zahl = ganzzahl / 1000;
		ganzzahl -= temp_zahl*1000;
		quersumme += temp_zahl;
		umgedrehteZahl = temp_zahl + umgedrehteZahl;
		System.out.println("1. ---> " + temp_zahl);
		
		temp_zahl = ganzzahl / 100;
		ganzzahl -= temp_zahl*100;
		quersumme += temp_zahl;
		umgedrehteZahl = temp_zahl + umgedrehteZahl;
		System.out.println("2. ---> " + temp_zahl);
		
		temp_zahl = ganzzahl / 10;
		ganzzahl -= temp_zahl*10;
		quersumme += temp_zahl;
		umgedrehteZahl = temp_zahl + umgedrehteZahl;
		System.out.println("3. ---> " + temp_zahl);
		
		temp_zahl = ganzzahl / 1;
		ganzzahl -= temp_zahl*1;
		quersumme += temp_zahl;
		umgedrehteZahl = temp_zahl + umgedrehteZahl;
		System.out.println("4. ---> " + temp_zahl);
		
		System.out.println("Quersumme: " + quersumme);
		System.out.println("Umgedrehte Zahl: " + umgedrehteZahl);
	}

}
