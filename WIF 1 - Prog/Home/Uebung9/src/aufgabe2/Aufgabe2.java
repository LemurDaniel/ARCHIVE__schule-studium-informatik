package aufgabe2;

public class Aufgabe2 {
	
	//	Aktuelle Parameter sind  Werte, die beim aufrufen einer Methode an diese �bergeben werden.
	// 	Im unteren Beisspiel w�re dies die beim aufrufen der Methode addiere(), die 20 und die 10.

	//	Formale Paramter sind Bezeichner, die bei der Definition einer Methode angegeben werden. 
	//	Sie dienen als Platzhalter f�r aktueller Parameter, die beim Aufrufen der Methode �bergeben werden.
	//	Im unteren beisspiel w�re in der Metode addiere() die beiden Variablen a und b.
	
	//	Innerhalb einer aufgerufenen Methode werden formale Parameter verwendet werden. Diese sind, solange die Methode aktiv ist, an einen aktuellen Wert gebunden.
	//	Kehrt die Methode zum Aufrufer zur�ck, enthalten die formalen Parameter der Methode keine Wert mehr.
	
	//	Die Unterscheidung ist sinvoll, da formale Parameter nur als Platzhalter dienen f�r tats�chlichen Parameter.
	
	public static void main(String[] args) {
		addiere(10, 20);
	}
	
	public static int addiere(int a, int b) {
		return a + b;
	}
}


