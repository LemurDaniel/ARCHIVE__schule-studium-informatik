package aufgabe9;

public class KundenUndAuftragsverwaltung {

	public static void main(String[] args) {
		Kunde[] kunden = new Kunde[2];
		
		kunden[0] = new Kunde( "Alfred J. Kwak", "Holzschuh 1" );
		for(int i=0; i<20; i++) {
			kunden[0].neuerAuftrag( new Dienst("Gartenarbeit", "3.10.1990", "Umgraben des Gartens", kunden[0], "Henk", 20, 25.25f) );
			kunden[0].neuerAuftrag( new Sache("Einkauf", "1.12.2015", "bla", kunden[0], "Krügen", 1, 75.50f) );
		}
		kunden[0].erstelleRechnungen();
	}

}
