package entity;

public class Artikel {
	private int id, menge;
	private double preis;
	private String bezeichnung;
	
	public Artikel() {}
	
	public Artikel(int id, int menge, double preis, String bezeichnung) {
		this.id = id;
		this.menge = menge;
		this.preis = preis;
		this.bezeichnung = bezeichnung;
	}
	
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public int getMenge() {
		return menge;
	}
	public void setMenge(int menge) {
		this.menge = menge;
	}

	@Override
	public String toString() {
		return "Artikel [id=" + id + ", menge=" + menge + ", preis=" + preis + ", bezeichnung=" + bezeichnung + "]";
	}


	public void setId(int id) {
		this.id = id;
		
	}
}
