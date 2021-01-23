package aufgabe3;

public class Prog {

	public static void main(String[] args) {
		 char bst = 'a';        
		 byte nr = 0;
		 
		 switch (bst) {           
		 case 'a': nr = 1; break;         
		 case 'b': nr = 2; break;          
		 case 'c': nr = 3; break;
		 default: System.out.println("Ungültige Eingabe");
		 }        
		 System.out.println("Zu "+ bst + " gehoert die Nummer "+nr); 

	}

}
