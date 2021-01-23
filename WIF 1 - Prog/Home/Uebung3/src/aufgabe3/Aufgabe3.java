package aufgabe3;

import javapack.Einlesen;

public class Aufgabe3 {

		public static void main(String[] args) {
			
			double a, b;
			
			a = Einlesen.liesDouble("Variable a> ");
			b = Einlesen.liesDouble("Variable b> ");
			
			System.out.println("(1) a + b = " + (a+b));
			System.out.println("(2) a - b = " + (a-b));
			System.out.println("(3) a * b = " + (a*b));
			System.out.println("(4) a / b = " + (a/b));
			System.out.println("(5) a % b = " + (a%b));
		}
		
}
