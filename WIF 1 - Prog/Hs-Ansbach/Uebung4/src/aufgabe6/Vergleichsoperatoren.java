package aufgabe6;

import javapack.Einlesen;

public class Vergleichsoperatoren {
	
	public static void main (String[] args) {
		
		int i, j;

		i = Einlesen.liesInt("Variable i> ");
		j = Einlesen.liesInt("Variable j> ");
		
		System.out.format("%d == %d ---> %b\n", i, j, i==j);
		System.out.format("%d != %d ---> %b\n", i, j, i!=j);
		System.out.format("%d >  %d ---> %b\n", i, j, i>j);
		System.out.format("%d >= %d ---> %b\n", i, j, i>=j);
		System.out.format("%d <  %d ---> %b\n", i, j, i<j);
		System.out.format("%d <= %d ---> %b\n", i, j, i<=j);
	}
}
