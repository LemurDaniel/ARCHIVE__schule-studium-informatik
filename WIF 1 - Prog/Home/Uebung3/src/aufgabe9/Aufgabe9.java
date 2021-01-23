package aufgabe9;

import javapack.Einlesen;

public class Aufgabe9 {

	public static void main (String[] args) {
		
		int n, m;
		double x, y;
		double[] A = {1.0, 2.5, 0.1, 0.0, 0.8, 0.0, 1.5};
		
		n = Einlesen.liesInt("Variable n> ");
		m = Einlesen.liesInt("Variable m> ");
		x = Einlesen.liesDouble("Variable x> ");
		
		y = Math.log( Math.abs(n-m)*x );
		System.out.printf("Ausdruck 1: % .4f\n", y);
		y = Math.sin( (n+m)*x/2.0 ) -  Math.cos( (n-m)*x/2.0 );
		System.out.printf("Ausdruck 2: % .4f\n", y);
		y = 0.5*(n-m)*x*Math.exp(-(n+m)*x*x) / ((double)(n+m));
		System.out.printf("Ausdruck 3: % .7f\n", y);
		
		y = A[0] - A[1]*x + A[2]*x*x - A[3]*Math.pow(x,3) + A[4]*Math.pow(x,4) - A[5]*Math.pow(x,5) + A[6]*Math.pow(x,6);
		System.out.printf("Ausdruck 4: % .4g\n", y);

		
		y = Math.pow((Math.exp(x)+x), 2) / (0.5*x+Math.exp(x)) - Math.exp(x);
		System.out.printf("Ausdruck 5: % f\n", y);
		
		
	}
}
