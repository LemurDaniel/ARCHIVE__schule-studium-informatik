package aufgabe4;

import javapack.Einlesen;

public class Aufgabe4 {

	public static void main(String[] args) {
		
		char c;
		int limit;
		
		c = Einlesen.liesChar("Charakter> ");
		limit = Einlesen.liesInt("Anzahl folgender Charakter> ");
			
		for(int i = 0; i < limit; i++) {
			System.out.println(i+1 + ". ---> " + (char)++c);
		}		

	}

}
