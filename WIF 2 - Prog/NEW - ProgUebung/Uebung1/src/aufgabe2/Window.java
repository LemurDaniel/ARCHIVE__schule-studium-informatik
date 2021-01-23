package aufgabe2;

public class Window {

	//
	private class Punkt{
		double x, y;
		
		Punkt(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
	
	//
	private Punkt eckPunkt;
	private double breite, hoehe;
	
	//
	Window(double x, double y, double breite, double hoehe){
		eckPunkt = new Punkt(x, y);
		this. breite = breite;
		this.hoehe = hoehe;
	}
	
	Window(){
		this(0, 0, 20, 10);
	}
	
	//
	public void vergroeﬂern(int faktor) {
		breite *= faktor;
		hoehe *= faktor;
	}
	
	public void setBreite(double w) {
		breite = w;
	}
	
	public void setHoehe(double h) {
		hoehe = h;
	}
	
	public Punkt addPunkt(double x, double y) {
		return new Punkt(x, y);
	}
	
	public Punkt getLinkerObererPunkt(){
		return eckPunkt;
	}
	
	public Punkt getRechterUntererPunkt() {
		return new Punkt(breite, hoehe);
	}
}
