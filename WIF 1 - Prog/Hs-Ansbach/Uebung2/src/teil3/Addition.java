package teil3;

public class Addition {
	
	public static void main (String[] args) {
		
		System.out.println("Dieses Programm addiert drei Zahlen."); // 1.F: Gänsefüßchen fehlen + 2.F: kein Semikolon
		int x = 3; 
		int y = 5; // 3.F: kein Datentyp spezifiziert
		double z = 1.5; // 4.F: falscher Datentyp
		System.out.println("Die Summe von " + x + ", " + y + " und " + z + " ist: "); // 5.F: '+' vergessen
		System.out.println(x+y+z); // 6.F: Semikolon fehlt 
		
	}
}
