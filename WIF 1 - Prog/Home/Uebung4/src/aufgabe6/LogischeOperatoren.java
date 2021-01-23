package aufgabe6;

import javapack.Einlesen;

public class LogischeOperatoren {

	public static void main(String[] args) {
		
		boolean i, j;
		
		i = Einlesen.liesBoolean("Booblean i> ");
		j = Einlesen.liesBoolean("Boolean j> ");
		
		System.out.format(" %b && %b ---> %b\n", i, j, i&&j);
		System.out.format(" %b || %b ---> %b\n", i, j, i||j);
		System.out.format(" %b ^  %b ---> %b\n", i, j, i^j);
		System.out.format("!%b ---> %b\n", i, !i);
		
	}

}
