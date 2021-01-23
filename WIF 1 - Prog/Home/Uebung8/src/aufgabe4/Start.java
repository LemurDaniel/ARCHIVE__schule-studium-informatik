package aufgabe4;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Artikel[] waren = new Artikel[3];
		waren[0] = new Artikel("Diashow", "Erlaubt Dia-Show auf HTML-Seite", 29.90f);
		waren[1] = new Artikel("Bildbeschriftung", "Erlaubt Beschriftung von Bildern", 99.90f);
		waren[2] = new Artikel("100 Piktos", "100 piktogramme für HTML-Seite", 54.50f);
		
		
		System.out.format("\n%9s    %-20s%-10s%-40s%12s","Artikelnr","Bezeichnung", "Sprache","Beschreibung","Verkaufspreis");
		for(Artikel art:waren) {
			System.out.format("\n%9d    %-20s%-10s%-40s%12.2f€", art.getNummer(), art.getBezeichnung(), art.getProgrammiersprache(), art.getBeschreibung(), art.getVerkauspreis());;
		}
		
	}

}
