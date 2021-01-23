package aufgabe7;

public class Zufallszeichen {

	public static void main(String[] args) {
		
		char[] feld = new char[10];
		int summe = 0;
		
		System.out.print("Zufallszeichen: ");
		for (int i = 0; i < feld.length; i++) {
			feld[i] = (char)(Math.random()*26 + 97);
			System.out.print(feld[i] + (i==feld.length-1 ? "\n":", ") );
		}
		
		System.out.print("Codes mit Summe: ");
		for (int i = 0; i < feld.length; i++) {
			summe += feld[i];
			System.out.print((int)feld[i] + (i==feld.length-1 ? " = "+summe+"\n":" + ") );
		}

	}

}
