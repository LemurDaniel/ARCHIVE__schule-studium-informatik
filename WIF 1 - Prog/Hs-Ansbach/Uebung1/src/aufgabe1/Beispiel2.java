package aufgabe1;

/*
* Unsere erste Java-Anwendung * (in diesem Zusammenhang gleich ein mehrzeiliger
* Kommentar)
*/ 

//>> damit beginnt ein Zeilenkommentar
//Beispiel1 ist ein Klassenname 
//der Dateiname muss Beispiel1.java heißen 

public class Beispiel2 { 
	
	public static void main(String[] args) //Dies ist eine Operation 
	//bzw. Methode; es folgen mehrere Ausgabeanweisungen 
	{
		System.out.println("*************************************");
		System.out.println("*              Java                 *"); 
		System.out.println("* eine Einführung in die Grundlagen *"); 
		System.out.println("*            FH Ansbach             *");
		System.out.println("*************************************"); 
		System.out.print("Hallo");
		System.out.println(" world");
		
		int zahl = 2;
		int zahl2 = 3;
		System.out.println(zahl + " meine erste Variable " + zahl2);
		
		double zahl3 = 2.5;
		System.out.println(zahl3);
	} 
}