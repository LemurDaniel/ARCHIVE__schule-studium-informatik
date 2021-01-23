package aufgabe9;

import java.util.ArrayList;

public class Kunde {
	
	private String name, adresse;
	private int auftragssumme;
	private ArrayList<Auftrag> auftraege = new ArrayList<Auftrag>();
	
	Kunde(String name, String adresse){
		this.name = name;
		this.adresse = adresse;
	}
	
	
	public void neuerAuftrag(Auftrag auftrag) {
		auftraege.add(auftrag);
	}
	
	public void deleteAuftrag(Auftrag auftrag) {
		if(auftraege.contains(auftrag)) {
			auftraege.remove(auftrag);
		}
	}
	
	public void erstelleRechnungen() {
		if(auftraege.isEmpty()) {
			System.out.println("Keine Aufträge vorhanden");
			return;
		}
		
		Auftrag temp = null;
		System.out.println();
		System.out.println(auftraege.get(0).rechnungsKopf());
		for(Auftrag a:auftraege) {
			if( a.getArt().equals(auftraege.get(0).getArt()) )
				System.out.println(a.rechnung());
			else
				temp = a;
		}
		
		if(temp != null) {
			System.out.println();
			System.out.println(temp.rechnungsKopf());
			for(Auftrag a:auftraege) {
				if( a.getArt().equals(temp.getArt()) )
					System.out.println(a.rechnung());
			}
		}
	}
	
	public Auftrag getAuftrag() {
		return auftraege.get(0);
	}
	
	public ArrayList<Auftrag> getAuftraege() {
		return auftraege;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAdresse() {
		return adresse;
	}
}
