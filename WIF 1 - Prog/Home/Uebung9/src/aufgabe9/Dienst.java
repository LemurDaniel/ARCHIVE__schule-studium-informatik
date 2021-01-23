package aufgabe9;

public class Dienst extends Auftrag{

	private String kontakt;
	private int dauer;
	private float stundensatz, gesamt;
	
	Dienst(String titel, String datum, String beschreibung, Kunde kunde, String kontakt, int dauer, float stundensatz){
		super(titel, datum, "Dienstleistung", beschreibung, kunde);
		this.kontakt = kontakt;
		this.dauer = dauer;
		this.stundensatz = stundensatz;
		gesamt = (dauer*stundensatz)*(1+this.getMehrwertsteuer()/100);
	}
	
	
	public String rechnungsKopf() {
		return super.rechnungsKopf() + String.format("%-10s %-5s%6$2s %-10s %-10s %-40s", "Kontakt", "Dauer", "St.satz", "Gesamt", "Beschreibung", "");
	}
	
	public String rechnung() {
		return super.rechnung() + String.format("%-10s %5d%6$2s %-10.2f %-10.2f %-40s", kontakt, dauer, stundensatz, gesamt, this.getBeschreibung(),"");
	}

}
