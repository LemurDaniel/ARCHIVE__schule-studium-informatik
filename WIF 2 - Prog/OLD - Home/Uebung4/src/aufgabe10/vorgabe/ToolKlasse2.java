package aufgabe10.vorgabe;

public class ToolKlasse2 {
	public String flaeche(int typ, double a, double b) {
		if(typ==1)
			return "Fläche Rechteck: " + a*b;
		else
			return "Fläche Ellipse: " + Math.PI*a*b;
	}
}
