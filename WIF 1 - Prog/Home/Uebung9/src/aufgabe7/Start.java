package aufgabe7;

import javapack.Einlesen;

public class Start {

	public static void main (String[] args) {
		Tank tank = new Tank(0, 1000);
		
		do {
			char eingabe = Einlesen.liesChar("Anzeigen(a), Bedienen(b), ende(e) \n --> ");
			System.out.println();
			
			switch(eingabe) {
			case 'e':
				System.exit(0);
			case 'a':
				System.out.format(">> Iststand:  %5d\n", tank.getIst());
				System.out.format(">> Kapazität: %5d\n", tank.getSoll());
				break;
			case 'b':
				int delta = Einlesen.liesInt(">> Menge? \n --> ");
				if( tank.getIst()+delta > tank.getSoll() ) {
					System.out.println(">> Füllmenge überschritten ");
					System.out.println(">> Überschussmenge: "+(tank.getIst()+delta-tank.getSoll()) );
				}else if( tank.getIst()+delta < 0) {
					System.out.println(">> Füllmenge unterschritten ");
					System.out.println(">> Fehlmenge: "+(tank.getIst()+delta) );
				}else
					tank.aendere(delta);
			}
			Einlesen.liesChar("");
			
		}while(true);
	
	}
}
	
