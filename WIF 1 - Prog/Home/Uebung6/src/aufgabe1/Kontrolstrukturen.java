package aufgabe1;

public class Kontrolstrukturen {

	public static void main(String[] args) {
		durchlaufeKontrollstrukturen(true, 1);
		durchlaufeKontrollstrukturen(false, 4);

	}
	
	public static void durchlaufeKontrollstrukturen(boolean Bedingung, int Wert){
//		 int i;
//		 i = 1;
//		 while(i < 10)
//		 {
//		 System.out.println(i);
//		 i = i + 1;
//		 }
//	
//		 while(Bedingung == true)
//		 {
//		 System.out.println("Bedingung ist wahr");
//		 break; // Beendet while-Schleife
//		 }
//	
//		 if(Wert == 0)
//		 {
//		 System.out.println("Wert ist 0");
//		 }
//		 else if(Wert == 1)
//		 {
//		 System.out.println("Wert ist 1");
//		 }
//		 else if(Wert == 2)
//		 {
//		 System.out.println("Wert ist 2");
//		 }
//		 else
//		 {
//		 System.out.println("Wert ist weder 0, noch 1, noch 2");
//		 }
//		}
			
		for(int i=1; i<10; i++) {
			System.out.println(i);
		}
		
		if(Bedingung)	System.out.println("Bedingung ist wahr");
		
		switch(Wert) {
		case 0: case 1: case 2: System.out.println("Wert ist" + Wert); break;
		default: System.out.println("Wert ist weder 0, noch 1, noch 2");
		}
	}

}
