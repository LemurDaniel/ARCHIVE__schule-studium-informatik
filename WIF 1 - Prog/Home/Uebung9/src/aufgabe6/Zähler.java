package aufgabe6;

public class Zähler {
	
	private int wert;
	
	public Zähler(){}
	
	
	public Zähler(int startWert){
		wert = startWert;
	}
	public int getWert(){
		return wert;
	}
	public void aendere(int delta) {
		wert += delta;
	}
	public void setzeAufNull(){
		wert = 0;
	}
}