package Buchungssystem;

public class Fehler {
	
	@SuppressWarnings("serial")
	static public class BuchungsFehler extends Exception{
			public BuchungsFehler(String text){
			super(text);
			}
	}

}
