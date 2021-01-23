package aufgabe3;

public class Test {

	public static void main(String[] args) {
		Datum d1 = new Datum(-1, 1, 1);
		d1.ausgabe(Datum.ISO_Norm);
		d1.ausgabe(Datum.DIN_Norm);
		d1.ausgabe(Datum.optional);
	
		System.out.println("-----------------------");
		Datum d2 = new Datum(1998, 1, 2);
		d2.ausgabe(Datum.ISO_Norm);
		d2.ausgabe(Datum.DIN_Norm);
		d2.ausgabe(Datum.optional);
		
		System.out.println("-----------------------");
		d2 = new Datum("2.1.1998");
		d2.ausgabe(Datum.ISO_Norm);
		d2.ausgabe(Datum.DIN_Norm);
		d2.ausgabe(Datum.optional);
		
		System.out.println("-----------------------");
		d2 = new Datum("1 Juni, 1998");
		d2.ausgabe(Datum.ISO_Norm);
		d2.ausgabe(Datum.DIN_Norm);
		d2.ausgabe(Datum.optional);
	}

}
