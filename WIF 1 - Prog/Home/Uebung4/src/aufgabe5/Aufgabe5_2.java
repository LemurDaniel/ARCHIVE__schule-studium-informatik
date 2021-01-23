package aufgabe5;

import javapack.Einlesen;

public class Aufgabe5_2 {
	
	public static void main(String[] args) {
		
		int ganzzahl, quersumme = 0, temp_zahl = 0, zahl_laenge;
		String ganzzahl_umgedreht = "";
		
		ganzzahl = Einlesen.liesInt("Ganzzahl> ");
		zahl_laenge = String.valueOf(ganzzahl).length();
		
		for(int i = 0; i < zahl_laenge; i++) {
			temp_zahl = ganzzahl/(int)Math.pow(10, zahl_laenge-i-1);
			ganzzahl -= temp_zahl*(int)Math.pow(10, zahl_laenge-i-1);
			
			System.out.println(1+i + ". ---> " + temp_zahl);
			ganzzahl_umgedreht =  temp_zahl + ganzzahl_umgedreht;
			quersumme += temp_zahl;
		}
			System.out.println("Quersumme: " + quersumme);
			System.out.println("Umgedrehte Zahl: " + ganzzahl_umgedreht);
	}
}
