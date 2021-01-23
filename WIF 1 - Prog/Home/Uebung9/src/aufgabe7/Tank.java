package aufgabe7;

public class Tank {
	
	private int soll, ist;
	
	Tank(int ist, int soll){
		this.soll = soll;
		this.ist = ist;
	}
	
	public void aendere(int delta) {
		ist += delta;
	}
	
	public int getIst() {
		return ist;
	}
	
	public int getSoll() {
		return soll;
	}
	
	
}
