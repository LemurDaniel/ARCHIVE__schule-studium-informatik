package aufgabe9;

abstract class Auftrag {
	
	private static int auftraege = 0;
	private int	nummer = ++auftraege;
	private String datum, art, beschreibung, titel;
	private float mehrwertsteuer = 19.5f;
	private Kunde kunde;
	
	Auftrag(String titel, String datum, String art, Kunde kunde){
		this(titel, datum, art, "Keine Beschreibung vorhanden", kunde);
	}
	
	Auftrag(String titel, String datum, String art, String beschreibung, Kunde kunde){
		this.titel = titel;
		this.datum	= datum;
		this.art	= art;
		this.beschreibung = beschreibung;
		this.kunde = kunde;
	}
	
	
	public float getMehrwertsteuer() {
		return mehrwertsteuer;
	}
	
	public void setMehrwertsteuer(float mehrwertseuer) {
		this.mehrwertsteuer = mehrwertsteuer;
	}	
	
	public String rechnungsKopf() {
		return String.format("%-15s  %-15s  %-20s  %-20s  %-10s  %-15s  %-5s  ","Auftragsnummer", "Titel", "Name", "Adresse", "Datum", "Art", "Mwst.");
	}
	
	public String rechnung() {
		return String.format("%14d   %-15s  %-20s  %-20s  %-10s  %-15s  %-5.2f  ", nummer, titel, kunde.getName(), kunde.getAdresse(), datum, art, mehrwertsteuer);
	}
	
	public void erstelleRechnung() {
		System.out.println(rechnungsKopf());
		System.out.println(rechnung());
		System.out.println();
	};
	
	public String getBeschreibung() {
		return beschreibung;
	}
	
	public String getArt() {
		return art;
	}

}

