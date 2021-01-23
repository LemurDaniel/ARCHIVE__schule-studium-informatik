package aufgabe2;

public class Kunde {

	private String name, adresse, ansprechpartner;
	private int auftragssumme;
	
	Kunde(String name){
		this.name = name;
	}
	
	Kunde(String name, String adresse){
		this.name = name;
		this.adresse = adresse;
	}
	
	
	public void aendereAdresse(String name, String adresse) {
		setName(name);
		setAdresse(adresse);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public void setAuftragssumme(int auftragssumme) {
		this.auftragssumme = auftragssumme;
	}
	
	public void setAnsprechpartner(String ansprechpartner) {
		this.ansprechpartner = ansprechpartner;
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public String getAnsprechpartner() {
		return ansprechpartner;
	}
	
	public int getAuftragssume() {
		return auftragssumme;
	}
	
}
