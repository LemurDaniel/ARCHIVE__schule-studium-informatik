package aufgabe10.vorgabe;

public class ToolKlasse2 {
	public String flaeche(int typ, double a, double b) {
		if(typ==1)
			return "Fl�che Rechteck: " + a*b;
		else
			return "Fl�che Ellipse: " + Math.PI*a*b;
	}
}
