package aufgabe5;

import javapack.Einlesen;

public class Zaehlschleifen {

	public static void main(String[] args) {
		int n = Einlesen.liesInt("n> ");
		for(int i=n; i>3; i-=2) {
			System.out.format("for --> n: %d, Zählerstand: %d\n", n, i);
		}
		System.out.println();
		
		int i=n;
		while(i>3) {
			System.out.format("While --> n: %d, Zählerstand: %d\n", n, i);
			i-=2;
		}
		System.out.println();
		
		i=n;
		do {
			System.out.format("do...While --> n: %d, Zählerstand: %d\n", n, i);
			i-=2;
		} while(i>3);
		
	}
	
}
