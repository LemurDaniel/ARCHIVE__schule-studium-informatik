package aufgabe3;

public class TankException extends Exception {

	private int delta;
	
	public TankException(String message, int delta) {
		super(message);
		this.delta = delta;
	}
	
	public int getDelta() {
		return delta;
	}
}
