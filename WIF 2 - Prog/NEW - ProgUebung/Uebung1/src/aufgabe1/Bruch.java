package aufgabe1;

public class Bruch {

	//
	private int zaehler, nenner;
	
	
	//
	Bruch(int zaehler, int nenner){
		this.zaehler = zaehler;
		this.nenner = nenner;
	}
	
	Bruch(int zaehler){
		this(zaehler, 1);
	}
	
	
	//
	public Bruch multipliziere(int n) {
		return new Bruch(zaehler*n, nenner);
	}
	
	public Bruch multipliziere(Bruch b) {
		return new Bruch( zaehler*b.getZaehler(), nenner* b.getNenner() );
	}
	
	public Bruch dividiere(int n) {
		return new Bruch(zaehler, nenner*n);
	}
	
	public Bruch dividiere(Bruch b) {
		return new Bruch( zaehler*b.getNenner(), nenner* b.getZaehler() );
	}
	
	public Bruch kuerze() {
		int ggN;
		if (nenner > zaehler)
			ggN = ggN(nenner, zaehler);
		else
			ggN = ggN(zaehler, nenner);
	
		zaehler /= ggN;
		nenner /= ggN;
		return this;
	}
	
	public int ggN(int n, int n2) {		
		if (n%n2 == 0) 
			return n2;
		else
			return ggN(n2, n%n2);
	}
	
//	public int ggN(int n, int n2) {		
//		
//		while (n%n2 != 0) {
//			int temp = n2;
//			n2 = n%n2;
//			n = temp;		
//		}
//		return n2;
//	}
	
	//
	public int getZaehler() {
		return zaehler;
	}
	
	public int getNenner() {
		return nenner;
	}
	
	public String toString() {
		return zaehler+"/"+nenner;
	}
	
	
}
