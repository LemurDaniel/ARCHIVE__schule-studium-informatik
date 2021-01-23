package aufgabe2;

public class Test {

	public static void main(String[] args) {
		
		FahrzeugCockpit fc[] = {new VW(), new BMW(), new Audi()};
		
		for(int i=0; i<fc.length; i++){
			fc[i].bewegeLenkrad();
			fc[i].gibGas();
			fc[i].bremse();
			System.out.println();
		}


	}

}
