package aufgabe6.temperaturmessung;

import aufgabe6.messdaten.Messreihe;

public class TemperaturMessreihe extends Messreihe {

	private String temperaturEinheit;
	
	
	public TemperaturMessreihe(int messwertAnzahl, String temperaturEinheit) {
		super(messwertAnzahl);
		this.temperaturEinheit = temperaturEinheit;
	}
	
	@Override
	public void print() {
		System.out.println("Ausgabe von Temperatur Messdaten in " + temperaturEinheit + ": ");
		super.print();
	}
	
	
	
	public static double CelsiusToFahrenheit(double celsiusTemp) {
		return celsiusTemp*1.8 + 32;
	}
	
}
