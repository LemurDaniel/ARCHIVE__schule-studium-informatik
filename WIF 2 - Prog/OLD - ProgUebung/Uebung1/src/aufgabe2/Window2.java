package aufgabe2;

public class Window2 {
	
	//
	private class Punkt{
		double x, y;
		
		Punkt(double x, double y){
			this.x = x;
			this.y = y;
		}
	}
	
	//
	private Punkt linkerObererPunkt, rechterUntererPunkt;
	
	//
	Window2(double x, double y, double x2, double y2){
		linkerObererPunkt = new Punkt(x, y);
		rechterUntererPunkt = new Punkt(x2, y2);
	}
	
	Window2(){
		this(0, 0, 20, 10);
	}
	
	//
	public void vergroeﬂern(int faktor) {
		rechterUntererPunkt.x *= faktor;
		rechterUntererPunkt.y *= faktor;
	}
	
	public void setBreite(double w) {
		rechterUntererPunkt.x = w;
	}
	
	public void setHoehe(double h) {
		rechterUntererPunkt.y = h;
	}
	
	public Punkt addPunkt(double x, double y) {
		return new Punkt(x, y);
	}
	
	public Punkt getLinkerObererPunkt(){
		return linkerObererPunkt;
	}
	
	public Punkt getRechterUntererPunkt() {
		return rechterUntererPunkt;
	}
}
