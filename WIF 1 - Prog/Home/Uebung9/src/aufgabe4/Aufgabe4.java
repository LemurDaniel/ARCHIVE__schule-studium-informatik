package aufgabe4;

public class Aufgabe4 {
	
	public static void main(String[] args ) {
		//1
		int ergebnis = mutlipliziere(2, 2);
		System.out.println("ergebnis: "+ergebnis);
		System.out.println();
		//2
		Wert w1 = new Wert(4);
		System.out.println("Wert: " +w1.x);
		aendereWert(5, w1);
		System.out.println("Wert: " +w1.x);
		System.out.println();
		//3
		Wert w2 = new Wert(19);
		System.out.println("Wert1: " +w1.x);
		System.out.println("Wert2: " +w2.x);
		tausche(w1, w2);
		System.out.println("Wert1: " +w1.x);
		System.out.println("Wert2: " +w2.x);
	}
	
	//1
	public static int mutlipliziere(int a, int b) {
		return a*b;
	}
	
	//2
	public static void aendereWert(int a, Wert w) {
		w.x = a;
	}
	
	//3
	public static void tausche(Wert w1, Wert w2) {
		int x = w1.x;
		w1.x = w2.x;
		w2.x = x;
	}
	
}
