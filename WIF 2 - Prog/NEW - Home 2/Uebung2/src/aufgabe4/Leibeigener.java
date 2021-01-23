package aufgabe4;

public class Leibeigener extends Bauer {

	@Override
	public int zuVersteuerndesEinkommen() {
		return Math.max(0, super.zuVersteuerndesEinkommen()-12);
	}
}
