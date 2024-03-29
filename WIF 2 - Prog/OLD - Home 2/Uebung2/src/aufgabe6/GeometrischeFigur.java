package aufgabe6;

public abstract class GeometrischeFigur {

	protected abstract double berechneFlaeche();
	protected abstract double berechneUmfang();
	
	public void print() {
		System.out.println(this.getClass().getSimpleName());
		System.out.println("Die Fl�che betr�gt: " + berechneFlaeche());
		System.out.println("Der Umfang betr�gt: " + berechneUmfang());
		System.out.println();
	}
}
