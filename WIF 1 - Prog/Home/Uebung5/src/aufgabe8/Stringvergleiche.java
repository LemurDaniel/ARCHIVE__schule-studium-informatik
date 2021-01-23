package aufgabe8;

import javapack.Einlesen;

public class Stringvergleiche {

	public static void main(String[] args) {
		
		String s1, s2, s1s2;
		s1 = Einlesen.liesString("String1> ");
		s2 = Einlesen.liesString("String2> ");
		
		System.out.print( "die Strings sind " + (s1.length()==s2.length() ? "gleich lang ":"unterschiedlich lang ") );
		System.out.println( (s1.equals(s2) ? "mit gleichem Inhalt":"mit unterschiedlichem Inhalt") );
		
		s1s2 = s1 + s2;	
		for(int i = 0; i < s1s2.length(); i++) {
			System.out.print( s1s2.charAt(i) + " ");
		}
	}

}
