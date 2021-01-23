package aufgabe7;

import javapack.Einlesen;

public class Pkw extends Fahrzeug {

	private String fahrzeugtyp = "Pkw";
	private String modellBezeichnung;
	
	public Pkw() {
		super();
		System.out.println("Geben Sie die " + "Modellbezeichnung ein: ");  
		modellBezeichnung = Einlesen.liesString("Modellbezeichnung> "); 
	}
	
	public void print() {
		super.print();
		System.out.println("Fahrzeugtyp: " + fahrzeugtyp);
		System.out.println("Modellbezeichnung: " + modellBezeichnung);
	}
}
