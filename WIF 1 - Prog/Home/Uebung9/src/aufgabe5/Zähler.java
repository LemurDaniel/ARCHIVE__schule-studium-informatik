package aufgabe5;

public class Z�hler {
	
	private int wert;
	
	public Z�hler(){}
	
	
	public Z�hler(int startWert){
		wert = startWert;
	}
	public int getWert(){
		return wert;
	}
	public void erh�he(){
		wert++;
	}
	public void setzeAufNull(){
		wert = 0;
	}
}