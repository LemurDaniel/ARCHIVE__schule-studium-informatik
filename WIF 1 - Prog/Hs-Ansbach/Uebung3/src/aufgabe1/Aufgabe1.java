package aufgabe1;

import javapack.Einlesen;

public class Aufgabe1 {

	public static void main(String[] args) {

		int v1, v2, v3;

		v1 = Einlesen.liesInt("Dezimalzahl> ");
		v2 = Einlesen.liesInt("Oktalzahl> ");
		v3 = Einlesen.liesInt("Hexadezimalzahl> ");
		
		System.out.printf("dezimal: %d, oktal: %1$o, hexadezimal: %1$x\n", v1);
		System.out.printf("oktal: %o, dezimal: %1$d, hexadezimal: %1$x\n", v2);
		System.out.printf("hexadezimal: %x, oktal: %1$o, deziaml: %1$d\n", v3);
		

	}

}


