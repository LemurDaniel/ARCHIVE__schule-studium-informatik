package aufgabe6;

public class Kunde {

	private String name, adresse;

	public Kunde() {
	}
	
	public Kunde(String name, String adresse) {
		this.name = name;
		this.adresse = adresse;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
}
