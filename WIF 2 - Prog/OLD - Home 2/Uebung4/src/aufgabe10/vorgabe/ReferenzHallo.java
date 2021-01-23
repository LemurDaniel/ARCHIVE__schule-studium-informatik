package aufgabe10.vorgabe;

public class ReferenzHallo {
	
	public static void main(String[] args) {
		
		berechneUndGibAus(new IFPar3(){
			@Override
			public String berechne(int p1, double p2, double p3) {
				return ToolKlasse.flaeche(p1, p2, p3);
			}
		},
		new IFPar1() {
			@Override
			public void machWas(String s) {
				System.out.println(s);
			}
		},
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
