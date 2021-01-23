package aufgabe5;

public class Einwohner {

	protected int einkommen;
	
	public int zuVersteuerndesEinkommen() {
		return einkommen;
	}
	
	public int steuer() {
		return Math.max(1, (int)Math.floor(zuVersteuerndesEinkommen()*0.1) );
	}
	
	public int setEinkommen(int einkommen) {
		this.einkommen = einkommen;
		return einkommen;

	}
}
