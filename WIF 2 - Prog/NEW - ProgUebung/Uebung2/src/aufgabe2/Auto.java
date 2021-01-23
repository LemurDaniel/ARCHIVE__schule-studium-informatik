package aufgabe2;

public class Auto {

	protected double neuwagenpreis;
	protected int baujahr;
	protected String modell;
	
	public Auto(double neuwagenpreis, int baujahr, String modell) {
		this.neuwagenpreis = neuwagenpreis;
		this.baujahr = baujahr;
		this.modell = modell;
	}
	
	public double getPreis() {
		return neuwagenpreis;
	}
}
