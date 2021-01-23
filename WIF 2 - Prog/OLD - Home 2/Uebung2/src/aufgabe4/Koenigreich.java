package aufgabe4;

public class Koenigreich {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Einwohner e[] = new Einwohner[4];
//		e[0] = new Koenig();
//		e[1] = new Adel();
//		e[2] = new Bauer();
//		e[3] = new Leibeigener();
//		
//		e[1].setEinkommen(523);
//		e[2].setEinkommen(60);
//		e[3].setEinkommen(60);
//		
//		int einkommen = 0;
//		for(int i=1; i<e.length; i++) {
//			einkommen += e[i].steuer();
//		}
//		e[0].setEinkommen(einkommen);
//		
//		System.out.println("Klasse		-	Einkommen	-	VersteuertesEinkommen	-	Steuer");
//		System.out.println();
//		for(int i=0; i<e.length; i++) {
//			System.out.printf("%-8s	-	%-8d	-	%-16d	-	%-8d \n", e[i].getClass().getSimpleName(), e[i].einkommen, e[i].zuVersteuerndesEinkommen(), e[i].steuer());
//		}
		
		
		System.out.println("Klasse		-	Einkommen	-	VersteuertesEinkommen	-	Steuer");
		steuerbescheid(new Koenig(), 20);
		steuerbescheid(new Adel(), 20);
		steuerbescheid(new Bauer(), 20);
		steuerbescheid(new Leibeigener(), 20);	
	
	}

	
	
	public static void steuerbescheid(Einwohner einwohner, int einkommen) {
		einwohner.setEinkommen(einkommen);
		System.out.printf("%-8s	-	%-8d	-	%-16d	-	%-8d \n", einwohner.getClass().getSimpleName(), einwohner.einkommen, einwohner.zuVersteuerndesEinkommen(), einwohner.steuer());
	}
}
