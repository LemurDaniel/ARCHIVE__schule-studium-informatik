package aufgabe1;

class Kunde{
	
	public String Kundenname, Kundenadresse;
	public int Auftragssumme = 0;
	
	public Kunde(String Name) {
		Kundenname = Name;
	}
	
	
	public void SetAendernAdress(String Name, String Adresse) {
		Kundenname = Name;
	}
	
	public void setzeKundenname(String Name) {
		Kundenname = Name;
	}
	
	public void setzeKundenadresse(String Adresse) {
		Kundenadresse = Adresse;
	}
	
	public void setzeAuftragssumme(int Summe) {
		Auftragssumme = Summe;
	}
	
	public String gibKundenname() {
		return Kundenname;
	}
	
	public String gibKundenadresse() {
		return Kundenadresse;
	}
	
	public int holeAuftragssumme() {
		return Auftragssumme;
	}
		
}