package teil1;

public class Aufgabe4 {

	public static void main(String[] args) {
		
		float f = Float.MAX_VALUE;
		System.out.println( f + " * 10 = " +  f*10);
		System.out.println(-f + " * 10 = " + -f*10);

		float f2 = 2.245564e20f;
		System.out.println( f + " + " + f2 + " = " + ( f+f2) );
		System.out.println(-f + " - " + f2 + " = " + (-f-f2) );
		
		System.out.println();
		
		double d = Double.MAX_VALUE;
		System.out.println( d + " * 10 = " +  d*10);
		System.out.println(-d + " * 10 = " + -d*10);
		
		double d2 = 1.364876753e231;
		System.out.println( d + " + " + d2 + " = " + ( d+d2) );
		System.out.println(-d + " + " + d2 + " = " + (-d+d2) );

	}

}
