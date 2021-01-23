package aufgabe6;

import aufgabe6.temperaturmessung.TemperaturMessreihe;
import java.time.LocalDate;

public class TestMesswerte	{
	
	public static void main (String[] args)	{
		
		double fahrenheit;
		TemperaturMessreihe temperaturMessungen = new TemperaturMessreihe (5, "°C");
		LocalDate datum1 = LocalDate.of(2000,5,10);
		temperaturMessungen.addMesswert (25.3, datum1);
		
		LocalDate datum2 = LocalDate.of(2001,5,10);
		temperaturMessungen.addMesswert (23.0, datum2);
		
		LocalDate datum3 = LocalDate.of(2002,5,10);
		temperaturMessungen.addMesswert (18.4, datum3);
		
		LocalDate datum4 = LocalDate.of(2003,5,10);
		temperaturMessungen.addMesswert (26.9, datum4);
		
		LocalDate datum5 = LocalDate.of(2004,5,10);
		temperaturMessungen.addMesswert (28.0, datum5);
		
		fahrenheit = TemperaturMessreihe.CelsiusToFahrenheit (25.0);		
		System.out.println("25.0 °C entsprechen " + fahrenheit + "° F.");
		System.out.println();
		temperaturMessungen.print();
	}
}
