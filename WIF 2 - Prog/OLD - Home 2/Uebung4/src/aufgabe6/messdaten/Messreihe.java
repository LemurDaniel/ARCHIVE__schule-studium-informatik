package aufgabe6.messdaten;

import java.time.LocalDate;

public class Messreihe {

	protected Messwert[] messwerte;
	
	
	public Messreihe(int messwertAnzahl) {
		messwerte = new Messwert[messwertAnzahl];
	}
	
	
	public void addMesswert(double messwert, LocalDate datum) {
		
		for(int i=0; i<messwerte.length; i++) {
			if (messwerte[i] == null) {
				messwerte[i] = new Messwert(messwert, datum);
				return;
			}
		}
		
		System.out.println("\n\t!!!Alle Messwerte sind bereits vergeben!!!\n");
	}
	
	public int getMesswert(LocalDate datum) {
		return 1;
	}
	
	public void print() {
		
		System.out.println( "\t" + messwerte.length + ". Messwerte \n");
		
		for(int i=0; i<messwerte.length; i++) {
			System.out.println("\t MesswertID: \t" + messwerte[i].getMesswertID() );
			System.out.println("\t MessDatum:  \t" + messwerte[i].getMessDatum() );
			System.out.printf("\t Messwert:    \t%.2f \n", messwerte[i].getWert() );
			System.out.println();
		}
	}
	
}
