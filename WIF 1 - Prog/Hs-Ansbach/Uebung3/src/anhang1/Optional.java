package anhang1;

import javapack.Einlesen;

public class Optional {
	
	public static void main (String[] args) {
		
		double a, b, c;
		double dk; // diskriminante
		
		a = Einlesen.liesDouble("Variable a> ");
		b = Einlesen.liesDouble("Variable b> ");
		c = Einlesen.liesDouble("Variable c> ");

//		a = 1;
//		b = 3;
//		c = 2;
//		
//		a = 1.3539206934;
//		b = 3.988677;
//		c = 0.437737;
		
		dk = b*b - 4*a*c;
		System.out.printf("Die Gleichung %+fx² %+fx %+f = 0\n", a, b, c);
		if(dk < 0) {
			System.out.println(" ist nicht lösbar");
		} else {
			System.out.println(" ist lösbar für: ");
			System.out.printf(" x1 = % f\n", (-b+Math.sqrt(dk))/2*a);
			System.out.printf(" x2 = % f\n", (-b-Math.sqrt(dk))/2*a);
		}
		
	}
		
}
