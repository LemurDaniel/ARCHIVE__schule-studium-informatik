package aufgabe6;

public class TestBerechnung {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Kreis kreisRef = new Kreis (5); 
		Quadrat quadratRef = new Quadrat (10); 
		kreisRef.print(); 
		quadratRef.print();
		
		System.out.println("----------");
		kreisRef = new Kreis(1/(2*Math.PI));
		kreisRef.print();
		
		System.out.println("----------");
		kreisRef = new Kreis( 1/Math.sqrt(Math.PI) );
		kreisRef.print();
		
		
		// Besser
		System.out.println("--------------");
		GeometrischeFigur f[] = {kreisRef, quadratRef};
		for(int i=0; i<f.length; i++) {
			f[i].print();
		}
	}

}
