package aufgabe6;

public class Z�hler {
	
	private int wert;
	
	public Z�hler(){}
	
	
	public Z�hler(int startWert){
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