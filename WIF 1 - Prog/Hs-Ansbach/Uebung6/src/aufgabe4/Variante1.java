package aufgabe4;

import javapack.Einlesen;

public class Variante1 {

	public static void main(String[] args) {
		double[] a = new double[Einlesen.liesInt("Feldlänge> ")];
		double y=0, x = Einlesen.liesDouble("x> ");
		
		System.out.println("Feld: ");
		for(int i=0; i<a.length; i++) {
			a[i] = (int)(Math.random()*100)/10d;	
			System.out.print(a[i] + (i==a.length-1 ? "\n":"; "));
			if(i%2==0)	y += a[i]*Math.pow(x, i);
			else 		y -= a[i]*Math.pow(x, i);
		}
		System.out.format("Ergebnis: %.4f\n", y);
		System.out.format("Ergebnis: %.4e\n", y);
	}

}
