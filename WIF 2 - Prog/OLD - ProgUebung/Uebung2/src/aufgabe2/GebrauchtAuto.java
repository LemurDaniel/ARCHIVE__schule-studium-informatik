package aufgabe2;

import java.math.BigDecimal;

public class GebrauchtAuto extends Auto {

	private int kilometerstand;
	
	GebrauchtAuto(double neuwagenpreis, int baujahr, String modell, int kilometerstand){
		super(neuwagenpreis, baujahr, modell);
		this.kilometerstand = kilometerstand;
	}
	
	@Override
	public double getPreis() {		
		double preis = round(neuwagenpreis* (1 - (kilometerstand / 20_000) * 0.1), 2);
		return preis <=20_000 ? 20_000:preis;
	}
	
	public double round(double d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Double.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	
}
