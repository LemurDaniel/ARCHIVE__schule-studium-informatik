package aufgabe8;

import javapack.Einlesen;

public class Aufgabe8 {

	public static void main(String[] args) {
		
		double x, y, z;
		
		x = Einlesen.liesDouble("Variable x> ");
		y = Einlesen.liesDouble("Variable y> ");
		
		z = x*x*y*y - 4*x*y + 4;
		System.out.printf("a) %g\n", z);
		z = Math.pow((1+x*y),2) / (1 + Math.pow((1+x*y),4));
		System.out.printf("b) %g\n", z);
		//z = 2*y;
		z = x*y + (3 - x)*y - y;		
		System.out.printf("c) %g\n", z);
		z = 2*y;
		System.out.printf("d) %g\n", z);
		
	}

}
