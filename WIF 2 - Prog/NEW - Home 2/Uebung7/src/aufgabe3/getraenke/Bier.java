package aufgabe3.getraenke;

public class Bier extends Getraenk {
	private String brauerei;
	
	public Bier(String b) {
	brauerei = b;
	}
	
	public String getBrauererei() {
	return brauerei;
	}
	public String toString() {
	return "Bier von " + brauerei;
	}
}
