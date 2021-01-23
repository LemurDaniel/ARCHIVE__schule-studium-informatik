package aufgabe8.vorgabe;

public class Meldung {

	private String typ, inhalt;
	
	public Meldung(String typ, String inhalt) {
		this.typ = typ;
		this.inhalt = inhalt;
	}
	
	public String getTyp() {
		return typ;
	}
	
	public String getInhalt() {
		return inhalt;
	}
}
