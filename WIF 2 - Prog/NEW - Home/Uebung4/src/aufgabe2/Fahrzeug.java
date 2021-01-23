package aufgabe2;

public abstract class Fahrzeug implements FahrzeugCockpit {

	
	@Override
	public void bewegeLenkrad() {
		System.out.println("Der " + this.getClass().getSimpleName() + " lenkt.");

	}

	@Override
	public void gibGas() {
		System.out.println("Der " + this.getClass().getSimpleName() + " gibt Gas.");

	}

	@Override
	public void bremse() {
		System.out.println("Der " + this.getClass().getSimpleName() + " bremst.");

	}

}
