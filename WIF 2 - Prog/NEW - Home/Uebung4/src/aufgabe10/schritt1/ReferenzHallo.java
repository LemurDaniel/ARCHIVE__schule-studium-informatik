package aufgabe10.schritt1;

import aufgabe10.vorgabe.ToolKlasse;

public class ReferenzHallo {

	public static void main(String[] args) {
		
		berechneUndGibAus( 
				(p1, p2, p3) -> {return ToolKlasse.flaeche(p1, p2, p3);}, 
						   s -> System.out.println(s),
				2, 3.5, 4.3);
	}
	
	static void berechneUndGibAus(IFPar3 p3, IFPar1 p1, int typ, double a, double b) {
		p1.machWas(p3.berechne(typ, a, b));
	}
	
	@FunctionalInterface
	interface IFPar3 {
		public String berechne(int p1, double p2, double p3);
	}
	
	@FunctionalInterface
	interface IFPar1 {
		public void machWas(String s);
	}
}
