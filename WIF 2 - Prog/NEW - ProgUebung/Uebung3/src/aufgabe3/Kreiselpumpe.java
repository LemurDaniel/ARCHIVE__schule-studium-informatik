package aufgabe3;

public class Kreiselpumpe extends Pumpe {

	 private int anzahlSchaufeln;
	 private int maximaleDrehzahl;

	 public Kreiselpumpe(String name, int tiefe, int hoehe, int breite, int gewicht, int maximaleFoerdermenge, float maximalerBetriebsdruck, int anzahlSchaufeln, int maximaleDrehzahl) {
		 super(name, tiefe, hoehe, breite, gewicht, maximaleFoerdermenge, maximalerBetriebsdruck);
		 this.anzahlSchaufeln = anzahlSchaufeln;
		 this.maximaleDrehzahl = maximaleDrehzahl;
	 }
}
