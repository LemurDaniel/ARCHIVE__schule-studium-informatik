package aufgabe2;

 @SuppressWarnings("unused")
public class Window3 {
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
	Window3(double x, double y, double x2, double y2){
		linkerObererPunkt = new Punkt(x, y);
		rechterUntererPunkt = new Punkt(x2, y2);
	}
	
	Window3(){
		this(0, 0, 20, 10);
	}
	//
	public void vergroeﬂern(int faktor) {
		rechterUntererPunkt.x *= faktor;
		rechterUntererPunkt.y *= faktor;
	}
	
	public void addDim(double width, double height) {
		rechterUntererPunkt.x = width;
		rechterUntererPunkt.y = height;	
	}
	
	public Punkt addPunkt(double x, double y) {
		return new Punkt(x, y);
	}
	
	public Punkt[] eckPunkte() {
		return new Punkt[]{ linkerObererPunkt, rechterUntererPunkt };
	}
}
