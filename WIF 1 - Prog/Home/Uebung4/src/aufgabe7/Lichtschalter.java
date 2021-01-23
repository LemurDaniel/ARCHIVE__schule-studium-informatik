package aufgabe7;

import javapack.Einlesen;

public class Lichtschalter {

	public static void main(String[] args) {
		
		boolean licht, strom, schalter;
		
		strom = Einlesen.liesBoolean("Strom> ");
		schalter = Einlesen.liesBoolean("Schalter> ");
		licht = strom && schalter;
		
		
		if (licht) { 
			System.out.println("  \\      ___      /");
			System.out.println("   \\    |___|    /	");
			System.out.println("    *   |   |   *	");
			System.out.println("       /     \\		");
			System.out.println("---*  |       |  *---");
			System.out.println("      |       |		");
			System.out.println("---*   \\     /   *---");
			System.out.println("        \\   /		");
			System.out.println("    *    \\ /	*	");
			System.out.println("   /             \\ ");
			System.out.println("  /       *       \\ ");
			System.out.println("          |          ");
			System.out.println("          |          ");
			
		} else {
			System.out.println("        ___			");
			System.out.println("       |___|		");
			System.out.println("       |***|		");
			System.out.println("      /*****\\		");
			System.out.println("     |*******|		");
			System.out.println("     |*******|		");
			System.out.println("      \\*****/		");
			System.out.println("       \\***/		");
			System.out.println("        \\*/		");
		}

	}

}
