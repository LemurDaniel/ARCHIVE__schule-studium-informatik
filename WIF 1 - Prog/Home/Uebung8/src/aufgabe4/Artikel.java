package aufgabe4;

public class Artikel {
	
	private static int artikelNummern = 1000;
	public final int nummer = artikelNummern++;
	private String bezeichnung, programmiersprache = "Java", beschreibung;
	private float verkaufspreis = 20f;
	
	Artikel(String bezeichnung){
		this.bezeichnung = bezeichnung;
	}
	
	Artikel(String bezeichnung, String beschreibung, float verkaufspreis){
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
		this.verkaufspreis = verkaufspreis;
	}
	
	
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	public void setProgrammiersprache(String programmiersprache) {
		this.programmiersprache = programmiersprache;
	}
	
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public void setVerkaufspreis(int verkaufspreis) {
		this.verkaufspreis = verkaufspreis;
	}
	
	
	
	public int getNummer() {
		return nummer;
	}
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	
	public String getProgrammiersprache() {
		return programmiersprache;
	}
	
	public String getBeschreibung() {
		return beschreibung;
	}
	
	public float getVerkauspreis() {
		return verkaufspreis;
	}
	
}
