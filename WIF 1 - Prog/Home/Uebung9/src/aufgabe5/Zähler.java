package aufgabe5;

public class Zähler {
	
	private int wert;
	
	public Zähler(){}
	
	
	public Zähler(int startWert){
		wert = startWert;
	}
	public int getWert(){
		return wert;
	}
	public void erhöhe(){
		wert++;
	}
	public void setzeAufNull(){
		wert = 0;
	}
}