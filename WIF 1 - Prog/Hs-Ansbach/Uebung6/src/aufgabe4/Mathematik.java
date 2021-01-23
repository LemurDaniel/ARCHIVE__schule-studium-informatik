package aufgabe4;

import javapack.Einlesen;

public class Mathematik {

	public static void main(String[] args) {
		double[] a = {1.0, 2.5, 0.1, 0.0, 0.8, 0.0, 1.5};
		double y, x = Einlesen.liesDouble("x> ");
		
		y=a[0];
		for(int i=1; i<a.length; i++) {
			if(i%2==0)	y += a[i]*Math.pow(x, i);
			else 		y -= a[i]*Math.pow(x, i);
		}
		System.out.format("Ergebnis: %.4f\n", y);
		System.out.format("Ergebnis: %.4e\n", y);
	}

}
