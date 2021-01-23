package teil1;

public class Aufgabe5 {

	public static void main(String[] args) {
		
		char c = 'A' + 1;
		int ci = 'A' + 1;
		System.out.println( c + " " + ci);
		
		ci = c + 20;
		c = (char)ci;
		System.out.println( c + " " + ci);
	
	
	}

}
