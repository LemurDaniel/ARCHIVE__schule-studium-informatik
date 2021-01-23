package zvPaket;

import javapack.Einlesen;

public class J00150523 {
	
	public static void main(String[] args) {
		
		int sekunden, minuten, stunden, tage, sekundenProbe;
		sekunden = Einlesen.liesInt("Anzahl Sekunden: ");
		
		tage = (sekunden / 86400); 			
		stunden = (sekunden / 3600) % 24;	
		minuten = (sekunden / 60) % 60;		
		sekunden %= 60;						
		
		sekundenProbe = tage*86400 + stunden*3600 + minuten*60 + sekunden; 

		System.out.format("--> %d Tage + %d Stunden + %d Minuten + %d Sekunden\n", tage, stunden, minuten, sekunden);
		System.out.format("--> (%d*24*60*60 + %d*60*60 + %d*60 + %d) Sekunden = %d Sekunden\n", tage, stunden, minuten, sekunden, sekundenProbe); 
	}

}
