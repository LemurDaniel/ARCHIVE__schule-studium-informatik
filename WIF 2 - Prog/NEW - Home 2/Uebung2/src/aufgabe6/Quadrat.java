package aufgabe6;

public class Quadrat extends GeometrischeFigur {

	private double seite;
	
	Quadrat(double seite){
		this.seite = seite;
	}
	
	@Override
	protected double berechneFlaeche() {
		return seite*seite;
	}

	@Override
	protected double berechneUmfang() {
		return seite*4;
	}

}
