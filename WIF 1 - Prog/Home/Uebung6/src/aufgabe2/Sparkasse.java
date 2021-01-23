package aufgabe2;

public class Sparkasse {

	public static void main(String[] args) {
		float[] kapitale = { 100f, 250f, 750f };
		verzinse(kapitale);
		}
	
	public static void verzinse(float[] anfangskapitale) {
		for(float kapital:anfangskapitale) {
			System.out.format("Berechnung mit Anfangskapital: %.2f\n", kapital);
			verzinse(kapital);
			System.out.println();
		}
	}
	
	public static void verzinse(float anfangskapital) {
		System.out.println("Anfangskapital     Endkapital     Zinssatz");
		for(float zs=3.0f; zs<=10.0;zs+=0.5) {
			System.out.format("%-14.2f     %-10.2f     %-8.2f\n", anfangskapital, anfangskapital*(1+zs/100), zs);
		}
		System.out.println("E N D E Berechnung");
	}

}
