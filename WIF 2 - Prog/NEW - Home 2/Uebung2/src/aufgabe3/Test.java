package aufgabe3;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MedienKunstwerk mk = new MedienKunstwerk();
		CD cd = new CD();
		DVD dvd = new DVD(); 
		
		mk = cd;
		mk = dvd;
		
		cd.leseDaten();
		System.out.println();
		dvd.leseDaten();
	}

}
