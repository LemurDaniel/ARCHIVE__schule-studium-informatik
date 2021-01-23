package aufgabe7;

import aufgabe7.benziner.Audi;
import aufgabe7.benziner.BMW;
import aufgabe7.benziner.VW;
import aufgabe7.emobil.Tesla;

public class Test {

	public static void main(String[] args) {
		
		FahrzeugCockpit fc[] = {new VW(), new BMW(), new Audi(), new Tesla()};
		
		for(int i=0; i<fc.length; i++){
			fc[i].bewegeLenkrad();
			fc[i].gibGas();
			fc[i].bremse();
			fc[i].hatEDrive();
			fc[i].metaDaten();
			System.out.println();
		}


	}

}
