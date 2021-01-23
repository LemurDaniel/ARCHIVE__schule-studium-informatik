package aufgabe8;

public class Start {
	
	public static void main (String[] args) {
		Werkzeug test = new Werkzeug();
		
		System.out.println("Variante 1:");
		System.out.println(test.plus(2, 3));
		
		System.out.println("Variante 2:");
		System.out.println(test.plus(2.5, 3.6));
		
		System.out.println("Variante 3:");
		System.out.println(test.plus("aa", "bc"));
	}
}
