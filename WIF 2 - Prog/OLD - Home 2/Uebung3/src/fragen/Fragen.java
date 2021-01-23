package fragen;

public class Fragen {
	
//	Konstruktoraufrufe
	
//	1. Frage
//	Welche der untenstehenden Aussagen treffen nicht zu?
	
//	a) Der Aufruf super() muss im Konstruktor immer an erster Stelle stehen.
//	b) Existieren nur die impliziten Standardkonstruktoren, so wird automatisch im
//		Standardkonstruktor der Subklasse der Konstruktor der Superklasse mit super() aufgerufen.
//	c) Es existiert immer ein Standardkonstruktor.
//	d) Mit this() wird der Standardkonstruktor der eigenen Klasse aufgerufen.
//	e) Mit super.laufen() wird die Methode laufen() der Superklasse aufgerufen.
//	f) Keine dieser Möglichkeiten.
//
//	Trifft zu: A, B, D, E
//	Treffen nicht zu: C, F
	
	
//
//	2. Frage
//	Welche der untenstehenden Aussagen trifft zu?
	
//	a) Ein fehlender Standardkonstruktor kann nie zu Kompilierfehlern führen.
//	b) Der Aufruf super() muss im Konstruktor immer an letzter Stelle stehen.
//	c) Existieren nur die impliziten Standardkonstruktoren, so wird automatisch im
//		Standardkonstruktor der Subklasse der Konstruktor der Superklasse mit this() aufgerufen.
//	d) Es existiert immer ein Standardkonstruktor.
//	e) Mit this() wird der Standardkonstruktor der Superklasse aufgerufen.
//	f) Keine dieser Möglichkeiten.
//
//	Trifft zu: F
//	Treffen nicht zu: A, B, C, D, E
	
	
	

//	3. Frage
//	Welches Ergebnis erhält man, wenn man folgendes Programm kompiliert und startet?
	
//	class Vater{
//		Vater(){
//			System.out.println(“Vater“);
//		}
//	}
	
//	class Tochter extends Vater{
//		Tochter(){
//			System.out.println(“Tochter“);
//		}
	
//		public static void main(String[ ] args){
//			Tochter tochter = new Tochter();
//		}
//	}
	
//	
//	Ausgabe: Vater
//			 Tochter
	
	
	
	
	
//	4. Frage
//	
//	Welches Ergebnis erhält man, wenn man folgendes Programm kompiliert und startet?
//			
//	class Vater{
//		Vater(){
//			System.out.println(“Vater“);
//		}
//	}
//	
//	class Tochter {
//		Tochter(){
//			System.out.println(“Tochter“);
//		}	
//		
//		public static void main(String[ ] args){
//			Tochter tochter = new Tochter();	
//		}
//		
//
//	Ausgabe: Tochter;
// 
	
	
	
	
//	5. Frage
//	
//	Welches Ergebnis erhält man, wenn man folgendes Programm kompiliert und startet?
//	class Vater{
//		Vater(){
//			System.out.println(“Vater“);
//		}
//	}
//	
//	class Tochter extends Vater{
//		Tochter(){
//			System.out.println(“Tochter“);
//		}
//		
//		public static void main(String[ ] args){
//			Tochter tochter = new Tochter();
//			super();
//		}
//	}
//	
//	Kompilierfehler --> Super has to be first statement in a constructor
	
	
	
	
	
//	6. Frage
//	Welches Ergebnis erhält man, wenn man folgendes Programm kompiliert und startet?
//			
//	class Vater{
//		Vater(){
//			System.out.println(“Vater“);
//		}
//	}
//	
//	class Tochter extends Vater{
//		Tochter(){
//			super()
//			System.out.println(“Tochter“);
//		}
//		
//		public static void main(String[ ] args){
//			Tochter tochter = new Tochter();
//		}
//	}
//	
// Ausgabe: Vater
//			Tochter
	
	
	
	
	
//	7. Frage
//	Welches Ergebnis erhält man, wenn man folgendes Programm kompiliert und startet?
//	
//	class Vater{
//		Vater(){
//			System.out.println(“Vater“);
//		}
//	}
//	
//	class Tochter extends Vater{
	
//		Tochter(){
//			this(“Tochter“);
//			System.out.println(“Tochter“);
//		}
//		
//		Tochter(String wort){
//			System.out.println(“Ich bin “ + wort);
//		}
//		
//		public static void main(String[ ] args){
//			Tochter tochter = new Tochter();
//		}
//	}
//
//	Ausgabe: Vater
//			 Ich bin Tochter
//			 Tochter
	
}
