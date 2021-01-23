package teil2;

import javapack.Einlesen;

public class Fingeruebung2 {
	
	public static void main(String[] args) {
		
		qf(0);
		qf(2);
		qf(2.0/3.0);
		qf(4);
		
		qf(Einlesen.liesDouble("Variable x: "));
		
	}
	
	public static void qf (double x) {
		double result = 3*x*x - 8*x + 4;
		System.out.println("Bei x = " + x + " ergibt die Quadratgleichung den Wert " + result);
	}	


}
