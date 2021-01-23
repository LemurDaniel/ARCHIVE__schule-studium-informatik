package aufgabe9;

public class Sache extends Auftrag{

	private String produkt;
	private int menge;
	private float preis, gesamt;
	
	Sache(String titel, String datum, String beschreibung, Kunde kunde, String produkt, int menge, float preis){
		super(titel, datum, "Sachleistung", beschreibung, kunde);
		this.produkt = produkt;
		this.menge = menge;
		this.preis = preis;
		gesamt = (preis*menge)*(1+this.getMehrwertsteuer()/100);
	}
	
	public String rechnungsKopf() {
		return super.rechnungsKopf() + String.format("%-10s %-5s%6$2s %-10s %-10s %-40s", "Produkt", "Menge", "Preis", "Gesamt", "Beschreibung", "");
	}
	
	public String rechnung() {
		return super.rechnung() + String.format("%-10s %5d%6$2s %-10.2f %-10.2f %-40s", produkt, menge, preis, gesamt, this.getBeschreibung(),"");
	}
		
}
