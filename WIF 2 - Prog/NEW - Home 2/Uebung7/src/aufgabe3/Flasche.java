package aufgabe3;

import aufgabe3.getraenke.Bier;
import aufgabe3.getraenke.Getraenk;
import aufgabe3.getraenke.Rotwein;
import aufgabe3.getraenke.Wein;

public class Flasche<T extends Getraenk> {
	
	public static void main(String[] args) {
		Flasche<Wein> f1 = new Flasche<>();
		Flasche<Bier> f2 = new Flasche<>();
		
		f1.fuellen(new Rotwein("Bla"));
		f2.fuellen(new Bier("aaaaa"));
	}
	
	
	
	T inhalt = null;
	
	public boolean istLeer() {
		return (inhalt == null);
	}
	
	public void fuellen(T g) {
		inhalt = g;
	}
	
	public Getraenk leeren() {
		Getraenk result = inhalt;
		inhalt = null;
		return result;
	}
}