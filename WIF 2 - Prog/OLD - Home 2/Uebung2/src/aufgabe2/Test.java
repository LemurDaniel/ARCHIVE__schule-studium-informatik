package aufgabe2;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GebrauchtAuto t = new GebrauchtAuto(40_000.99, 1998, "k2", 41_000);
		System.out.printf("Preis: %,.2f €\n", t.getPreis());
		
		System.out.printf("(Preis: %,.8f €)", t.getPreis());

	}

}
