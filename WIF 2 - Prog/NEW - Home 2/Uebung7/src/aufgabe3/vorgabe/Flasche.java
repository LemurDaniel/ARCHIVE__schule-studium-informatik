package aufgabe3.vorgabe;

import aufgabe3.getraenke.Getraenk;

public class Flasche {
	
	Getraenk inhalt = null;
	public boolean istLeer() {
		return (inhalt == null);
	}
	public void fuellen(Getraenk g) {
		inhalt = g;
	}
	public Getraenk leeren() {
		Getraenk result = inhalt;
		inhalt = null;
		return result;
	}
}