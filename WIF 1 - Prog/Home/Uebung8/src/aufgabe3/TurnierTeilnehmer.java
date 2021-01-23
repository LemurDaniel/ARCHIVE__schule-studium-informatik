package aufgabe3;

public class TurnierTeilnehmer {
	
	private int startnummer, turnierdisziplin;
	private String reiterName, pferdeName, pferdeRasse = "QH";
	private char pferdeGeschlecht;
	private int pferdeGeburtsjahr;
	
	TurnierTeilnehmer(int startnummer, String reiterName, String pferdeName, char pferdeGeschlecht){
		this.startnummer = startnummer;
		this.reiterName = reiterName;
		this.pferdeName = pferdeName;
		this.pferdeGeschlecht = pferdeGeschlecht;
	}
	
	public String selfie() {
		String selfie = "\nStartnummer: "+startnummer;
		selfie += "\nTurnierdisziplin: "+turnierdisziplin;
		
		selfie += "\nReiter: ";
		selfie += "\n Name: "+reiterName;
		
		selfie += "\nPferd: ";
		selfie += "\n Name: "+pferdeName;
		selfie += "\n Rasse: "+pferdeRasse;
		selfie += "\n Geschlecht: "+pferdeGeschlecht;
		selfie += "\n Geburtsjahr: "+pferdeGeburtsjahr;
		return selfie;
	}
	
	
	public String getReiterName() {
		return reiterName;
	}
	
	public String getPferdeName() {
		return pferdeName;
	}
	
	public String getPferdeRasse() {
		return pferdeRasse;
	}
	
	public char pferdeGeschlecht() {
		return pferdeGeschlecht;
	}
	
	public int getPferdeGeburtsjahr() {
		return pferdeGeburtsjahr;
	}
	
	public int getTurnierdisziplin() {
		return turnierdisziplin;
	}
	
	
	public void setPferdeRasse(String pferdeRasse) {
		this.pferdeRasse = pferdeRasse;
	}
	
	public void setPferdeGeburtsjahr(int pferdeGeburtsjahr) {
		this.pferdeGeburtsjahr = pferdeGeburtsjahr;
	}
	
	public void setTurnierdisziplin(int turnierdisziplin) {
		if( String.valueOf(turnierdisziplin).length() == 3)
			this.turnierdisziplin = turnierdisziplin;
		else
			System.out.println("Ungültige Eingabe: Turnierdisziplin ist ein ganze Dreistellige Zahl");
	}
}
