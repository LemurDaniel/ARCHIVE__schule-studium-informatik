package aufgabe1;

public class Test {

	public static void main(String[] args) {

		Bruch b1 = new Bruch(100, 25);
		Bruch b2 = new Bruch(1, 8);
		Bruch b3 = new Bruch(10, 25);
		
		System.out.println(b1.toString());
		System.out.println( b1.kuerze().toString() );
		System.out.println("------------------");
		
		System.out.println(b3.toString());
		System.out.println( b3.kuerze().toString() );
		System.out.println("------------------");
		
		System.out.println(b3.toString());
		System.out.println( b3.multipliziere(2).toString() );
		System.out.println( b3.multipliziere(2).kuerze().toString() );
		System.out.println("------------------");
		
		System.out.println( b3.toString());
		System.out.println( b3.multipliziere(b2).toString() );
		System.out.println( b3.multipliziere(b2).kuerze().toString() );
		System.out.println("------------------");
		
		

	}

}
