package aufgabe1;

public class Test {

	public static void main(String[] args) {

		Bruch b1 = new Bruch(100, 25);
		Bruch b2 = new Bruch(1, 8);
		Bruch b3 = new Bruch(10, 25);
		
		System.out.println(b1);
		System.out.println(b1.kuerze());
		System.out.println("------------------");
		
		System.out.println(b3);
		System.out.println(b3.kuerze());
		System.out.println("------------------");
		
		System.out.println(b3);
		System.out.println( b3.multipliziere(2));
		System.out.println( b3.multipliziere(2).kuerze());
		System.out.println("------------------");
		
		System.out.println( b3);
		System.out.println( b3.multipliziere(b2));
		System.out.println( b3.multipliziere(b2).kuerze());
		System.out.println("------------------");
		
		

	}

}
