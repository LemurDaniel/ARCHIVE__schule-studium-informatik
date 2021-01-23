package aufgabe5;

public class Aufgabe5 {

	public static void main(String[] args) {

		float br = 100;
		float ps = 19;
		
		float ud = br*ps/(ps+100);
		System.out.printf("Die Umsatzsteuer beträgt %.2f\n", ud);
		System.out.printf("Der Nettopreis beträgt %.2f\n", (br-ud));

	}

}
