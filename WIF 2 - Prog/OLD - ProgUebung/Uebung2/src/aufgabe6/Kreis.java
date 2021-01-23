package aufgabe6;

public class Kreis extends GeometrischeFigur {

	private double radius;
	
	Kreis(double radius){
		this.radius = radius;
	}
	
	@Override
	protected double berechneFlaeche() {
		return radius*radius*Math.PI;
	}

	@Override
	protected double berechneUmfang() {
		return 2*radius*Math.PI;
	}

}
