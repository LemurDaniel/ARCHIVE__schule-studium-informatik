package aufgabe4;

public class While {

	public static void main(String[] args) {
		int i = 0;   
		
		// Ohne Verbesserung
		// W�rde unendlich lange laufen, da nach dem while ein Semikolon das Statement beendet.
		// Der Block nach while wird nicht ausgef�hrt und folglich �ndert sich der Wert der des 'i' nicht mehr.
		// While - Schleife immer true ---> Endlosschleife
		
		// Mit Verbesserung:
		// 100 mal
		
		//while (i < 100); Fehler1: ein semikolon nach while! ---> Endlosschleife
		while (i < 100) {
			i++;    
			if(i<100)System.out.print(i + ", ");    
			else System.out.print(i);   
		}  
	}

	
}
