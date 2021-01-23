package aufgabe5;

public class Leibeigener extends Bauer {
	
	@Override
	public int zuVersteuerndesEinkommen() {
		return Math.max(0, einkommen-12);
	}

}
