package aufgabe5;

public class Kunde {
	
	private class Konto {
		private int kontonummer;
		private float kontostand = 0f;
		
		Konto(int kontonummer, float kontostand){
			this.kontonummer = kontonummer;
			this.kontostand = kontostand;
		}
		
		public boolean UeberweisungPruefung(float uberweisungsbetrag) {
			return uberweisungsbetrag>0 && uberweisungsbetrag<=4000f && kontostand-uberweisungsbetrag > -500f;
		}
		
		public void ueberweisung(float uberweisungsbetrag) {
			if( UeberweisungPruefung(uberweisungsbetrag) )
				kontostand -= uberweisungsbetrag;
			else
				System.out.println("\nÜberweisung nicht möglich!");
		}
		
		public int getKontonummer() {
			return kontonummer;
		}
		
		public float getKontostand() {
			return kontostand;
		}
	}
	
	
	
	private String name;
	private Konto konto;
	
	Kunde(){
		this("Test");
	}
	
	Kunde(String name){
		this.name = name;
	}
	
	Kunde(String name, int kontonummer, float kontostand){
		this.name = name;
		this.konto = new Konto(kontonummer, kontostand);
	}
	
	public void gibAus() {
		System.out.println("\nKunde: "+name);
		System.out.println("Kontonummer: "+konto.getKontonummer());
		System.out.format("Kontostand: %.2f€\n", konto.getKontostand());
	}
	
	public void ueberweisung(float uberweisungsbetrag) {
		konto.ueberweisung(uberweisungsbetrag);
	}
	
	public void anlegenKonto(int kontonummer, int kontostand) {
		konto = new Konto(kontonummer, 2000f);
	}
		
	public String getName() {
		return name;
	}
}
