package Buchungssystem;

public class Buchung {
		public static final int SOLL = 0;
		public static final int HABEN = 1;
	
		int typ;
		Konto konto;
		public int betrag;
}
