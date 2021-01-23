package aufgabe5;

import javapack.Einlesen;

public class Zahlenspiel {

	public static void main(String[] args) {
		
		int zahl = Einlesen.liesInt("Zahl> ");
		int s1, s2, s3, s4;
		int quer;
		
		if (zahl>9999 || zahl<1000) {
			System.out.println("Zahl nicht gut: " + zahl);
			System.exit(0);
		}
		
		s1 = zahl / 1000;
		s2 = zahl / 100 % 10;
		s3 = zahl / 10 % 10;
		s4 = zahl % 10;
		quer = s1 + s2 + s3 + s4;
		
		System.out.println("1. --> " + s1);
		System.out.println("2. --> " + s2);
		System.out.println("3. --> " + s3);
		System.out.println("4. --> " + s4);
		System.out.println("Quersumme " + quer);
		System.out.println("Umgedrehte Zahl: " + s4 + s3 + s2+ s1);
	}

}
