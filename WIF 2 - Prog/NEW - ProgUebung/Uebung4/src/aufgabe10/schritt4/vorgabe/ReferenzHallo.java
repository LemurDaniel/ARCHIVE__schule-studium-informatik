package aufgabe10.schritt4.vorgabe;

public class ReferenzHallo {

	public ReferenzHallo(){
		
		berechneUndGibAus(new IFPar3() {
			@Override
			public String berechne(int p1, double p2, double p3) {
				return flaeche(p1, p2, p3);
			}
		},new IFPar1() {
			@Override
			public void machWas(String s) {
				System.out.println(s);
			}
		}, 2, 3.5, 4.3);
		
	}
		
	public static void main(String[] args) {
		new ReferenzHallo();
	}
	
	void berechneUndGibAus(IFPar3 p3, IFPar1 p1, int typ, double a, double b) {
		p1.machWas(p3.berechne(typ, a, b));
	}
	
	
	String flaeche(int typ, double a, double b) {
		if (typ == 1)
			return "Fläche Rechteck: " + a * b;
		else
			return "Fläche Ellipse: " + Math.PI * a * b;
	}

	interface IFPar3 {
		public String berechne(int p1, double p2, double p3);
	}
	interface IFPar1 {
		public void machWas(String s);
	}
}
