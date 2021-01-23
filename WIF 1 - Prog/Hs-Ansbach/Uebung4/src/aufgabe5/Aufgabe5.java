package aufgabe5;

import javapack.Einlesen;

public class Aufgabe5 {

	public static void main(String[] args) {

		int ganzzahl, quersumme = 0;
		char[] c;
		String s_umgedreht = "";
				
		ganzzahl = Einlesen.liesInt("Ganzzahl> ");
		c = String.valueOf(ganzzahl).toCharArray();
		
		for(int i = 0; i < c.length; i++) {
			System.out.println(i+1 + ". ---> " + c[i]);									
			quersumme += Integer.parseInt( c[i]+"" );	
			s_umgedreht = c[i] + s_umgedreht;		
		}
			System.out.println("Quersumme = " + quersumme);
			System.out.println("Ganzzahl:	 " + ganzzahl);
			System.out.println("Umgedrehte Zahl: " + s_umgedreht);
			
			int i = 1234;
			System.out.println(i/100);
			
	}

}
