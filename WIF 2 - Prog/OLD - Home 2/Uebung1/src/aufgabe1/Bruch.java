package aufgabe1;

public class Bruch {

	//
	private int zaehler, nenner;
	
	Bruch(String zaehler, String nenner) throws Exception{
		this(Integer.parseInt(zaehler), Integer.parseInt(nenner));
	}
	//
	Bruch(int zaehler, int nenner){
		this.zaehler = zaehler;
		this.nenner = nenner;
	}
	
	Bruch(int zaehler){
		this(zaehler, 1);
	}
	
	public Bruch subtrahiere(Bruch b) {
		return addiere(b.multipliziere(-1));
	}
	public Bruch addiere(Bruch b) {
		return new Bruch(b.nenner*zaehler + nenner*b.zaehler, nenner*b.nenner).kuerze();
	}
	
	//
	public Bruch multipliziere(int n) {
		return new Bruch(zaehler*n, nenner).kuerze();
	}
	
	public Bruch multipliziere(Bruch b) {
		return new Bruch( zaehler*b.getZaehler(), nenner*b.getNenner() ).kuerze();
	}
	
	public Bruch dividiere(int n) {
		return new Bruch(zaehler, nenner*n).kuerze();
	}
	
	public Bruch dividiere(Bruch b) {
		return new Bruch( zaehler*b.getNenner(), nenner* b.getZaehler() ).kuerze();
	}
	public Bruch erweitern(int i) {
		return new Bruch(zaehler*i, nenner*i);
	}
	
	public Bruch kuerze() {
		int ggN = nenner>zaehler ? ggN(nenner, zaehler):ggN(zaehler, nenner);	
		zaehler /= ggN;
		nenner /= ggN;
		return this;
	}
	
	public int ggN(int n, int n2) {		
		return n%n2==0 ? n2:ggN(n2, n%n2);
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
