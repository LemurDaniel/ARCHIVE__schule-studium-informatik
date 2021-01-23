package aufgabe4;

import javapack.Einlesen;

public class Test {

	public static void main(String[] args) {
	
		Fahrkartenautomat f1 = new Fahrkartenautomat();
		
		while(true) {
			System.out.println();
			f1.eingezahlt();
			System.out.println();
			System.out.println("Fahrkarte lösen (f)");
			System.out.println("Geld eingeben   (g)");
			System.out.println("stornieren      (s)");
			System.out.println("Status anzeigen (a)");
			System.out.println("Fahrpreis       (p)");
			System.out.println("Ende            (e)");
			
			System.out.println("");
			char c = Einlesen.liesChar("Eingabe > ");
			System.out.println("");
		
			switch(c) {
			case 'f':
				f1.fahrkarteLoesen();	break;
			
			case 'g':
				f1.geldEingeben(Einlesen.liesDouble("Einzahlung> "));	break;
			
			case 's':
				System.out.println("*** Ihr Fahrkarte wurde storniert ***");
				f1.rueckgabe(); 
				break;
			
			case 'a':
				if (f1.status())
					System.out.println("	Sie können jetzt ihre Fahrkarte lösen");
				else {
					System.out.println("	Sie müssen noch Geld einzahlen");
					f1.fehlendesGeld();
				}
				break;
			
			case 'p':
				f1.getPreis(); break;
			
			case 'e':
				System.exit(0);
			
				default:
					System.out.println("Falsche Eingabe");
				
			}	
		}


	}
}