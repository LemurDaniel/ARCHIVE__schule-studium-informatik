package aufgabe7;

import Bla.Einlesen;

public class Fahrzeug {
	
	private float preis;  
	private String herstellerName; 
	 
	 public Fahrzeug() {   
		 System.out.println();   
		 System.out.print("Geben Sie den " + "Herstellernamen ein: ");   
		 herstellerName = Einlesen.liesString("Hesrtellername> ");   
		 System.out.print("Geben Sie den Preis ein: ");   
		 preis = Einlesen.liesFloat("Preis> ");  
	} 
	 
	 public void print() {   
		 System.out.println();   
		 System.out.println("Herstellername: " + herstellerName);  
		 System.out.println("Preis : " + preis); 
	}  
	 
	 public float getPreis() {
		 return preis;
	 }
}
