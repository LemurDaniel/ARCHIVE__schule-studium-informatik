package aufgabe3;

@SuppressWarnings("unused")
public class Pumpe extends Produkt {
	
	 private int maximaleFoerdermenge;	 
	 private float maximalerBetriebsdruck;

	 Pumpe(String name, int tiefe, int hoehe, int breite, int gewicht, int maximaleFoerdermenge, float maximalerBetriebsdruck){
		 super(name, tiefe, hoehe, breite, gewicht);
		 this.maximaleFoerdermenge = maximaleFoerdermenge;
		 this.maximalerBetriebsdruck = maximalerBetriebsdruck;
	 }
}
