package aufgabe3.getraenke;

	public class Wein extends Getraenk {
	private String herkunft;
	
	public Wein(String origin) {
		herkunft = origin;
	}
	public String getHerkunft() {
		return herkunft;
	}
	public String toString() {
		return ("Wein aus " + herkunft);
	}
}
