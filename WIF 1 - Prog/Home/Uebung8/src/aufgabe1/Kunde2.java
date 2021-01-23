package aufgabe1;

public class Kunde2 {

	private String name, adresse;
	private int auftragssumme;
	
	Kunde2(String name){
		this.name = name;
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
	
	
	public String getName() {
		return name;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public int getAuftragssume() {
		return auftragssumme;
	}
}
