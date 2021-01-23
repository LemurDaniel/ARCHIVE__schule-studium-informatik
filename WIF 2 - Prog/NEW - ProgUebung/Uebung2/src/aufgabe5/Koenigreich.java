package aufgabe5;

public class Koenigreich {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Einwohner[] e = new Einwohner[5];
		e[0] = new Einwohner();
		e[1] = new Adel();
		e[2] = new Koenig();
		e[3] = new Bauer();
		e[4] = new Leibeigener();
		
		e[0].setEinkommen(20);
		e[1].setEinkommen(512);
		e[3].setEinkommen(20);
		e[4].setEinkommen(13);
		
		
		
		int st = 0;
		for(int i=0; i<e.length; i++) {
			st += e[i].steuer();
		}
		e[2].setEinkommen(st);
		
		System.out.println("Einwohner	-	Einkommen	-	Versteurtes Einkommen	-	Steuern");
		System.out.println();
		for(int i=0; i<e.length; i++) {
			System.out.printf("%-8s	-	%-8d	-	%-16d	-	%-8d\n", e[i].getClass().getSimpleName(), e[i].einkommen, e[i].zuVersteuerndesEinkommen(), e[i].steuer());
		}
	}

}
