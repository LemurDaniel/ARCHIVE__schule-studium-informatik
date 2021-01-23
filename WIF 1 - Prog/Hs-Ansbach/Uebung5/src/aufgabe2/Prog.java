package aufgabe2;

public class Prog {

	public static void main(String[] args) {        
		boolean b = true;        
		
		if (b == false)   // (b = false) <--- Wetzuweisung in if Anweisung und somit immer 'false'
			System.out.println("b ist false");        
		else           
			System.out.println("b ist true");           
		System.out.println("\nKontr.ausg.: b ist "+b);    
	} 
}
