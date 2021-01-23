package teil2;

import javapack.Einlesen;

public class Fingeruebung1 {
	
	public static void main(String[] args) {
		
		int i = 10;
		int i2 = Einlesen.liesInt("Variable 2: ");
		int i3 = Einlesen.liesInt("Variable 3: ");
		int result = (i + i2) * i3;
		System.out.println("Ergebnis: (" + i + " + " + i2 + ") * " + i3 + " = " + result );
		
	}

}
