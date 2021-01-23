package aufgabe2;

import javapack.Einlesen;

public class Aufgabe2 {
	
	public static void main(String[] args) {
		
		int a, b;
		
		a = Einlesen.liesInt("Variable a> ");
		b = Einlesen.liesInt("Variable b> ");
		
		System.out.println("(1) a + b = " + (a+b));
		System.out.println("(2) a - b = " + (a-b));
		System.out.println("(3) a * b = " + (a*b));
		System.out.println("(4) a / b = " + (a/b));
		System.out.println("(5) a % b = " + (a%b));
	}
}
