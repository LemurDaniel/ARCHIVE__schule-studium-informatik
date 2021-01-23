package aufgabe3;

public class Membranpumpe extends Pumpe{
	 
	 private String membranmaterial;
	 
	 public Membranpumpe(String name, int tiefe, int hoehe, int breite, int gewicht, int maximaleFoerdermenge, float maximalerBetriebsdruck, String membranmaterial) {
		 super(name, tiefe, hoehe, breite, gewicht, maximaleFoerdermenge, maximalerBetriebsdruck);
		 this.membranmaterial = membranmaterial;
	 }
}
