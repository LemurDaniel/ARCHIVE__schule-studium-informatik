package aufgabe7.emobil;

import aufgabe7.Fahrzeug;

public class Tesla extends Fahrzeug {

	@Override
	public void hatEDrive() {
		System.out.println("Der " + this.getClass().getSimpleName() + " hat einen EDrive");
	}
}
